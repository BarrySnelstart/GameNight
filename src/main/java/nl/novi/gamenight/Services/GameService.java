package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Model.Game.Game;
import nl.novi.gamenight.Repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameOutputDto addGame (GameInputDto gameInput) {
        Game game = fromGameInputDtoToEntity(gameInput);
        gameRepository.save(game);
        return fromEntityToGameOutputDto(game);
    }
    public List <GameOutputDto> getAllGames (){
        List<GameOutputDto> allGamesList = new ArrayList<>();
        for (Game games: gameRepository.findAll()) {
            allGamesList.add(fromEntityToGameOutputDto(games));
        }
        return  allGamesList;
    }
    public GameOutputDto getGameByID (Long id){
        var game = gameRepository.getReferenceById(id);
        return fromEntityToGameOutputDto(game);
    }
    public Game fromGameInputDtoToEntity (GameInputDto gameInput) {
        var game = new Game();
        game.setName(gameInput.name);
        game.setManufacturer(gameInput.manufacturer);
        game.setMinimumPlayers(gameInput.minimumPlayers);
        game.setMaximumPlayers(gameInput.maximumPlayers);
        game.setAge(gameInput.age);
        game.setMinimumDuration(gameInput.minimumDuration);
        game.setAverageDuration(gameInput.averageDuration);
        //game.setCategory(gameInput.category);
        game.setType(gameInput.type);
        return game;
    }

    public GameOutputDto fromEntityToGameOutputDto (Game game) {
        GameOutputDto gameOutputDto = new GameOutputDto();
        gameOutputDto.gameID = game.getGameID();
        gameOutputDto.name = game.getName();
        gameOutputDto.manufacturer = game.getManufacturer();
        gameOutputDto.minimumPlayers = game.getMinimumDuration();
        gameOutputDto.maximumPlayers = game.getMaximumPlayers();
        gameOutputDto.age = game.getAge();
        gameOutputDto.minimumDuration = game.getMinimumDuration();
        gameOutputDto.averageDuration = game.getAverageDuration();
        gameOutputDto.category = game.getCategory();
        gameOutputDto.type = game.getType();
        gameOutputDto.averageStarValue = game.getAverageStarValue();
        return gameOutputDto;
    }

}
