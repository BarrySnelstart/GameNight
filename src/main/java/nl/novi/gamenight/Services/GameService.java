package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

//    public GameOutputDto addGame (GameOutputDto gameOutputDto) {
//
//        gameRepository.save();
//    }

    public void fromGameInputDtoToEntity (Game game) {
        GameOutputDto gameOutputDto = new GameOutputDto();
        gameOutputDto.gameID = game.getGameID();
        gameOutputDto.name = game.getName();


    }

    public void fromEntityToGameOutputDto () {

    }

}
