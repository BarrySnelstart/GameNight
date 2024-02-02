package nl.novi.gamenight.Repository;


import nl.novi.gamenight.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
