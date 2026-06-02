package Control;

import modelo.Kits.Kit;
import modelo.Enums.Estado;
import modelo.Coleccion.Pila;

public class PilaKitsControl {

    private Pila<Kit> kits;

    public PilaKitsControl() {
        this.kits = new Pila<>();
    }

    public void agregarKit(int cantidadElementos) {
        Kit kit = new Kit(cantidadElementos);
        kits.apilar(kit);
    }

    public Kit retirarKit() {
        if (kits.esVacia()) {
            return null;
        }
        return kits.desapilar();
    }

    public Kit verSiguiente() {
        if (kits.esVacia()) {
            return null;
        }
        return kits.getCima();
    }

    public boolean hayKits() {
        return !kits.esVacia();
    }

    public int getTotalKits() {
        return kits.getTamaño();
    }
}