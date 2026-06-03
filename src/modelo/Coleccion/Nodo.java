package modelo.Coleccion;

/**
 * Representa un nodo de una estructura enlazada.
 * Cada nodo almacena un dato y una referencia al siguiente nodo.
 *
 * @param <T> tipo de dato almacenado en el nodo.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Nodo<T> {

    private Nodo<T> siguiente;
    private T dato;

    /**
     * Crea un nodo con el dato especificado.
     *
     * @param dato dato que almacenará el nodo.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     *
     * @return siguiente nodo de la estructura.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Modifica la referencia al siguiente nodo.
     *
     * @param siguiente nuevo nodo siguiente.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return dato contenido en el nodo.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Modifica el dato almacenado en el nodo.
     *
     * @param dato nuevo dato.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Devuelve una representación textual del dato almacenado.
     *
     * @return representación en texto del dato.
     */
    @Override
    public String toString() {
        return dato.toString();
    }

}