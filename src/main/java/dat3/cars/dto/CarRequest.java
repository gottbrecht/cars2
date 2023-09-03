package dat3.cars.dto;


import dat3.cars.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CarRequest {
    private String car_brand;
    private String car_model;
    private int pricePrDay;
    private int max_discount;

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.car_brand, c.car_model);
    }

    public CarRequest(Car c) {
        this.car_brand = c.getCar_brand();
        this.car_model = c.getCar_model();
    }
}
