package igor.henrique.dtos.dish.output;

import java.math.BigDecimal;

public record DishOutputDTO(
        Long dishId,
        String name,
        String description,
        BigDecimal price
) {
}
