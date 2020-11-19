package com.example.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/init")
    public String init(){
        // tries to find the user so that we only save the user if it does not exist
        User user = userRepository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("123"));
            // svae the user with username user and an encoded value for 123 as password
            userRepository.save(user);
        }

        return "ok";
    }

    @PostMapping("/registerUser")
    public String registeruser(@RequestParam String username, @RequestParam String password){
        // tries to find the user so that we only save the user if it does not exist

        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            user.setRoles("ADMIN");
            // svae the user with username user and an encoded value for 123 as password
            userRepository.save(user);
        }

        return "redirect:/";
    }
}
