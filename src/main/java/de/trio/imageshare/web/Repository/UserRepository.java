package de.trio.imageshare.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserDaten, Long> {
    Optional<UserDaten> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}