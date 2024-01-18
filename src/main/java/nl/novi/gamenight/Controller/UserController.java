package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.Game.GameInputDto;
import nl.novi.gamenight.Dto.Game.GameOutputDto;
import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List <GameOutputDto> getAllUsers() {
        return userService.getAllusers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <UserOutputDto> getUserByID(@PathVariable("id") Long id) {
        UserOutputDto user = userService.getuserByID(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserByID(@PathVariable("id") Long id) {
        return userService.deleteUserByID(id);
    }
}

@PostMapping
public ResponseEntity <Object> addUser(@Validated @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
    return userService.addGame(userInputDto, bindingResult);
}

@PutMapping("/user/{id}")
public ResponseEntity <Object> updateUserByID(@PathVariable("id") Long id, @Validated @RequestBody UserInputDto updateUser, BindingResult bindingResult) {
    return userService.updateUserByID(id, updatedUser, bindingResult);
}
