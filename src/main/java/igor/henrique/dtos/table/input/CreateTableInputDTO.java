package igor.henrique.dtos.table.input;

import igor.henrique.enums.TableStatus;
import jakarta.validation.constraints.NotNull;

public record CreateTableInputDTO(

        @NotNull
        int tableNumber,

        @NotNull
        TableStatus tableStatus,

        @NotNull
        Long waiterId
) {
}
