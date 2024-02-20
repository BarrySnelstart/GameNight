package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Model.Manual;
import nl.novi.gamenight.Repository.ManualRepository;
import nl.novi.gamenight.Services.ManualService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("manual")
public class ManualController {
    private final ManualService manualService;
    private ManualRepository manualRepository;

    public ManualController(ManualService manualUpDownloadService, ManualRepository manualUpDownloadRepository) {
        this.manualService = manualUpDownloadService;
        this.manualRepository = manualUpDownloadRepository;
    }
    /*TODO Create a list*/

    @PostMapping("/{gameID}")
    public ResponseEntity<String> upLoadManual(@RequestParam("file") MultipartFile multipartFile, @PathVariable("gameID") Long gameID) throws IOException {
        String data = manualService.manualUpload(multipartFile, gameID);
        return ResponseEntity.ok("File Uploaded");
    }

    @GetMapping("/{manualID}")
    public ResponseEntity<?> downLoadManual(@PathVariable("manualID") Long manualID) throws IOException {
        byte[] download = manualService.manualDownload(manualID);
        Optional<Manual> dbData = manualRepository.findById(manualID);
        MediaType mediaType = MediaType.valueOf(dbData.get().getType());
        return ResponseEntity.ok().contentType(mediaType).body(download);
    }

}
