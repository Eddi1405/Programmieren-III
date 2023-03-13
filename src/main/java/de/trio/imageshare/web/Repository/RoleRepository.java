package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.RoleDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Schnittstelle für ein JPA Repository die Methode sucht in der Datenbank nach einer Rolle und gibt diese als optional Objekt zurück.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleDaten, Integer> {
    Optional<RoleDaten> findByName(String name);
}