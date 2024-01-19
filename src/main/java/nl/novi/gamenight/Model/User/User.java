package nl.novi.gamenight.Model.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


@Entity (name = "users")
public class User {
    @Id
    @GeneratedValue
    private long userID;

    private String userName;

    /*TODO This should be encrypted */
    private String passWord;

    /*TODO Wy whould a user be able to set UserRole ??*/
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
