package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import da
import dat3.cars.repositories.CarsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    CarsRepository carsRepository;

    //dependencyInject
    public CarService(CarsRepository CarsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carsRepository.findAll();
        List<CarResponse> carsResponses = new ArrayList<>();
        for (Car c : cars) {
            CarResponse cr = new CarResponse();
            carsResponses.add(cr);
        }
        return carsResponses;

    }
    //    List<CarsResponse> responses = cars.stream().map((car -> new CarResponse(car,includeAll))).toList();

    public List<CarResponse> getCars() {
        List<Car> car = carsRepository.findAll();
        List<CarResponse> cars = new ArrayList<>();
        for (Car c : car) {
            CarResponse cr = CarResponse.builder()
                    .car_model(c.getCar_brand()) //mangler attributter for car
                    .car_brand(c.getCar_model())//mangler attributter for car
                    .build();
            cars.add(cr);
        }
        return cars;
    }

    public CarResponse getCarByModel(String model) {
        Car car = carsRepository.findById(car_model) .orElseThrow(() -> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Car not found"));
        return new MemberResponse(car, true);

    }

    public CarResponse addCar(CarRequest requestBody) {
        if (carsRepository.existsById(requestBody.getCar_model())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this model already exist");
        }

        //DTO car
        Car newCar = CarRequest.getCarEntity(requestBody);
        newCar = carsRepository.save(newCar);

        return new CarResponse(newCar, true);

    }

    public ResponseEntity<Boolean> editCar(CarRequest body, String car_model) {
        Car car = carsRepository.findById(car_model).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Car with this model exists"));
        if (!body.getCar_model().equals(car_model)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't change username");
        }
        car.setCar_brand(body.getCar_model());
        car.setCar_model(body.getCar_model());
        car.setPricePrDay(body.getPricePrDay());
        car.setMax_discount(body.getMax_discount());
        carsRepository.save(car);
        return ResponseEntity.ok(true);
    }

    /*
        public void setRankingFor(String username, int value) {
            Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                    (HttpStatus.BAD_REQUEST, "Members with this username does exist"));
            member.setRanking(value);
            memberRepository.save(member);
        }
    */
    public void setRankingForNoExistingUser(String username, int value) {
        Car car = carsRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Members with this username doesn't exist"));
        car.setRanking(value);
        carsRepository.save(car);

    }


    public ResponseEntity<Boolean> deleteMemberByUserName(String username) {
        Car car = carsRepository.findById(username).orElseThrow(() -> new ResponseStatusException
                (HttpStatus.BAD_REQUEST, "Member w this un doesn't exist"));
        carsRepository.delete(car);
        return ResponseEntity.ok(true);
    }


    private Car getMemberByUsername(String username) { //hvs ikke den finder member, smider den exception:
        return carsRepository.findById(username).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this username does not exist"));
    }
}