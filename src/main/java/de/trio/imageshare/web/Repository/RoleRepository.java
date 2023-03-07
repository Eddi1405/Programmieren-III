package de.trio.imageshare.web.Repository;

import de.trio.imageshare.web.indexData.IndexModel.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDaten, Integer> {
    Optional<RoleDaten> findByName(String name);
}