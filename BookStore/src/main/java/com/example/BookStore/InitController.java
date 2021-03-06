package com.example.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class InitController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/init")
    public String init() {
        // tries to find the user so that we only save the user if it does not exist
        User user = userRepository.findByUsername("d");
        if (user == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("123"));
            // svae the user with username user and an encoded value for 123 as password
            userRepository.save(user);
        }

        return "ok";
    }


}
