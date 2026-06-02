package modelo.Coleccion;

public class Cola<T> {

    public Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

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

    public Nodo<T> getFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente;
    }

    public int getTamaño() {
        return tamaño;
    }
}
