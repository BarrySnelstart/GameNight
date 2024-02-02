package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "epansions")
public class Expansion {
    @Id
    @GeneratedValue
    private Long expansionID;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private Game games;

}
