package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.sololearnuzversion.entity.Payment;

import java.util.List;
import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
        @Override
    Optional<Payment> findById(Long aLong);

    List<Payment> findByOrderId(Long orderId);
}
