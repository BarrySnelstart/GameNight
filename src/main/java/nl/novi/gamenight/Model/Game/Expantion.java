package nl.novi.gamenight.Model.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "expantions")
public class Expantion {

    @Id
    @GeneratedValue
    private int id;
    private String expantionName;


}
