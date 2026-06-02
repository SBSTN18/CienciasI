package modelo.Coleccion;

public class Pila<T> {

    private Nodo<T> cima;
    private int tamaño;

    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamaño++;
    }

    public T desapilar() {
        if (esVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamaño--;
        return dato;
    }

    public T getCima() {
        if (esVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return cima.getDato();
    }

    public boolean esVacia() {
        return tamaño == 0;
    }

    public int getTamaño() {
        return tamaño;
    }


    
}
