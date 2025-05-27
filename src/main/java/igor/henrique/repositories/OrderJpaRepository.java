package igor.henrique.repositories;

import igor.henrique.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

    @Query("""
        SELECT o FROM Order o
        WHERE (:tableNumber IS NULL OR o.table.tableNumber = :tableNumber)
          AND (:waiterName IS NULL OR LOWER(o.table.waiter.name) LIKE LOWER(CONCAT('%', :waiterName, '%')))
          AND (:orderStatus IS NULL OR o.orderStatus = igor.henrique.entities.enums.OrderStatus.valueOf(:orderStatus))
    """)
    List<Order> findFiltered(@Param("tableNumber") Integer tableNumber,
                             @Param("waiterName") String waiterName,
                             @Param("orderStatus") String orderStatus);
}
