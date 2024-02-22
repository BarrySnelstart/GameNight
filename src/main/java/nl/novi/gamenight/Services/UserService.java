package nl.novi.gamenight.Services;


import nl.novi.gamenight.Dto.user.UserInputDto;
import nl.novi.gamenight.Dto.user.UserOutputDto;
import nl.novi.gamenight.Model.Role;
import nl.novi.gamenight.Model.User;
import nl.novi.gamenight.Repository.RoleRepository;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.exceptions.IdNotFoundException;
import nl.novi.gamenight.exceptions.UserNotUniqueException;
import nl.novi.gamenight.security.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UserService {
    UserRepository userRepository;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserService(RoleRepository roleRepos, PasswordEncoder encoder, UserRepository userRepository) {
        this.roleRepos = roleRepos;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> createUser(@RequestBody UserInputDto userDto, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : validationResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);

        } else if (userRepository.findByUsername(userDto.username).isPresent()) {
            return new ResponseEntity<>(new UserNotUniqueException("User is not Unique").getMessage(), HttpStatus.BAD_REQUEST);
        }

        List<Role> userRoles = new ArrayList<>();
        var rol = roleRepos.findById("USER");
        userRoles.add(0, rol.get());

        User newUser = toEntity(userDto);
        newUser.setUsername(userDto.username);
        newUser.setPassword(encoder.encode(userDto.password));
        newUser.setRoles(userRoles);
        userRepository.save(newUser);

        if (rol.isEmpty()) {
            return ResponseEntity.badRequest().body("No UserRoles found");
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("")
                .buildAndExpand(newUser)
                .toUri();
        return ResponseEntity.created(location).body(toDto(newUser));
    }

    public List<UserOutputDto> getAllUsers() {
        List<UserOutputDto> allUsersList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            allUsersList.add(toDto(user));
        }
        return allUsersList;
    }

    public ResponseEntity<Object> getUserByID(Long userID) {
        UserOutputDto user = toDto(userRepository.getReferenceById(userID));
        return ResponseEntity.ok(user);
    }

    public ResponseEntity deleteUserByID(Long userID) {
        if (!checkOwningUser(userID)) {
            return ResponseEntity.badRequest().body("User is not authorized");
        }

        Optional<User> ifExist = userRepository.findById(userID);
        if (ifExist.isPresent()) {
            userRepository.deleteById(userID);
            return ResponseEntity.ok("User Deleted");
        } else {
            return new ResponseEntity<>(new IdNotFoundException("UserID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateUserNameByID(@Validated Long userID, UserInputDto updatedUser, BindingResult bindingResult) {
        Optional<User> ifExist = userRepository.findById(userID);
        if (!checkOwningUser(userID)) {
            return ResponseEntity.badRequest().body("User is not authorized");
        }
        if (ifExist.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            } else {
                var updateUser = userRepository.getReferenceById(userID);
                updateUser.setUsername(updatedUser.username);
                updateUser.setPassword(encoder.encode(updatedUser.password));
                userRepository.save(updateUser);

                return ResponseEntity.ok(updatedUser);
            }
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public UserOutputDto toDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.userID = user.getUserID();
        userOutputDto.username = user.getUsername();
        return userOutputDto;
    }

    public User toEntity(UserInputDto userInputDto) {
        var user = new User();
        user.setUsername(userInputDto.username);
        user.setPassword(encoder.encode(userInputDto.password));
        return user;
    }

    private boolean checkOwningUser(Long userID) {
        var user = userRepository.findById(userID);
        if (user.isEmpty()) {
            return false;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MyUserDetails userDetails = (MyUserDetails) principal;

        if(userID.equals(userDetails.getUserId())) {
            return true;
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
    }
}

















