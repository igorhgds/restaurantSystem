package igor.henrique.repositories;

import igor.henrique.entities.Dish;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DishJpaRepository extends JpaRepository<Dish, UUID> {

    Optional<Dish> findByDishId(UUID dishId);

    Optional<Object> findByName(@NotBlank String name);
}
