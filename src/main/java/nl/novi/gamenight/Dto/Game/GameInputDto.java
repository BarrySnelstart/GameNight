package nl.novi.gamenight.Dto.Game;

import nl.novi.gamenight.Model.Game.Category;
import nl.novi.gamenight.Model.Game.Type;

public class GameInputDto {
    public String name;
    public String manufacturer;
    public int minimumPlayers;
    public int maximumPlayers;
    public int age;
    public int minimumDuration;
    public int averageDuration;
    public Category category;
    public Type type;
    public String averageStarValue;

}
