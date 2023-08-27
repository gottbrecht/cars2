package dat3.cars.repository;

import dat3.cars.entity.Car;
import dat3.cars.repositories.CarsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

public class CarsRepositoryTest {

    @Autowired
    CarsRepository carsRepository;
    boolean isInitialized = false;
    @BeforeEach
    void setUp() {
        if(!isInitialized) {
            carsRepository.save(new Car("BMW", "3 series"));
            carsRepository.save(new Car("Porche", "911"));
            isInitialized = true;
        }
    }
    @Test
    public void testAll(){
        Long count = carsRepository.count();
        assertEquals(2,count);
    }

    @Test
    public void deleteAll(){
        carsRepository.deleteAll();
        assertEquals(0,carsRepository.count());
    }

}
