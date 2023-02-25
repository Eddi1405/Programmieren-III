package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}