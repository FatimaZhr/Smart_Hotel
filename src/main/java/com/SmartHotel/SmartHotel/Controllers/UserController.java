package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    // GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET /api/users/1
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // POST /api/users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // DELETE /api/users/1
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
