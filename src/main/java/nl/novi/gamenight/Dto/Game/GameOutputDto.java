package nl.novi.gamenight.Dto.Game;

import nl.novi.gamenight.Model.Category;

public class GameOutputDto {
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
    public String averageStarValue;

}
