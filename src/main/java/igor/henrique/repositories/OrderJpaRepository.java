package igor.henrique.repositories;

import igor.henrique.entities.Order;
import igor.henrique.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(Long orderId);

    @Query("""
    SELECT o FROM orders o
    WHERE (:tableNumber IS NULL OR o.table.tableNumber = :tableNumber)
      AND (:waiterName IS NULL OR LOWER(o.table.waiter.name) LIKE :waiterName)
      AND (:orderStatus IS NULL OR o.status = :orderStatus)
""")
    List<Order> findFiltered(
            @Param("tableNumber") Integer tableNumber,
            @Param("waiterName") String waiterName,
            @Param("orderStatus") OrderStatus orderStatus
    );


}
