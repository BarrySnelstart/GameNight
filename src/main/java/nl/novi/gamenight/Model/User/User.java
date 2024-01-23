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

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
