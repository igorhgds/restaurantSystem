package igor.henrique.usecases.table;

import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.mappers.table.TableStructMapper;
import igor.henrique.repositories.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTablesUseCase {

    private final TableJpaRepository tableJpaRepository;
    private final TableStructMapper tableStructMapper;

    public List<TableOutputDTO> execute() {
        return tableJpaRepository.findAll()
                .stream()
                .map(tableStructMapper::toTableOutputDTO)
                .toList();
    }

}
