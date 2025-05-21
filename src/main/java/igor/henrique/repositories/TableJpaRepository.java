package igor.henrique.repositories;

import igor.henrique.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableJpaRepository extends JpaRepository<Table, Long> {

    Optional<Table> findByTableNumber(int tableNumber);

}
