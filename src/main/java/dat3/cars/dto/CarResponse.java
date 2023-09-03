package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)

public class CarResponse {
    private String car_model;
    private String car_brand;
    private double pricePrDay;
    private int max_discount;

    LocalDateTime created; //??

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Boolean approved;

    public CarResponse(Car c, boolean includeAll) {
        this.car_model = c.getCar_model();
        this.car_brand = c.getCar_brand();
        this.pricePrDay = c.getPricePrDay();
        this.max_discount = c.getMax_discount();




    }
}
