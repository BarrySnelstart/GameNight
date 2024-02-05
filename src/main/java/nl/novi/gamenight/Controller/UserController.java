package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.RoleRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    UserService userService;


    public UserController(UserService userService, RoleRepository roleRepos, PasswordEncoder encoder, UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@Validated @RequestBody UserInputDto userDto, BindingResult validatioResult) {
        return (userService.createUser(userDto, validatioResult));
    }

    /*TODO Admin Only*/
    @GetMapping
    public List<UserOutputDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /*TODO Admin and Owning User Only*/
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable("id") Long ID) {
        return userService.getUserByID(ID);
    }
    /*TODO Admin and Owning User Only*/
    @DeleteMapping("{id}")
    public ResponseEntity deleteUserByID(@PathVariable("id") Long ID) {
        return userService.deleteUserByID(ID);
    }
    /*TODO Admin and Owning User Only*/
    @PutMapping("{id}")
    public ResponseEntity<Object>  updateUserNameByID(@Validated @PathVariable("id") Long ID,@RequestBody UserInputDto updatedUser, BindingResult bindingResult)
    {
        return userService.updateUserNameByID(ID, updatedUser, bindingResult);
    }

}
