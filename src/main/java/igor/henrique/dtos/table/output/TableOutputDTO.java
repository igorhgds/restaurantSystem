package igor.henrique.dtos.table.output;

import igor.henrique.enums.TableStatus;

public record TableOutputDTO(
        Long id,
        Integer tableNumber,
        TableStatus tableStatus,
        String waiterName
) {
}
