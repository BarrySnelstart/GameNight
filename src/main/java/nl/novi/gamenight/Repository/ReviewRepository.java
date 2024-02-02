package nl.novi.gamenight.Repository;


import nl.novi.gamenight.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
