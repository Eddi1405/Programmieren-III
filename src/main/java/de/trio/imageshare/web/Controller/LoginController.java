package de.trio.imageshare.web.Controller;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
}
