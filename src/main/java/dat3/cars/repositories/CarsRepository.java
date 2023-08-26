package dat3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dat3.cars.entity.Car;

public interface CarsRepository extends JpaRepository<Car,String> {
}
