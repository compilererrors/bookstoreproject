package com.example.BookStore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class InitController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    private static final Map<Long, User> USERS_MAP = new HashMap<>();

    static {
        initDATA();
    }

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


    @GetMapping("/register")
    public String users(Model model) {
        List<com.example.BookStore.User> users = (List<com.example.BookStore.User>) model.addAttribute(userRepository.findAll());
        model.addAttribute(users);
        return "/userform";
    }

    @PostMapping("/register")
    public String registeruser
        // tries to find the user so that we only save the user if it does not exist
    (@RequestParam("user") com.example.BookStore.User user, HttpServletRequest request, Errors errors) {


        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setUsername(user.getUsername());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles("ADMIN");
        }

        // svae the user with username user and an encoded value for 123 as password
        userRepository.save(user);


        return "redirect:/";
    }

    private static void initDATA() {
        String encrytedPassword = "";
        /*

        User user = new User("hej", "ADMIN", encrytedPassword)

        User tom = new User(1L, "tom", "Tom", "Tom", //
                true, Gender.MALE, "tom@waltdisney.com", encrytedPassword, "US");

        User jerry = new User(2L, "jerry", "Jerry", "Jerry", //
                true, Gender.MALE, "jerry@waltdisney.com", encrytedPassword, "US");

        USERS_MAP.put(tom.getUserId(), tom);
        USERS_MAP.put(jerry.getUserId(), jerry);

         */
    }


    public Long getMaxUserId() {
        long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }

    //

    public User findAppUserByUserName(String userName) {
        Collection<User> users = USERS_MAP.values();
        for (User u : users) {
            if (u.getUsername().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    /*
    public User findAppUserByEmail(String email) {
        Collection<User> appUsers = USERS_MAP.values();
        for (User u : appUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }*/

    public List<User> getAppUsers() {
        List<User> list = new ArrayList<>();

        list.addAll(USERS_MAP.values());
        return list;
    }

    /*
    public User createAppUser(UserForm form) {
        Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.encoder.encode(form.getPassword());

        User user = new User(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);

        USERS_MAP.put(userId, user);
        return user;
    }*/

}


