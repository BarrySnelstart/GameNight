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

    @PostMapping("create/{id}")
    public ResponseEntity<Object> addGameExpansion(@Validated @PathVariable("id") Long gameID, @RequestBody GameInputDto gameInputDto, BindingResult bindingResult) {
        return expansionService.addGameExpansion(gameInputDto, bindingResult, gameID);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteAGameExpansionByID(@PathVariable("id") Long expansionID){
        return expansionService.deleteAGameExpansionByID(expansionID);
    }

    @GetMapping("expansions")
    public List<GameExpansionOutputDto> getAllExpansions() {
        return expansionService.getAllExpansions();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getExpansionByID(@PathVariable("id") Long ID) {
        return expansionService.getExpansionsByID(ID);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateGameExpansion(@Validated @PathVariable("id") Long expansionID, @RequestBody GameExpansionInPutDto expansionData){
        return expansionService.updateGameExpansion(expansionID, expansionData);
    }

}
