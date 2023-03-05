package de.trio.imageshare.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {

    @GetMapping(value = "/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }


}
