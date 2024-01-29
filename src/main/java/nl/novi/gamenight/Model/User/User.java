package nl.novi.gamenight.Model.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter


@Entity (name = "users")
public class User {
    @Id
    @GeneratedValue
    private long userID;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
