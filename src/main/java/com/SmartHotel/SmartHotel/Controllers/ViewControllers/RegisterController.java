package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.Role;
import com.SmartHotel.SmartHotel.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    // GET /register
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // POST /register
    @PostMapping("/register")
    public String register(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (userService.findByEmail(email) != null) {
            model.addAttribute("error",
                    "Email already exists!");
            return "register";
        }


        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .passwordHash(password)
                .role(Role.GUEST)
                .createdAt(LocalDateTime.now())
                .build();

         User savedUser = userService.createUser(user);

        session.setAttribute("loggedUser", savedUser);

        return "redirect:/rooms";
    }
}
