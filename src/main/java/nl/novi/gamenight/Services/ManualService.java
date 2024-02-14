package nl.novi.gamenight.Services;

import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Model.Manual;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ManualRepository;
import nl.novi.gamenight.utils.CompressUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ManualService {
    private ManualRepository manualRepository;
    private GameRepository gameRepository;

    public ManualService(ManualRepository manualRepository, GameRepository gameRepository) {
        this.manualRepository = manualRepository;
        this.gameRepository = gameRepository;
    }

    public String manualUpload(MultipartFile multipartFile, Long gameID) throws IOException {
        Optional<Game> game = Optional.of(gameRepository.getReferenceById(gameID));
        Game game1 = game.get();
        Manual data = new Manual();
        data.setName(multipartFile.getName());
        data.setType(multipartFile.getContentType());
        data.setData(CompressUtil.compress(multipartFile.getBytes()));
        data.setGame(game1);

        Manual saveData = manualRepository.save(data);
        game1.setManualUpDownload(saveData);
        gameRepository.save(game1);
        return saveData.getName();
    }

    public byte[] manualDownload (Long ID) throws IOException {
        Optional<Manual> download = manualRepository.findById(ID);
        //Optional<Game> game = Optional.of(gameRepository.getReferenceById(gameID));

        Manual data = download.get();
        return CompressUtil.decompress(data.getData());


    }

}
