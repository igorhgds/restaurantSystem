package igor.henrique.dtos.table.output;

import igor.henrique.enums.TableStatus;

public record TableOutputDTO(
        Long tableId,
        Integer tableNumber,
        TableStatus tableStatus,
        String waiterName
) {
}
