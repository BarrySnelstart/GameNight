package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    UserService userService;
/*TODO Check Return type for correct Status*/
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List <UserOutputDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <UserOutputDto> getUserByID(@PathVariable("id") Long id) {
        UserOutputDto user = userService.getUserByID(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserByID(@PathVariable("id") Long id) {
        return userService.deleteUserByID(id);
    }


    @PostMapping
    public ResponseEntity <Object> addUser(@Validated @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
        return userService.addUser(userInputDto, bindingResult);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity <Object> updateUserByID(@PathVariable("id") Long id, @Validated @RequestBody UserInputDto updateUser, BindingResult bindingResult) {
        return userService.updateUserameByID(id, updateUser, bindingResult);
    }
}
