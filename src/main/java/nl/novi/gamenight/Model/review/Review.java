package nl.novi.gamenight.Model.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Model.User.User;

import java.util.List;

@Getter
@Setter
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    Long reviewID;
    /*TODO Grote bepalen van de varschar*/
    @Column(columnDefinition = "VARCHAR(12000)")
    String userReview;

    private int starRating;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User users;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private Game games;
}
