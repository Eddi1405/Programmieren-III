package de.trio.imageshare.web.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import de.trio.imageshare.web.entities.UserDaten;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        UserDaten user = new UserDaten();
        user.setEmail("test@gmail.com");
        user.setPassword("test123456");
        user.setUsername("test");

        UserDaten savedUser = repo.save(user);

        UserDaten existUser = entityManager.find(UserDaten.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
