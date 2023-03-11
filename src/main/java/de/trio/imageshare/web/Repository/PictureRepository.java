package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Ist dafür da das man aktionen in der datenbank ausführen kann
 */
@Repository
public interface PictureRepository extends JpaRepository<PictureDaten, Integer> {
    Optional<PictureDaten> findBybildname(String bildname);

    boolean existsBybildname(String bildname);
}
