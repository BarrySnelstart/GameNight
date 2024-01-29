package nl.novi.gamenight.Repository;


import nl.novi.gamenight.Model.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
