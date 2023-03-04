package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<UserDaten, Long> {
    //Optional<UserDaten> findByUsername(String username);
    //Boolean existsByUsername(String username);
}