package de.trio.imageshare.web.indexData.IndexModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Ist dafür da das man aktionen in der datenbank ausführen kann
 */
@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
