package igor.henrique.usecases.table;

import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.mappers.table.TableStructMapper;
import igor.henrique.repositories.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindTableByNumberTableUseCase {

    private final TableJpaRepository tableJpaRepository;
    private final TableStructMapper tableStructMapper;

    public TableOutputDTO getTable(Integer tableNumber){
        return tableJpaRepository.findByTableNumber(tableNumber)
                .map(tableStructMapper::toTableOutputDTO)
                .orElseThrow(() -> new IllegalArgumentException("Mesa nº " + tableNumber + " não encontrada"));
    }
}
