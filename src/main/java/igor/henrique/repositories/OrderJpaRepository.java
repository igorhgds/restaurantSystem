package igor.henrique.repositories;

import igor.henrique.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
