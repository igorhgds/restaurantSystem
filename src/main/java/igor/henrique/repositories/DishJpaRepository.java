package igor.henrique.repositories;

import igor.henrique.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishJpaRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findByDishId(long dishId);
}
