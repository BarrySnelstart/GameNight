package nl.novi.gamenight.Dto.reviewDto;

public class ReviewInputDto {
    public ReviewInputDto(String userReview, int starRating, Long gameID) {
        this.userReview = userReview;
        this.starRating = starRating;
        this.gameID = gameID;
    }
    public ReviewInputDto() {}

    public String userReview;
    public int starRating;

    public Long gameID;
}
