package nl.novi.gamenight.Repository;


import nl.novi.gamenight.Model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository <Review, Long>{
}
