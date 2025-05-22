package igor.henrique.usecases.table;

import igor.henrique.entities.Table;
import igor.henrique.enums.TableStatus;
import igor.henrique.repositories.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTableUseCase {

    private final TableJpaRepository tableJpaRepository;

    public void delete(Integer tableNumber) {
        Table table = tableJpaRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não enontrada"));

        if (table.getTableStatus() == TableStatus.OCCUPIED) {
            throw new IllegalArgumentException("Não é possível deletar uma mesa ocupada");
        }

        tableJpaRepository.delete(table);
    }
}
