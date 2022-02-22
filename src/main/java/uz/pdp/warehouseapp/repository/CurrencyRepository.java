package uz.pdp.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseapp.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    Long countById(Integer id);
}
