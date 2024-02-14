package nl.novi.gamenight.Repository;

import nl.novi.gamenight.Model.Manual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository <Manual, Long> {
}
