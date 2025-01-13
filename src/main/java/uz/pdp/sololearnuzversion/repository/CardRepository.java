package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.sololearnuzversion.entity.Card;

import java.util.List;
import java.util.Optional;
@Repository
public interface CardRepository extends JpaRepository<Card , Long> {
    List<Card> findByUserId(Long userId);
    List<Card> findByProductId(Long productId);
}
