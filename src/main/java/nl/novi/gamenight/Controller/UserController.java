package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Model.User.Role;
import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Repository.RoleRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    UserService userService;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserController(UserService userService, RoleRepository roleRepos, PasswordEncoder encoder, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserInputDto userDto) {

        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));


        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepos.findById(rolename);

            userRoles.add(or.get());
        }
        newUser.setRoles(userRoles);

        userRepository.save(newUser);

        return "Done";
    }
//    @PostMapping("/users")
//    public String createUser(@RequestBody UserInputDto userDto) {
//        User newUser = new User();
//        newUser.setUsername(userDto.username);
//        newUser.setPassword(encoder.encode(userDto.password));
//        List<String> userRoles = new ArrayList<>();
//        userRoles.add("User");
//        newUser.setRoles(userRoles);
//
//        userRepository.save(newUser);
//
//        return "Done";
//    }
//    @GetMapping
//    public List <UserOutputDto> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity <UserOutputDto> getUserByID(@PathVariable("id") Long id) {
//        UserOutputDto user = userService.getUserByID(id);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity deleteUserByID(@PathVariable("id") Long id) {
//        return userService.deleteUserByID(id);
//    }
//
//
//    @PostMapping
//    public ResponseEntity <Object> addUser(@Validated @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
//        return userService.addUser(userInputDto, bindingResult);
//    }
//
//    @PutMapping("/user/{id}")
//    public ResponseEntity <Object> updateUserByID(@PathVariable("id") Long id, @Validated @RequestBody UserInputDto updateUser, BindingResult bindingResult) {
//        return userService.updateUserNameByID(id, updateUser, bindingResult);
//    }
}
