package de.trio.imageshare.web.Service;

import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.entities.UserDaten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 * <p>
 * Die Methode findByUsername(String username) sucht in der Datenbank nach einem Benutzer mit dem angegebenen Benutzernamen und gibt ein Optional zurück,
 * das entweder einen gefundenen Benutzer oder null enthält.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveData(String password, String name, String email) {
        UserDaten data = new UserDaten();
        data.setPassword(password);
        data.setUsername(name);
        data.setEmail(email);
        userRepository.save(data);
    }


}
