package igor.henrique.usecases.table;

import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.entities.Table;
import igor.henrique.entities.User;
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

    public void createTable(CreateTableInputDTO dto){

        User waiter = userJpaRepository.findById(dto.waiterId())
                .orElseThrow(() -> new IllegalArgumentException("Garçon não encontrado"));

        Table table = tableStructMapper.toEntity(dto);
        table.setWaiter(waiter);
        table.setCreatedAt(LocalDateTime.now());

        tableJpaRepository.save(table);
    }
}

