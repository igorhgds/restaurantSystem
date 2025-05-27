package igor.henrique.repositories;


import igor.henrique.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByOrderItemId(Long Id);
}
