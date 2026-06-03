package Control;

import modelo.Kits.Repuesto;
import modelo.Coleccion.Pila;

/**
 * Gestiona el inventario de repuestos mediante una estructura tipo pila.
 * Permite agregar, retirar y consultar repuestos disponibles siguiendo
 * el principio LIFO (Last In, First Out).
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class PilaRepuestoControl {

    private Pila<Repuesto> repuestos;

    /**
     * Inicializa la pila de repuestos.
     */
    public PilaRepuestoControl() {
        this.repuestos = new Pila<>();
    }

    /**
     * Agrega un nuevo repuesto al inventario.
     *
     * @param nombre nombre del repuesto.
     * @param cantidad cantidad disponible.
     */
    public void agregarRepuesto(String nombre, int cantidad) {
        Repuesto repuesto = new Repuesto(nombre, cantidad);
        repuestos.apilar(repuesto);
    }

    /**
     * Retira el repuesto ubicado en la cima de la pila.
     *
     * @return repuesto retirado o null si no existen repuestos disponibles.
     */
    public Repuesto retirarRepuesto() {
        if (repuestos.esVacia()) {
            return null;
        }
        return repuestos.desapilar();
    }

    /**
     * Consulta el próximo repuesto disponible sin retirarlo de la pila.
     *
     * @return repuesto ubicado en la cima o null si la pila está vacía.
     */
    public Repuesto verSiguiente() {
        if (repuestos.esVacia()) {
            return null;
        }
        return repuestos.getCima();
    }

    /**
     * Verifica si existen repuestos disponibles.
     *
     * @return true si hay repuestos almacenados, false en caso contrario.
     */
    public boolean hayRepuestos() {
        return !repuestos.esVacia();
    }

    /**
     * Obtiene la cantidad total de repuestos almacenados.
     *
     * @return total de repuestos.
     */
    public int getTotalRepuestos() {
        return repuestos.getTamaño();
    }
}