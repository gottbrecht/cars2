package dat3.cars.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;


//Database
@Entity
@Table

//Lombock
@Getter
@Setter
@NoArgsConstructor

public class Car extends AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = true, name = "brand", length = 50)
    private String car_brand;
    @Column(nullable = true, name = "Model", length = 60)
    private String car_model;

    @Column(name = "rental_price_day", nullable = true)
    private double pricePrDay;

    @Column(name = "max_discount", nullable = true)
    private int max_discount;

    public Car(String car_brand, String car_model) {
        this.car_brand = car_brand;
        this.car_model = car_model;
    }

}
