package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.sololearnuzversion.entity.User;

import java.util.Optional;
@Repository
public interface UserInterface extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
