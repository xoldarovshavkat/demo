package uz.pdp.sololearnuzversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.sololearnuzversion.entity.Products;

import java.util.Optional;
@Repository
public interface ProductRespository extends JpaRepository<Products, Long> {
    @Override
    Optional<Products> findById(Long aLong);
}
