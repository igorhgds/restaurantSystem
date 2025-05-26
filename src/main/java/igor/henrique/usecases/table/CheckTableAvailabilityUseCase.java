package igor.henrique.usecases.table;

import igor.henrique.enums.TableStatus;
import igor.henrique.repositories.TableJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckTableAvailabilityUseCase {

    private final TableJpaRepository tableJpaRepository;

    public boolean isAvailable(Integer tableNumber){
        return tableJpaRepository.findByTableNumber(tableNumber)
                .map(table -> table.getTableStatus() == TableStatus.AVAILABLE)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));
    }

}
