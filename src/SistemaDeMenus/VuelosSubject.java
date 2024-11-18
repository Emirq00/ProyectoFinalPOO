package SistemaDeMenus;

import java.util.ArrayList;
import java.util.List;
import Cuentas.*;

public class VuelosSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<String> vuelos = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(boolean asientoDisponible) {
        for (Observer observer : observers) {
            observer.actualizar(asientoDisponible);
        }
    }

    public void agregarVuelo(String vuelo) {
        vuelos.add(vuelo);
        notifyObservers(false);
    }

    public void desocuparAsiento(String vuelo) {
        notifyObservers(true);
    }

    public List<String> obtenerVuelos() {
        return vuelos;
    }
}
