package nl.novi.gamenight.Model.Game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity (name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String manufacturer;
    private int minimumPlayers;
    private int maximumPlayers;
    private int age;
    private int minimumDuration;
    private int averageDuration;
    private Category category;
    private Type type;
    private String averageStarValue;

    @OneToMany(mappedBy = "game")
    List<GameExpansion> gameExpansionList;
}
