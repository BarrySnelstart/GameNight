package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Model.Role;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.RoleRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /*TODO Move logica to ServiceLayer*/
    @PostMapping("/users")
    public String createUser(@RequestBody UserInputDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        var rol = roleRepos.findById("USER");

        if (rol.isEmpty()) {
            return "";
        }

        userRoles.add(0, rol.get());

        newUser.setRoles(userRoles);

        userRepository.save(newUser);

        return "Done";
    }
}
