package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}