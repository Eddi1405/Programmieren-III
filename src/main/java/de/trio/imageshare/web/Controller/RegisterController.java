package de.trio.imageshare.web.Controller;
import de.trio.imageshare.web.Repository.UserDaten;
import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.dto.RegisterDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
public class RegisterController {



/*
    public String addData(@RequestParam long id, @RequestParam String username, @RequestParam String password,@RequestParam String password2, @RequestParam String email)throws IOException {

        UserDaten user = new UserDaten();

        user.setUsername((registerDto.getUsername()));
        user.setPassword(registerDto.getPassword());
        user.setPassword(registerDto.getPassword2());
        user.setEmail(registerDto.getEmail());
        user.setRole(registerDto.getRole());
        userRepository.save(user);

        return "redirect:/login";
    }*/

}
