package nl.novi.gamenight.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Logging {
    @Id
    @GeneratedValue
    Long logID;

    String username;

    String password;

    String message;

    LocalDateTime logDate;


}
