package Control;

import modelo.Vehiculo;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;
import modelo.Enums.Estado;
import modelo.Enums.TipoVehiculo;

/**
 * Gestiona el registro y consulta de vehículos del sistema.
 * Permite agregar vehículos, buscar unidades disponibles y
 * realizar consultas según su estado.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ListaVehiculosControl {

    private Lista<Vehiculo> vehiculos;

    /**
     * Inicializa la lista de vehículos.
     */
    public ListaVehiculosControl() {
        this.vehiculos = new Lista<>();
    }

    /**
     * Registra un nuevo vehículo en el sistema.
     *
     * @param tipo tipo de vehículo.
     * @param zona zona de operación.
     */
    public void agregarVehiculo(TipoVehiculo tipo, String zona) {
        Vehiculo vehiculo = new Vehiculo(zona, tipo);
        vehiculos.agregar(vehiculo);
    }

    /**
     * Busca un vehículo disponible que coincida con el tipo y la zona especificados.
     *
     * @param tipo tipo de vehículo requerido.
     * @param zona zona requerida.
     * @return vehículo disponible o null si no se encuentra uno.
     */
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

    /**
     * Obtiene todos los vehículos registrados.
     *
     * @return arreglo con todos los vehículos.
     */
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

    /**
     * Obtiene los vehículos que se encuentran en un estado específico.
     *
     * @param estado estado a consultar.
     * @return arreglo de vehículos que cumplen la condición.
     */
    public Vehiculo[] obtenerPorEstado(Estado estado) {
        int total = 0;
        Nodo<Vehiculo> actual = vehiculos.getCabeza();

        while (actual != null) {
            if (actual.getDato().getEstado() == estado) {
                total++;
            }
            actual = actual.getSiguiente();
        }

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

    /**
     * Obtiene la cantidad total de vehículos registrados.
     *
     * @return total de vehículos.
     */
    public int getTotalVehiculos() {
        return vehiculos.getTamaño();
    }
}