package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.entities.UserDaten;
import de.trio.imageshare.web.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDaten());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(UserDaten user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(!userRepo.existsByUsername(user.getUsername())&& !userRepo.existsByEmail(user.getEmail())){
            userRepo.save(user);
            return "register_success";

        }else{
            return "register_error";
        }

    }
}
