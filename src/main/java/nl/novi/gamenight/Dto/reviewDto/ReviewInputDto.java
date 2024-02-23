package nl.novi.gamenight.Dto.reviewDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class ReviewInputDto {

    @NotBlank
    public String userReview;
    @Min(1)
    @Max(5)
    public int starRating;

    public Long gameID;
}
