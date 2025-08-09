package igor.henrique.dtos.dish.output;

import java.math.BigDecimal;
import java.util.UUID;

public record DishOutputDTO(
        UUID dishId,
        String name,
        String description,
        BigDecimal price
) {
}
