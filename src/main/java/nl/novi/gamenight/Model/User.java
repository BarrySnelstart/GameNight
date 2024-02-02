package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Getter
@Setter


@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long userID;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToMany(mappedBy = "users")
    private List<Review> reviews;
}
