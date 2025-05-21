package igor.henrique.usecases.table;

import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.entities.Table;
import igor.henrique.entities.User;
import igor.henrique.enums.TableStatus;
import igor.henrique.mappers.table.TableStructMapper;
import igor.henrique.repositories.TableJpaRepository;
import igor.henrique.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateTableUseCase{

    private final TableJpaRepository tableJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final TableStructMapper tableStructMapper;

    public TableOutputDTO createTable(CreateTableInputDTO dto){

        User waiter = userJpaRepository.findById(dto.waiterId())
                .orElseThrow(() -> new IllegalArgumentException("Garçon não encontrado"));

        Table table = tableStructMapper.toEntity(dto);
        table.setWaiter(waiter);
        table.setCreatedAt(LocalDateTime.now());
        table.setTableStatus(TableStatus.AVAILABLE);

        Table saved = tableJpaRepository.save(table);
        return tableStructMapper.toTableOutputDTO(saved);
    }
}

