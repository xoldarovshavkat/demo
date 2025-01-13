package uz.pdp.sololearnuzversion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.entity.User;
import uz.pdp.sololearnuzversion.repository.UserInterface;
import uz.pdp.sololearnuzversion.service.Userservie;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final Userservie userService;
    private final UserInterface userInterface;

    public UserController(Userservie userservie,
                          UserInterface userInterface) {
        this.userService = userservie;
        this.userInterface = userInterface;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        // Shart: Email takrorlanmasligi kerak
        if (userInterface.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        // Shart: Parol uzunligi kamida 8 ta belgidan iborat bo'lishi kerak
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long!");
        }

        // Shart: Telefon raqami kiritilgan bo'lsa, 10 ta raqamdan iborat bo'lishi kerak
        if (user.getPhone() != null && !user.getPhone().matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits long!");
        }

        // Shartlarga mos kelganda foydalanuvchini yaratish
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userInterface.save(user);
    }



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

