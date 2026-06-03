package modelo.Coleccion;

/**
 * Implementa una estructura de datos tipo pila (LIFO - Last In, First Out).
 * Permite agregar y retirar elementos desde la cima de la estructura.
 *
 * @param <T> tipo de dato almacenado en la pila.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Pila<T> {

    private Nodo<T> cima;
    private int tamaño;

    /**
     * Crea una pila vacía.
     */
    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento a la cima de la pila.
     *
     * @param dato elemento que se desea apilar.
     */
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamaño++;
    }

    /**
     * Elimina y retorna el elemento ubicado en la cima de la pila.
     *
     * @return elemento retirado o null si la pila está vacía.
     */
    public T desapilar() {
        if (esVacia()) {
            return null;
        }

        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamaño--;

        return dato;
    }

    /**
     * Obtiene el elemento ubicado en la cima de la pila sin eliminarlo.
     *
     * @return elemento en la cima o null si la pila está vacía.
     */
    public T getCima() {
        if (esVacia()) {
            return null;
        }

        return cima.getDato();
    }

    /**
     * Verifica si la pila se encuentra vacía.
     *
     * @return true si no contiene elementos, false en caso contrario.
     */
    public boolean esVacia() {
        return tamaño == 0;
    }

    /**
     * Obtiene la cantidad de elementos almacenados en la pila.
     *
     * @return tamaño actual de la pila.
     */
    public int getTamaño() {
        return tamaño;
    }

}