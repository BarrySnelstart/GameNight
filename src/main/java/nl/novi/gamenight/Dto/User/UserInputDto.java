package nl.novi.gamenight.Dto.User;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserInputDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
    public String[] roles;
    /*TODO Wy whould a user be able to set UserRole ??*/

}
