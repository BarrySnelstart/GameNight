package nl.novi.gamenight.Dto.Game;

import jakarta.validation.constraints.*;
import nl.novi.gamenight.Model.Category;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Validated

public class GameInputDto {
    @NotBlank(message = "Game name cannot be empty or null")
    public String name;
    @NotBlank(message = "Manufacturer cannot be empty or null")
    public String manufacturer;

    @Min(value = 1, message = "Minimum of 1 player")
    public int minimumPlayers;

    @Min(value = 1, message = "Minimum of 1 player")
    @Digits(integer = 3, fraction = 0, message = "Can only contain digits")
    public int maximumPlayers;
    @Range(min = 1, max = 100, message = "Must be between 1 and 100")
    public int age;
    @Min(value = 1, message = "must be a number and higher then 1")
    public int minimumDuration;
    @Min(value = 1, message = "must be a number and higher then 1")
    public int averageDuration;

    // TO-DO Validation on Enum
    public Category category;
    @NotBlank(message = "Type cannot be empty or null")
    public String type;

    //public String averageStarValue;

}
