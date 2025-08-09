package igor.henrique.repositories;


import igor.henrique.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, UUID> {

    Optional<OrderItem> findByOrderItemId(UUID Id);
}
