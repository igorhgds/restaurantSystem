package igor.henrique.mappers.table;

import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.entities.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableStructMapper {

    @Mapping(target = "tableId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "waiter", ignore = true)
    @Mapping(target = "tableStatus", ignore = true)
    Table toEntity(CreateTableInputDTO dto);

    @Mapping(source = "waiter.name", target = "waiterName")
    TableOutputDTO toTableOutputDTO(Table table);
}
