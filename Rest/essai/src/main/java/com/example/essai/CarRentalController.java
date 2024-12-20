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

    // Initialisation des voitures avec des dates de location existantes
    public CarRentalController() {
        // Création de la voiture 1
        Car car1 = new Car("11AA22", "Ferrari", 100);
        car1.addRentalPeriod(new Dates("2023-01-01", "2023-01-10")); // Période 1
        car1.addRentalPeriod(new Dates("2023-02-15", "2023-03-01")); // Période 2
        carInventory.put("11AA22", car1);

        // Création de la voiture 2
        Car car2 = new Car("33BB44", "Tesla", 200);
        car2.addRentalPeriod(new Dates("2023-05-01", "2023-06-01")); // Période 1
        carInventory.put("33BB44", car2);

        // Création de la voiture 3
        Car car3 = new Car("55CC66", "BMW", 150);
        car3.addRentalPeriod(new Dates("2023-07-10", "2023-07-20")); // Période 1
        car3.addRentalPeriod(new Dates("2023-08-01", "2023-08-10")); // Période 2
        carInventory.put("55CC66", car3);
    }

    // Obtenir les informations d'une voiture
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        Car car = carInventory.get(plateNumber);
        if (car == null) {
            throw new Exception("Car not found");
        }
        return car; // Retourne la voiture, qui inclut la liste des périodes de location
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
            if (dates != null) {
                car.addRentalPeriod(dates); // Ajoute la nouvelle période de location
            }
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
        return new ArrayList<>(carInventory.values()); // Retourne la liste des voitures avec toutes les périodes
    }
}
