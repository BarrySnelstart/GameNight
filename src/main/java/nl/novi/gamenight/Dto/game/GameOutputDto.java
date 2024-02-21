package nl.novi.gamenight.Dto.game;

import nl.novi.gamenight.Model.Category;

public class GameOutputDto {
    public GameOutputDto(Long gameID, String name, String manufacturer, int minimumPlayers, int maximumPlayers, int age, int minimumDuration, int averageDuration, Category category, String type, int averageStarValue) {
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
    }
public GameOutputDto() {}
    public Long gameID;
    public String name;
    public String manufacturer;
    public int minimumPlayers;
    public int maximumPlayers;
    public int age;
    public int minimumDuration;
    public int averageDuration;
    public Category category;
    public String type;
    public int averageStarValue;
}
