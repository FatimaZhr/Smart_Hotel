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

@Controller
public class LoginController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam(required = false) String role,
                        HttpSession session,
                        Model model) {

        User user = userService.findByEmail(email);

        if (user == null ||
                !user.getPasswordHash().equals(password)) {
            model.addAttribute("error", "Email or password incorrect!");
            return "login";
        }

        // ✅ احفظ المستخدم فـ session
        session.setAttribute("loggedUser", user);

        if (user.getRole() == Role.MANAGER) {
            return "redirect:/dashboard";
        }
        if (user.getRole() == Role.RECEPTIONIST) {
            return "redirect:/reservations";
        }
        return "redirect:/rooms";
    }
}