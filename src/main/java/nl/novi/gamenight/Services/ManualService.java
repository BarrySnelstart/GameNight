package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.game.GameOutputDto;
import nl.novi.gamenight.Dto.manual.ManualOutputDto;
import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Model.Manual;
import nl.novi.gamenight.Repository.GameRepository;
import nl.novi.gamenight.Repository.ManualRepository;
import nl.novi.gamenight.utils.CompressUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public byte[] manualDownload(Long ID) throws IOException {
        Optional<Manual> download = manualRepository.findById(ID);
        Manual data = download.get();
        return CompressUtil.decompress(data.getData());


    }

    public List<ManualOutputDto> getAllManuals() {
        List<ManualOutputDto> manualList = new ArrayList<>();
        for (Manual manuals : manualRepository.findAll()) {
            ManualOutputDto addToList = new ManualOutputDto();
            addToList.gameName = manuals.getGame().getName();
            addToList.manualID = manuals.getManualID();
            manualList.add(addToList);
        }
        return manualList;
    }
}
