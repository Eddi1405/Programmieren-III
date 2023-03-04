package de.trio.imageshare.web.dto;

import de.trio.imageshare.web.Repository.UserDaten;
import de.trio.imageshare.web.Repository.UserRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String password2;
    private String email;
    private String role = "user";

    UserRepository userRepository;

    public String addData(@RequestParam long id, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email)throws IOException {

        UserDaten user = new UserDaten();

        user.setUsername(username);
        user.setPassword(password);
        user.setPassword(password2);
        user.setEmail(email);
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/login";
    }
}