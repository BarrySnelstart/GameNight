package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    Long reviewID;

    @Column(columnDefinition = "VARCHAR(300)")
    String userReview;

    private int starRating;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User users;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private Game games;
}
