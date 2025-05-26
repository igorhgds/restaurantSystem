package igor.henrique.usecases.table;

import igor.henrique.entities.User;
import igor.henrique.repositories.TableJpaRepository;
import igor.henrique.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTableWaiterUseCase {

    private final TableJpaRepository tableJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public void updateTableWaiter(Integer tableNumber, Long waiterId) {
        var table = tableJpaRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new IllegalArgumentException("Mesa não encontrada"));

        var waiter = userJpaRepository.findById(waiterId)
                .orElseThrow(() -> new IllegalArgumentException("Garçom não encontrado"));

        table.setWaiter(waiter);
        tableJpaRepository.save(table);
    }
}
