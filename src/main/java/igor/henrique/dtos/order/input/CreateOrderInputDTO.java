package igor.henrique.dtos.order.input;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateOrderInputDTO(
        @NotNull(message = "O número da mesa é obrigatório.")
        Integer tableNumber,

        @NotNull(message = "O ID do garçom é obrigatório.")
        UUID waiterId
) {
}
