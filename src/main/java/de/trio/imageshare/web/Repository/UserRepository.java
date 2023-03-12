package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.UserDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserDaten, Long> {
    Optional<UserDaten> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}