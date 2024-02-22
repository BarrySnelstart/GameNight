package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Dto.reviewDto.ReviewOutputDto;
import nl.novi.gamenight.Model.Review;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ReviewRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.exceptions.IdNotFoundException;
import nl.novi.gamenight.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import java.util.*;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    UserRepository userRepository;
    GameRepository gameRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public ResponseEntity<Object> addReview(ReviewInputDto reviewInputDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MyUserDetails userDetails = (MyUserDetails) principal;
            var user = userRepository.getReferenceById(userDetails.getUserId());
            var game = gameRepository.getReferenceById(reviewInputDto.gameID);
            Review review = new Review();

            review.setUserReview(reviewInputDto.userReview);
            review.setStarRating(reviewInputDto.starRating);

            review.setGames(game);
            review.setUsers(user);
            reviewRepository.save(review);

            updateStarValue(reviewInputDto.gameID);
            ReviewOutputDto reviewOutputDto = toDto(review);
            reviewOutputDto.gameID = reviewInputDto.gameID;
            reviewOutputDto.userID = user.getUserID();
            return ResponseEntity.created(null).body(reviewOutputDto);
        }
    }

    void updateStarValue(Long gameID) {
        System.out.println();

        int count = 0;
        int value = 0;
        int averageValue = 0;


        for (Review review : reviewRepository.findAll()) {

            if (review.getGames().getGameID() == (gameID)) {

                value = value + review.getStarRating();
                count++;
            }
        }
        averageValue = value / count;
        var game = gameRepository.getReferenceById(gameID);
        game.setAverageStarValue(averageValue);
        gameRepository.save(game);
    }

    public List<ReviewOutputDto> getReviewList() {
        List<ReviewOutputDto> reviewOutputDtoList = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {

            reviewOutputDtoList.add(toDto(review));
        }
        return reviewOutputDtoList;

    }

    public ResponseEntity<ReviewOutputDto> getReviewByID(Long reviewID) {
        ReviewOutputDto foundReview = toDto(reviewRepository.getReferenceById(reviewID));
        return ResponseEntity.ok().body(foundReview);
    }


    public ResponseEntity<Object> updateReviewByID(Long reviewID, ReviewInputDto updatedReviewInput, BindingResult bindingResult) {
        Optional<Review> ifExist = reviewRepository.findById(reviewID);

        if (ifExist.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            } else {
                ReviewOutputDto foundReview = toDto(reviewRepository.getReferenceById(reviewID));
                foundReview.userReview = updatedReviewInput.userReview;
                updateStarValue(foundReview.gameID);
                reviewRepository.save(toEntity(updatedReviewInput));
                return ResponseEntity.ok(foundReview);
            }
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
/*TODO Database Constraint*/
    public ResponseEntity deleteReviewByID(Long reviewID) {
        Optional<User> ifExist = userRepository.findById(reviewID);
        if (ifExist.isPresent()) {
            userRepository.deleteById(reviewID);
            return ResponseEntity.ok("Review Deleted");
        } else {
            return new ResponseEntity<>(new IdNotFoundException("Review not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ReviewOutputDto toDto(Review review) {
        ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
        reviewOutputDto.userReview = review.getUserReview();
        reviewOutputDto.starRating = review.getStarRating();
        reviewOutputDto.reviewID = review.getReviewID();
        reviewOutputDto.gameID = review.getGames().getGameID();
        reviewOutputDto.userID = review.getUsers().getUserID();
        return reviewOutputDto;
    }

    public Review toEntity(ReviewInputDto reviewInputDto) {
        var review = new Review();
        review.setUserReview(reviewInputDto.userReview);
        review.setStarRating(reviewInputDto.starRating);
        return review;
    }

}
