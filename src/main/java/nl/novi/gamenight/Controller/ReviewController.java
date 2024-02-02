package nl.novi.gamenight.Controller;


import nl.novi.gamenight.Dto.reviewDto.ReviewInputDto;
import nl.novi.gamenight.Services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {
    public final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/{id}")
    public ResponseEntity<Object> addReview(@Validated @PathVariable ("id")Long gameID, @RequestBody ReviewInputDto reviewInputDto, BindingResult bindingResult) {
        return reviewService.addReview(reviewInputDto, bindingResult,gameID);
    }
}