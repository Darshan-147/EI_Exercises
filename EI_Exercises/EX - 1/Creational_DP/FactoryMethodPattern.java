// Scenario: A vehicle factory that produces Thar or RollsRoyce based on the input.

// Product Interface
interface Vehicle {
    void drive();
}

// Concrete Products
class Thar implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Thar.");
    }
}

class RollsRoyce implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a RollsRoyce.");
    }
}

// Factory Class
class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type.equalsIgnoreCase("Thar")) {
            return new Thar();
        } else if (type.equalsIgnoreCase("RollsRoyce")) {
            return new RollsRoyce();
        } else {
            throw new IllegalArgumentException("No vehicles found.");
        }
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        Vehicle thar = VehicleFactory.createVehicle("thar");
        thar.drive();

        Vehicle rollsroyce = VehicleFactory.createVehicle("rollsroyce");
        rollsroyce.drive();
    }
}
