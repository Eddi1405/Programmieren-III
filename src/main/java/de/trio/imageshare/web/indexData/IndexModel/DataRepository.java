package de.trio.imageshare.web.indexData.IndexModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Ist dafür da das man aktionen in der datenbank ausführen kann
 */
@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Optional<Data> findBybildname(String bildname);
    public boolean existsBybildname(String bildname);
}
