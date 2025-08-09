package igor.henrique.rest.specs;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "5. OrderItem")
public interface OrderItemControllerSpecs {

    @Operation(summary = "Adiciona um item ao pedido",
            description = "Cria e adiciona um item (prato) a um pedido existente.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Item criado e adicionado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Pedido ou prato não encontrados")
            })
    @PostMapping
    ResponseEntity<OrderItemOutputDTO> createOrderItem(@RequestBody @Valid CreateOrderItemInputDTO dto);
}
