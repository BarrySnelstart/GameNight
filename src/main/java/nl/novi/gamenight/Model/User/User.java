package nl.novi.gamenight.Model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


@Entity (name = "users")
public class User {
    @Id
    @GeneratedValue
    private long Id;

    private String userName;
    private String passWord;
    private UserRole userRole;

}
