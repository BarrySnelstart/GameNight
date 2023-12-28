package nl.novi.gamenight.Repository;

import nl.novi.gamenight.Model.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
