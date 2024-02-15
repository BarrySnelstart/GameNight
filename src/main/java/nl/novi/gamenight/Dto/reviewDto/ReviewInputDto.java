package nl.novi.gamenight.Dto.reviewDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class ReviewInputDto {
    public ReviewInputDto(String userReview, int starRating, Long gameID) {
        this.userReview = userReview;
        this.starRating = starRating;
        this.gameID = gameID;
    }
    public ReviewInputDto() {}

    @NotBlank
    public String userReview;
    @Min(1)
    @Max(5)
    public int starRating;
    @NotBlank
    public Long gameID;
}
