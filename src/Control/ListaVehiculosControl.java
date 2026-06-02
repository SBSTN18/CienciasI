package Control;

import modelo.Vehiculo;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;
import modelo.Enums.Estado;
import modelo.Enums.TipoVehiculo;

public class ListaVehiculosControl {

    private Lista<Vehiculo> vehiculos;

    public ListaVehiculosControl() {
        this.vehiculos = new Lista<>();
    }

    public void agregarVehiculo(TipoVehiculo tipo, String zona) {
        Vehiculo vehiculo = new Vehiculo(zona, tipo);
        vehiculos.agregar(vehiculo);
    }

    public Vehiculo buscarDisponible(TipoVehiculo tipo, String zona) {
        Nodo<Vehiculo> actual = vehiculos.getCabeza();
        while (actual != null) {
            Vehiculo v = actual.getDato();
            if (v.getEstado() == Estado.DISPONIBLE 
                && v.getTipo() == tipo 
                && v.getZona().equals(zona)) {
                return v;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Vehiculo[] obtenerTodos() {
        Vehiculo[] resultado = new Vehiculo[vehiculos.getTamaño()];
        Nodo<Vehiculo> actual = vehiculos.getCabeza();
        int i = 0;
        while (actual != null) {
            resultado[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public Vehiculo[] obtenerPorEstado(Estado estado) {
        // Primero cuenta cuántos hay con ese estado
        int total = 0;
        Nodo<Vehiculo> actual = vehiculos.getCabeza();
        while (actual != null) {
            if (actual.getDato().getEstado() == estado) total++;
            actual = actual.getSiguiente();
        }

        // Luego los agrega al arreglo
        Vehiculo[] resultado = new Vehiculo[total];
        actual = vehiculos.getCabeza();
        int i = 0;
        while (actual != null) {
            if (actual.getDato().getEstado() == estado) {
                resultado[i++] = actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public int getTotalVehiculos() {
        return vehiculos.getTamaño();
    }
}