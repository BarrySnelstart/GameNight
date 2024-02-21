package nl.novi.gamenight.Dto.expansionDto;

import nl.novi.gamenight.Model.Category;
import nl.novi.gamenight.Model.Game;

public class GameExpansionInPutDto extends Game {
    public Long baseGameID;

    public GameExpansionInPutDto(Long gameID, String name, String manufacturer, int minimumPlayers, int maximumPlayers, int age, int minimumDuration, int averageDuration, Category category, String type, int averageStarValue, Long baseGameID) {
        super(gameID, name, manufacturer, minimumPlayers, maximumPlayers, age, minimumDuration, averageDuration, category, type, averageStarValue);
        this.baseGameID = baseGameID;
    }

    public GameExpansionInPutDto(Long baseGameID) {
        this.baseGameID = baseGameID;
    }
public GameExpansionInPutDto (){}
}
