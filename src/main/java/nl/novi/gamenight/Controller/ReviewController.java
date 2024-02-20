package nl.novi.gamenight.Controller;


import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Dto.reviewDto.ReviewOutputDto;
import nl.novi.gamenight.Services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {
    public final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/create")
    public ResponseEntity<Object> addReview(@Validated @RequestBody ReviewInputDto reviewInputDto, BindingResult bindingResult) {
        return reviewService.addReview(reviewInputDto, bindingResult);
    }

    @GetMapping("reviews")
    public List<ReviewOutputDto> getReviewList() {
        return reviewService.getReviewList();
    }

    @PutMapping("update/{reviewID}")
    public ResponseEntity<Object> updateReviewByID(@Validated @PathVariable("reviewID") Long reviewID, @RequestBody ReviewInputDto updatedReview, BindingResult bindingResult) {
        return reviewService.updateReviewByID(reviewID, updatedReview, bindingResult);
    }

    @GetMapping("/{reviewID}")
    public ResponseEntity<ReviewOutputDto> getReviewByID(@PathVariable("reviewID") Long reviewID) {
        return reviewService.getReviewByID(reviewID);
    }

    @DeleteMapping("delete/{reviewID}")
    public ResponseEntity deleteReviewByID(@PathVariable("reviewID") Long reviewID) {
        return reviewService.deleteReviewByID(reviewID);
    }


}