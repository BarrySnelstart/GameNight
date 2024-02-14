package nl.novi.gamenight.Model;


import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@Entity(name = "games")
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
    private int averageStarValue;

    @OneToMany(mappedBy = "games")
    private List<Review> reviews;

    @OneToMany(mappedBy = "games")
    private List<Expansion> expansion;

    @OneToOne(mappedBy = "game")
    private Manual manualUpDownload;

    public Game(Long gameID, String name, String manufacturer, int minimumPlayers, int maximumPlayers, int age, int minimumDuration, int averageDuration, Category category, String type, int averageStarValue) {
        this.gameID = gameID;
        this.name = name;
        this.manufacturer = manufacturer;
        this.minimumPlayers = minimumPlayers;
        this.maximumPlayers = maximumPlayers;
        this.age = age;
        this.minimumDuration = minimumDuration;
        this.averageDuration = averageDuration;
        this.category = category;
        this.type = type;
        this.averageStarValue = averageStarValue;
        this.reviews = reviews;
        this.expansion = expansion;
    }

    public Game() {
    }
}
