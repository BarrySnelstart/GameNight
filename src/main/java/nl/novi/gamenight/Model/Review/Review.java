package nl.novi.gamenight.Model.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue
    long id;
    String review;
    int startValue; // Min 1 max 5
}
