package Control;

import modelo.Kits.Repuesto;
import modelo.Coleccion.Pila;

public class PilaRepuestoControl {

    private Pila<Repuesto> repuestos;

    public PilaRepuestoControl() {
        this.repuestos = new Pila<>();
    }

    public void agregarRepuesto(String nombre, int cantidad) {
        Repuesto repuesto = new Repuesto(nombre, cantidad);
        repuestos.apilar(repuesto);
    }

    public Repuesto retirarRepuesto() {
        if (repuestos.esVacia()) {
            return null;
        }
        return repuestos.desapilar();
    }

    public Repuesto verSiguiente() {
        if (repuestos.esVacia()) {
            return null;
        }
        return repuestos.getCima();
    }

    public boolean hayRepuestos() {
        return !repuestos.esVacia();
    }

    public int getTotalRepuestos() {
        return repuestos.getTamaño();
    }
}