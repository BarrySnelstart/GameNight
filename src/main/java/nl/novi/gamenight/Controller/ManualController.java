package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Model.Game;
import nl.novi.gamenight.Model.Manual;
import nl.novi.gamenight.Repository.GameRepository;
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


    @PostMapping("/{id}")
    public ResponseEntity<String> upLoadManual(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id) throws IOException {
        return ResponseEntity.ok("File Uploaded");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downLoadManual(@PathVariable("id") Long id) throws IOException {
        byte[] download = manualService.manualDownload(id);
        Optional<Manual> dbData = manualRepository.findById(id);
        MediaType mediaType = MediaType.valueOf(dbData.get().getType());
        return ResponseEntity.ok().contentType(mediaType).body(download);
    }

}
