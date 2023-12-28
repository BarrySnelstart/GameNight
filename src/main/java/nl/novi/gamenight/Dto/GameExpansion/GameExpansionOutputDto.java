package nl.novi.gamenight.Dto.GameExpansion;

import nl.novi.gamenight.Model.Category;
import nl.novi.gamenight.Model.Type;

public class GameExpansionOutputDto {
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
    public Long id;
}
