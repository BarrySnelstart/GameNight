package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manuals")
public class Manual {
    @Id
    @GeneratedValue
    private Long manualID;
    private String name;
    private String type;

    @Lob
    private byte[] data;


    @OneToOne
    @JoinColumn(name = "gameid", referencedColumnName = "gameid")
    private Game game;
}
