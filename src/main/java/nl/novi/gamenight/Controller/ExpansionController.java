package nl.novi.gamenight.Controller;


import nl.novi.gamenight.Dto.expansionDto.GameExpansionInPutDto;
import nl.novi.gamenight.Dto.expansionDto.GameExpansionOutputDto;
import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Services.ExpansionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expansion")
public class ExpansionController {
    private final ExpansionService expansionService;

    public ExpansionController(ExpansionService expansionService) {
        this.expansionService = expansionService;
    }

    @PostMapping("create/{baseGameID}")
    public ResponseEntity<Object> addGameExpansion(@Validated @PathVariable("baseGameID") Long baseGameID, @RequestBody GameInputDto gameInputDto, BindingResult bindingResult) {
        return expansionService.addGameExpansion(gameInputDto, bindingResult, baseGameID);
    }
    @DeleteMapping("delete/{expansionID}")
    public ResponseEntity deleteAGameExpansionByID(@PathVariable("expansionID") Long expansionID){
        return expansionService.deleteAGameExpansionByID(expansionID);
    }

    @GetMapping("expansions")
    public List<GameExpansionOutputDto> getAllExpansions() {
        return expansionService.getAllExpansions();
    }

    @GetMapping("{expansionID}")
    public ResponseEntity<Object> getExpansionByID(@PathVariable("expansionID") Long expansionID) {
        return expansionService.getExpansionsByID(expansionID);
    }
    /*TODO violates foreign key constraint */
    @PutMapping("update/{expansionID}")
    public ResponseEntity<Object> updateGameExpansion(@Validated @PathVariable("expansionID") Long expansionID, @RequestBody GameExpansionInPutDto expansionData){
        return expansionService.updateGameExpansion(expansionID, expansionData);
    }

}
