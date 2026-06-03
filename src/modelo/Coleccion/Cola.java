package modelo.Coleccion;

/**
 * Implementa una estructura de datos tipo cola (FIFO - First In, First Out).
 * Permite agregar elementos al final de la cola y retirarlos desde el frente.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Cola<T> {

    public Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    /**
     * Crea una cola vacía.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    /**
     * Verifica si la cola se encuentra vacía.
     *
     * @return true si la cola no contiene elementos, false en caso contrario.
     */
    public boolean estaVacia() {
        return tamaño == 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param dato elemento que se desea encolar.
     */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
        }
        tamaño++;
    }

    /**
     * Elimina y retorna el elemento ubicado al frente de la cola.
     *
     * @return elemento retirado o null si la cola está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        tamaño--;

        if (estaVacia()) {
            fin = null;
        }

        return dato;
    }

    /**
     * Obtiene el nodo ubicado al frente de la cola.
     *
     * @return nodo frontal o null si la cola está vacía.
     */
    public Nodo<T> getFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente;
    }

    /**
     * Obtiene la cantidad de elementos almacenados en la cola.
     *
     * @return tamaño actual de la cola.
     */
    public int getTamaño() {
        return tamaño;
    }
}