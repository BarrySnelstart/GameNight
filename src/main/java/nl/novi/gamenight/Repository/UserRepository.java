package nl.novi.gamenight.Repository;


import nl.novi.gamenight.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
