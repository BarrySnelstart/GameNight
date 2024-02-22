package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.user.UserInputDto;
import nl.novi.gamenight.Dto.user.UserOutputDto;
import nl.novi.gamenight.Repository.RoleRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    UserService userService;


    public UserController(UserService userService, RoleRepository roleRepos, PasswordEncoder encoder, UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@Validated @RequestBody UserInputDto userDto, BindingResult validatioResult) {
        return (userService.createUser(userDto, validatioResult));
    }


    @GetMapping("users")
    public List<UserOutputDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{userID}")
    public ResponseEntity<Object> getUserByID(@PathVariable("userID") Long userID) {
        return userService.getUserByID(userID);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity deleteUserByID(@PathVariable("userID") Long userID) {
        return userService.deleteUserByID(userID);
    }

    @PutMapping("/update/{userID}")
    public ResponseEntity<Object>  updateUserNameByID(@Validated @PathVariable("userID") Long ID,@RequestBody UserInputDto updatedUser, BindingResult bindingResult)
    {
        return userService.updateUserNameByID(ID, updatedUser, bindingResult);
    }

}
