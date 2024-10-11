package com.example.essai;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class CarRentalController {

    private Map<String, Car> carInventory = new HashMap<>();

    // Initialisation des voitures
    public CarRentalController() {
        carInventory.put("11AA22", new Car("11AA22", "Ferrari", 100));
        carInventory.put("33BB44", new Car("33BB44", "Tesla", 200));
        carInventory.put("55CC66", new Car("55CC66", "BMW", 150));
    }

    // Obtenir les informations d'une voiture
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        Car car = carInventory.get(plateNumber);
        if (car == null) {
            throw new Exception("Car not found");
        }
        return car;
    }

    // Louer ou rendre une voiture
    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrReturnCar(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent") boolean rent,
            @RequestBody(required = false) Dates dates) throws Exception {

        Car car = carInventory.get(plateNumber);
        if (car == null) {
            throw new Exception("Car not found");
        }

        if (rent) {
            if (car.isRented()) {
                throw new Exception("Car is already rented");
            }
            car.setRented(true);
            System.out.println("Car rented from " + dates.getBegin() + " to " + dates.getEnd());
        } else {
            if (!car.isRented()) {
                throw new Exception("Car is not currently rented");
            }
            car.setRented(false);
            System.out.println("Car returned");
        }
    }

    // Obtenir la liste de toutes les voitures
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listOfCars() {
        return new ArrayList<>(carInventory.values());
    }

    @GetMapping("/hell")
    @ResponseStatus(HttpStatus.OK)
    public String hell() {
        return "hell";
    }
}
