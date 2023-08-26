package sem3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.cars.entity.Car;

public interface CarsRepository extends JpaRepository<Car,String> {
}
