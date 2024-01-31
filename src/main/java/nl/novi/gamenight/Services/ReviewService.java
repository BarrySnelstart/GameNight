package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Model.review.Review;
import nl.novi.gamenight.Repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReviewService {
    ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ResponseEntity<Object> addReview(ReviewInputDto reviewInputDto, BindingResult bindingResult, Long gameId ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            Review review =new Review();
            reviewRepository.save(review);
            return ResponseEntity.created(null).body(review);
        }
    }

}
