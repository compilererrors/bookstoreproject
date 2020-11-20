package com.example.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public String registeruser
        // tries to find the user so that we only save the user if it does not exist
    (@ModelAttribute User user, HttpServletRequest request, Errors errors) {


        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setUsername(user.getUsername());
            user.setPassword(encoder.encode(user.getPassword()));
            //user.setRoles("ADMIN");
        }

        // svae the user with username user and an encoded value for 123 as password
        userRepository.save(user);


        return "redirect:/";
    }
}
