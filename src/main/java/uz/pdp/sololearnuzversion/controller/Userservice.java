package uz.pdp.sololearnuzversion.controller;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link uz.pdp.sololearnuzversion.entity.User}
 */
@Value
public class Userservice implements Serializable {
    Long id;
    String name;
    String email;
    String password;
    String phone;
    String address;
    String role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}