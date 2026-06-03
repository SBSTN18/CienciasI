package Control;

import modelo.Kits.Kit;
import modelo.Coleccion.Pila;

/**
 * Gestiona el inventario de kits mediante una estructura tipo pila.
 * Permite agregar, retirar y consultar kits disponibles siguiendo
 * el principio LIFO (Last In, First Out).
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class PilaKitsControl {

    private Pila<Kit> kits;

    /**
     * Inicializa la pila de kits.
     */
    public PilaKitsControl() {
        this.kits = new Pila<>();
    }

    /**
     * Agrega un nuevo kit al inventario.
     *
     * @param cantidadElementos cantidad de elementos que contiene el kit.
     */
    public void agregarKit(int cantidadElementos) {
        Kit kit = new Kit(cantidadElementos);
        kits.apilar(kit);
    }

    /**
     * Retira el kit ubicado en la cima de la pila.
     *
     * @return kit retirado o null si no existen kits disponibles.
     */
    public Kit retirarKit() {
        if (kits.esVacia()) {
            return null;
        }
        return kits.desapilar();
    }

    /**
     * Consulta el próximo kit disponible sin retirarlo de la pila.
     *
     * @return kit ubicado en la cima o null si la pila está vacía.
     */
    public Kit verSiguiente() {
        if (kits.esVacia()) {
            return null;
        }
        return kits.getCima();
    }

    /**
     * Verifica si existen kits disponibles.
     *
     * @return true si hay kits almacenados, false en caso contrario.
     */
    public boolean hayKits() {
        return !kits.esVacia();
    }

    /**
     * Obtiene la cantidad total de kits almacenados.
     *
     * @return total de kits.
     */
    public int getTotalKits() {
        return kits.getTamaño();
    }
}