package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.entities.PictureDaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Ist dafür da das man aktionen in der Datenbank ausführen kann
 */
@Repository
public interface PictureRepository extends JpaRepository<PictureDaten, Integer> {
    Optional<PictureDaten> findBybildname(String bildname);

    List<PictureDaten> findBybenutzer(String benutzer);

    boolean existsBybildname(String bildname);

    @Query(value = "SELECT * FROM data WHERE kategorie = ?1 AND benutzer = ?2", nativeQuery = true)
    List<PictureDaten> findByKategorie(String param1, String param2);

    @Query(value = "SELECT * FROM data WHERE title = ?1 AND benutzer = ?2", nativeQuery = true)
    List<PictureDaten> findByTitle(String param1, String param2);

    @Query(value = "SELECT * FROM data WHERE title = ?1 AND kategorie = ?2 AND benutzer = ?3", nativeQuery = true)
    List<PictureDaten> findByKategorieTitle(String param1, String param2, String param3);

    @Query(value = "SELECT * FROM data WHERE datum >= ?1 AND datum <= ?2 AND benutzer = ?3", nativeQuery = true)
    List<PictureDaten> findByDatum(String param1, String param2, String param3);

    @Query(value = "SELECT * FROM data WHERE kategorie = ?1 AND datum >= ?2 AND datum <= ?3 AND benutzer = ?4", nativeQuery = true)
    List<PictureDaten> findByKategorieDatum(String param1, String param2, String param3, String param4);

    @Query(value = "SELECT * FROM data WHERE title = ?1 AND datum >= ?2 AND datum <= ?3 AND benutzer = ?4", nativeQuery = true)
    List<PictureDaten> findByTitleDatum(String param1, String param2, String param3, String param4);

    @Query(value = "SELECT * FROM data WHERE kategorie = ?1 AND title = ?2 AND datum >= ?3 AND datum <= ?4 AND benutzer = ?5", nativeQuery = true)
    List<PictureDaten> findByKategorieTitleDatum(String param1, String param2, String param3, String param4, String param5);
}
