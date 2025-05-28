package igor.henrique.rest.controllers;

import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.OrderItem;
import igor.henrique.usecases.order_item.CreateOrderItemUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-items")
@RequiredArgsConstructor
@Tag(name = "OrderItemController", description = "Operações relacionadas aos itens dos pedidos do restaurante")
public class OrderItemController {

    private final CreateOrderItemUseCase createOrderItemUseCase;

    @PostMapping
    public ResponseEntity<OrderItemOutputDTO> createOrderItem(@RequestBody CreateOrderItemInputDTO dto) {
        OrderItemOutputDTO response = createOrderItemUseCase.createOrderItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
