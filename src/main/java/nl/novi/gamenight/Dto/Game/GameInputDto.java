package nl.novi.gamenight.Dto.Game;

import jakarta.validation.constraints.*;
import nl.novi.gamenight.Model.Game.Category;
import org.springframework.validation.annotation.Validated;

@Validated
public class GameInputDto {
    @NotBlank
    public String name;
    @NotBlank
    public String manufacturer;
    @NotNull
    @Min(value = 1,message = "Minimum of 1 player")
    public int minimumPlayers;
    @NotNull
    public int maximumPlayers;
    @NotNull
    @Digits(integer = 3, fraction = 0, message = "Please enter a valid number")
    public int age;
    @Digits(integer = 10, fraction = 0, message = "Please enter a valid number")
    public int minimumDuration;
    @Digits(integer = 10, fraction = 0, message = "Please enter a valid number")
    public int averageDuration;
    @NotNull
    public Category category;
    @NotNull
    public String type;

    //public String averageStarValue;

}
