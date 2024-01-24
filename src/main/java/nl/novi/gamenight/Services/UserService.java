package nl.novi.gamenight.Services;

import nl.novi.gamenight.Dto.User.UserInputDto;
import nl.novi.gamenight.Dto.User.UserOutputDto;
import nl.novi.gamenight.Model.User.User;
import nl.novi.gamenight.Repository.UserRepository;
import nl.novi.gamenight.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<Object> addUser(@Validated UserInputDto userInput, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        } else {
            User user = fromUserInputDtoToEntity(userInput);
            userRepository.save(user);
            return ResponseEntity.created(null).body(fromEntityToUserOutputDto(user));
        }
    }

    public List<UserOutputDto> getAllUsers() {
        List<UserOutputDto> allUsersList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            allUsersList.add(fromEntityToUserOutputDto(user));
        }
        return allUsersList;
    }

    public UserOutputDto getUserByID(Long id) {
        var user = userRepository.getReferenceById(id);
        return fromEntityToUserOutputDto(user);
    }

    public ResponseEntity updateUserNameByID(@Validated Long id, UserInputDto updatedUser, BindingResult bindingResult) {
        Optional<User> ifExist = userRepository.findById(id);

        if (ifExist.isPresent()) {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errors);
            } else {
                var update = userRepository.getReferenceById(id);
                User setUpdate = fromUserInputDtoToEntity(updatedUser);
                setUpdate.setUserName(updatedUser.userName);
                setUpdate.setPassWord(updatedUser.passWord);
                setUpdate.setUserID(update.getUserID());
                userRepository.save(update);

                return ResponseEntity.created(null).body(setUpdate);
            }
        } else {
            return new ResponseEntity<>(new IdNotFoundException("ID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity deleteUserByID(Long id) {
        Optional<User> ifExist = userRepository.findById(id);
        if (ifExist.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User Deleted");
        } else {
            return new ResponseEntity<>(new IdNotFoundException("UserID not found in database").getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public User fromUserInputDtoToEntity(UserInputDto UserInput) {
        var user = new User();
        user.setUserName(UserInput.userName);
        user.setPassWord(UserInput.passWord);
        return user;
    }

    public UserOutputDto fromEntityToUserOutputDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.userID = user.getUserID();
        userOutputDto.userName = user.getUserName();

        /* TODO Delete password from outputDto when password is encoded*/
        userOutputDto.password = user.getPassWord();
        return userOutputDto;
    }


}







