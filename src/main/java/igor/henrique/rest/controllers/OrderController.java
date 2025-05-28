package igor.henrique.rest.controllers;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.input.ListOrdersFilterInputDTO;
import igor.henrique.dtos.order.output.OrderDetailsOutputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.usecases.order.AddItemToOrderUseCase;
import igor.henrique.usecases.order.CloseOrderUseCase;
import igor.henrique.usecases.order.CreateOrderUseCase;
import igor.henrique.usecases.order.GetOrderDetailsUseCase;
import igor.henrique.usecases.order.ListOrderItemByOrderIdUseCase;
import igor.henrique.usecases.order.ListOrdersUseCase;
import igor.henrique.usecases.order.RemoveItemFromOrderUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "OrderController", description = "Operações relacionadas aos pedidos do restaurante")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final CloseOrderUseCase closeOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final AddItemToOrderUseCase addItemToOrderUseCase;
    private final RemoveItemFromOrderUseCase removeItemFromOrderUseCase;
    private final ListOrderItemByOrderIdUseCase listOrderItemByOrderIdUseCase;
    private final GetOrderDetailsUseCase getOrderDetailsUseCase;

    @PostMapping
    public ResponseEntity<OrderOutputDTO> createOrder(@RequestBody CreateOrderInputDTO dto) {
        OrderOutputDTO response = createOrderUseCase.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> closeOrder(@PathVariable Long id) {
        closeOrderUseCase.closeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDTO>> listOrders(
            @RequestParam(required = false) Integer tableNumber,
            @RequestParam(required = false) String waiterName,
            @RequestParam(required = false) String orderStatus
    ) {
        ListOrdersFilterInputDTO filter = new ListOrdersFilterInputDTO(tableNumber, waiterName, orderStatus);
        List<OrderOutputDTO> result = listOrdersUseCase.listOrders(filter);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItemOutputDTO> addItemToOrder(
            @PathVariable Long orderId,
            @RequestBody @Valid CreateOrderItemInputDTO dto) {

        CreateOrderItemInputDTO fixedDto = new CreateOrderItemInputDTO(
                orderId,
                dto.dishId(),
                dto.quantity(),
                dto.unitPrice()
        );

        OrderItemOutputDTO result = addItemToOrderUseCase.addItem(fixedDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public ResponseEntity<Void> removeItemFromOrder(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId) {

        removeItemFromOrderUseCase.removeItem(orderId, orderItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemOutputDTO>> listItems(@PathVariable Long orderId) {
        List<OrderItemOutputDTO> items = listOrderItemByOrderIdUseCase.listItems(orderId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailsOutputDTO> getOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok(getOrderDetailsUseCase.execute(orderId));
    }

}
