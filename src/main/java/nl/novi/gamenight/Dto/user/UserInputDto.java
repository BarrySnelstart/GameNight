package nl.novi.gamenight.Dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserInputDto {

    @NotBlank
    public String username;

    @NotBlank
    @Size(min = 6)
    public String password;
}
