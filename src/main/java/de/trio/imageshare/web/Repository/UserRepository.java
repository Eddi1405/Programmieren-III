package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.UserDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 *
 * Die Methode findByUsername(String username) sucht in der Datenbank nach einem Benutzer mit dem angegebenen Benutzernamen und gibt ein Optional zurück,
 * das entweder einen gefundenen Benutzer oder null enthält.
 *
 * Die Methoden existsByUsername(String username) und existsByEmail(String email) prüfen,
 * ob ein Benutzer mit dem angegebenen Benutzernamen oder der angegebenen E-Mail-Adresse in der Datenbank vorhanden ist und geben true zurück,
 * wenn ja, andernfalls false.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDaten, Long> {
    Optional<UserDaten> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}