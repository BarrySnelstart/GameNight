package nl.novi.gamenight.Repository;

import nl.novi.gamenight.Model.GameExpansion.GameExpansion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameExpansionRepository extends JpaRepository <GameExpansion, Long>{
}
