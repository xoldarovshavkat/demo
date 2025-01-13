package uz.pdp.sololearnuzversion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.User;
import uz.pdp.sololearnuzversion.repository.UserInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Userservie {
    private UserInterface userRepository;

    public void UserService(UserInterface userRepository) {
        this.userRepository = userRepository;
    }

    public Userservie(UserInterface userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // Shart: Rol faqat "admin" yoki "user" bo'lishi kerak
        if (!user.getRole().equals("admin") && !user.getRole().equals("user")) {
            throw new IllegalArgumentException("Role must be either 'admin' or 'user'!");
        }

// Shart: Manzil bo'sh bo'lmasligi kerak (agar talab qilinsa)
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty!");
        }

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update User
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setPhone(userDetails.getPhone());
            user.setAddress(userDetails.getAddress());
            user.setRole(userDetails.getRole());
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Foydalanuvchini ro'yxatdan o'tkazish
     *
     * @param user Ro'yxatdan o'tayotgan foydalanuvchi ma'lumotlari
     * @return Saqlangan foydalanuvchi
     */
    public User registerUser(User user) {
        // Parolni hashlash
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Foydalanuvchini saqlash
        return userRepository.save(user);
    }

    /**
     * Foydalanuvchini email orqali qidirish
     *
     * @param email Foydalanuvchi emaili
     * @return Foydalanuvchi
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
