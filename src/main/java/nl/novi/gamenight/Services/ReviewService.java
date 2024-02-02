package nl.novi.gamenight.Services;
import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Dto.reviewDto.ReviewOutputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Model.review.Review;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ReviewRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.security.MyUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.Map;

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





    public ResponseEntity<Object> addReview(ReviewInputDto reviewInputDto, BindingResult bindingResult, Long gameId ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
           Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MyUserDetails userDetails = (MyUserDetails)principal;
            var user = userRepository.getReferenceById(userDetails.getUserId());
            var game = gameRepository.getReferenceById(gameId);
            Review review = new Review();

            review.setUserReview(reviewInputDto.userReview);
            review.setStarRating(reviewInputDto.starRating);
            review.setGames(game);
            review.setUsers(user);

            reviewRepository.save(review);

            /*TODO USE MAPPER*/
            ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
            reviewOutputDto.userReview = review.getUserReview();
            reviewOutputDto.userID = user.getUserID();
            reviewOutputDto.starRating=review.getStarRating();
            reviewOutputDto.gameID = game.getGameID();


            return ResponseEntity.created(null).body(reviewOutputDto);
        }
    }
    public Review mapToEntity(ReviewInputDto reviewInput) {
        var review = new Review();
        review.setUserReview(reviewInput.userReview);
        review.setStarRating(reviewInput.starRating);
        return review;
    }

    public ReviewOutputDto mapToDto(Review review) {
        ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
        //reviewOutputDto.gameID = review.getGamesID();
        reviewOutputDto.userReview = review.getUserReview();
        reviewOutputDto.starRating = review.getStarRating();
        //reviewOutputDto.userID= review.getUserID();
        return reviewOutputDto;
    }


}
