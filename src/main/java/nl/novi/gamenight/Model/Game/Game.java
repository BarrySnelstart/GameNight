package nl.novi.gamenight.Model.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity (name = "games")
public class Game {
    @Id
    @GeneratedValue
    private int id;
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
}
