package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "expansions")
public class Expansion {
    @Id
    @GeneratedValue
    private Long expansionID;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private Game games;

    public Expansion(Long expansionID, Game games) {
        this.expansionID = expansionID;
        this.games = games;
    }

    public Expansion() {
    }
}
