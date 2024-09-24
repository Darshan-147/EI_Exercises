import java.util.*;

// Device Interface
interface SmartDevice {
    void turnOn();
    void turnOff();
    void schedule(String timeOn, String timeOff);
    String getStatus();
    int getId();
}

// Light Class
class Light implements SmartDevice {
    private int id;
    private boolean isOn;
    private String scheduledOn;
    private String scheduledOff;

    public Light(int id) {
        this.id = id;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        System.out.println("Light " + id + " turned on.");
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        System.out.println("Light " + id + " turned off.");
    }

    @Override
    public void schedule(String timeOn, String timeOff) {
        this.scheduledOn = timeOn;
        this.scheduledOff = timeOff;
        System.out.println("Light " + id + " scheduled on at " + timeOn + " and off at " + timeOff);
    }

    @Override
    public String getStatus() {
        return "Light " + id + " is " + (isOn ? "On" : "Off");
    }

    @Override
    public int getId() {
        return this.id;
    }
}

// Thermostat Class
class Thermostat implements SmartDevice {
    private int id;
    private int temperature;
    private String scheduledOn;
    private String scheduledOff;

    public Thermostat(int id) {
        this.id = id;
        this.temperature = 70; // Default temperature
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat " + id + " set to " + temperature + " degrees.");
    }

    @Override
    public void turnOn() {
        System.out.println("Thermostat " + id + " turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat " + id + " turned off.");
    }

    @Override
    public void schedule(String timeOn, String timeOff) {
        this.scheduledOn = timeOn;
        this.scheduledOff = timeOff;
        System.out.println("Thermostat " + id + " scheduled on at " + timeOn + " and off at " + timeOff);
    }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set at " + temperature + " degrees.";
    }

    @Override
    public int getId() {
        return this.id;
    }
}

// Factory Method for Smart Devices
class SmartDeviceFactory {
    public static SmartDevice createDevice(String type, int id) {
        if (type.equalsIgnoreCase("light")) {
            return new Light(id);
        } else if (type.equalsIgnoreCase("thermostat")) {
            return new Thermostat(id);
        } else {
            throw new IllegalArgumentException("Invalid device type.");
        }
    }
}

// Observer Pattern
interface HomeObserver {
    void update(int temperature);
}

// HomeHub Class (Subject in Observer Pattern)
class HomeHub {
    private List<SmartDevice> devices = new ArrayList<>();
    private List<HomeObserver> observers = new ArrayList<>();

    public void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println("Added device: " + device.getClass().getSimpleName() + " with ID: " + device.getId());
    }

    public void removeDevice(int id) {
        devices.removeIf(device -> device.getId() == id);
        System.out.println("Removed device with ID: " + id);
    }

    public void controlDevice(int id, boolean turnOn) {
        for (SmartDevice device : devices) {
            if (device.getId() == id) {
                if (turnOn) {
                    device.turnOn();
                } else {
                    device.turnOff();
                }
                return;
            }
        }
        System.out.println("Device with ID: " + id + " not found.");
    }

    public void notifyObservers(int temperature) {
        for (HomeObserver observer : observers) {
            observer.update(temperature);
        }
    }

    public void registerObserver(HomeObserver observer) {
        observers.add(observer);
    }

    public void deregisterObserver(HomeObserver observer) {
        observers.remove(observer);
    }

    public void setTemperature(int temperature) {
        System.out.println("Temperature set to " + temperature);
        notifyObservers(temperature);
    }

    public void showDeviceStatus() {
        for (SmartDevice device : devices) {
            System.out.println(device.getStatus());
        }
    }
}

// Main Class for Testing
public class SmartHomeSystem {
    public static void main(String[] args) {
        HomeHub hub = new HomeHub();

        SmartDevice light1 = SmartDeviceFactory.createDevice("light", 1);
        SmartDevice thermostat1 = SmartDeviceFactory.createDevice("thermostat", 2);

        hub.addDevice(light1);
        hub.addDevice(thermostat1);

        // Turning on light
        hub.controlDevice(1, true);

        // Scheduling thermostat
        thermostat1.schedule("06:00", "08:00");

        // Change temperature in the home
        hub.setTemperature(75);

        // Show all device statuses
        hub.showDeviceStatus();
    }
}
