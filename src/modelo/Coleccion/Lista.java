package modelo.Coleccion;

/**
 * Implementa una lista enlazada simple para almacenar elementos de forma
 * dinámica mediante nodos enlazados.
 *
 * @param <T> tipo de dato almacenado en la lista.
 * 
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Lista<T> {

    private Nodo<T> cabeza;
    private int tamaño;

    /**
     * Crea una lista vacía.
     */
    public Lista() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param dato elemento que se desea agregar.
     */
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    /**
     * Obtiene el elemento almacenado en una posición específica.
     *
     * @param indice posición del elemento.
     * @return elemento encontrado o null si el índice es inválido.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }

        Nodo<T> actual = cabeza;

        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

    /**
     * Elimina el elemento ubicado en una posición específica.
     *
     * @param indice posición del elemento a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return false;
        }

        if (indice == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo<T> actual = cabeza;

            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }

        tamaño--;
        return true;
    }

    /**
     * Modifica el nodo cabeza de la lista.
     *
     * @param cabeza nuevo nodo cabeza.
     */
    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * Obtiene el nodo cabeza de la lista.
     *
     * @return nodo cabeza.
     */
    public Nodo<T> getCabeza() {
        return cabeza;
    }

    /**
     * Obtiene la cantidad de elementos almacenados.
     *
     * @return tamaño actual de la lista.
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Verifica si la lista se encuentra vacía.
     *
     * @return true si no contiene elementos, false en caso contrario.
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }
}