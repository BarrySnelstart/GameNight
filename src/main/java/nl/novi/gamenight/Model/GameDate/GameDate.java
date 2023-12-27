package nl.novi.gamenight.Model.GameDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class GameDate {
    @Id
    @GeneratedValue
    int id;

    Date gameDate;
}
