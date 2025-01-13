package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.sololearnuzversion.entity.Order;

import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long aLong);
}
