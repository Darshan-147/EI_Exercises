/* Scenario: A weather station sends temperature updates to different displays 
(phone app, TV). */

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature);
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject (Weather Station)
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

// Concrete Observers (Phone Display, TV Display)
class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: temperature is now " + temperature + "°C");
    }
}

class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("TV Display: temperature is now " + temperature + "°C");
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        Observer phone = new PhoneDisplay();
        Observer tv = new TVDisplay();
        
        station.registerObserver(phone);
        station.registerObserver(tv);
        
        station.setTemperature(30.0f);
        station.setTemperature(25.0f);
    }
}
