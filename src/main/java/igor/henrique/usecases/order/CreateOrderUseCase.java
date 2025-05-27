package igor.henrique.usecases.order;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.entities.Order;
import igor.henrique.entities.Table;
import igor.henrique.entities.User;
import igor.henrique.enums.OrderStatus;
import igor.henrique.enums.TableStatus;
import igor.henrique.mappers.order.OrderStructMapper;
import igor.henrique.repositories.OrderJpaRepository;
import igor.henrique.repositories.TableJpaRepository;
import igor.henrique.repositories.UserJpaRepository;
import igor.henrique.usecases.table.CheckTableAvailabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final TableJpaRepository tableJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CheckTableAvailabilityUseCase checkTableAvailabilityUseCase;
    private final OrderStructMapper orderStructMapper;

    @Transactional
    public OrderOutputDTO createOrder(CreateOrderInputDTO dto) {
        boolean isTableAvailable = checkTableAvailabilityUseCase.isAvailable(dto.tableNumber());
        if (!isTableAvailable) {
            throw new IllegalArgumentException("Mesa já está com pedido em andamento.");
        }

        Table table = tableJpaRepository.findByTableNumber(dto.tableNumber())
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));

        User waiter = userJpaRepository.findById(dto.waiterId())
                .orElseThrow(() -> new IllegalArgumentException("Garçom não encontrado"));

        Order order = orderStructMapper.toEntity(dto);
        order.setTable(table);
        order.setWaiter(waiter);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(BigDecimal.ZERO);

        table.setTableStatus(TableStatus.OCCUPIED);
        table.setWaiter(waiter);
        tableJpaRepository.save(table);

        return orderStructMapper.toOrderOutputDTO(orderJpaRepository.save(order));
    }
}
