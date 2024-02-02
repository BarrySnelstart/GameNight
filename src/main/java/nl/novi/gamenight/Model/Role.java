package nl.novi.gamenight.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String userRole;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
