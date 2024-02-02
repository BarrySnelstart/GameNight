package nl.novi.gamenight.Model.Game;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.springframework.web.bind.annotation.RestController;

@Entity (name = "epansions")
public class Expansion {
    @Id
    @GeneratedValue
    private Long expansionID;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private Game games;

}
