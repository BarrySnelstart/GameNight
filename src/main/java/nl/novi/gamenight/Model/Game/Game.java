package nl.novi.gamenight.Model.Game;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Entity (name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Long gameID;

    private String name;
    private String manufacturer;
    private int minimumPlayers;
    private int maximumPlayers;
    private int age;
    private int minimumDuration;
    private int averageDuration;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String type;
    private String averageStarValue;
}
