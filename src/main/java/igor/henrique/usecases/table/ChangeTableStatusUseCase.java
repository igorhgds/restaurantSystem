package igor.henrique.usecases.table;

import igor.henrique.entities.Table;
import igor.henrique.enums.TableStatus;
import igor.henrique.repositories.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeTableStatusUseCase {

    private final TableJpaRepository tableJpaRepository;

    public void changeStatus(Integer tableNumber, TableStatus newStatus){
        Table table = tableJpaRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));

        table.setTableStatus(newStatus);
        tableJpaRepository.save(table);
    }
}
