package nl.novi.gamenight.Dto.User;

import jakarta.validation.constraints.NotBlank;
import nl.novi.gamenight.Model.User.UserRole;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserInputDto {
    @NotBlank
    public String userName;
    @NotBlank
    public String passWord;

    /*TODO Wy whould a user be able to set UserRole ??*/
    public UserRole userRole;
}
