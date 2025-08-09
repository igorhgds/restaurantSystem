package igor.henrique.repositories;

import igor.henrique.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TableJpaRepository extends JpaRepository<Table, UUID> {

    Optional<Table> findByTableNumber(Integer tableNumber);

}
