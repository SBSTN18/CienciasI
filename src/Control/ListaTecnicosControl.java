package Control;

import modelo.Tecnico;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;
import modelo.Enums.Estado;
import modelo.Enums.Especialidad;

public class ListaTecnicosControl {

    private Lista<Tecnico> tecnicos;

    public ListaTecnicosControl() {
        this.tecnicos = new Lista<>();
    }

    public void agregarTecnico(String nombre, String zona, Especialidad especialidad) {
        Tecnico tecnico = new Tecnico(nombre, zona, especialidad);
        tecnicos.agregar(tecnico);
    }

    public Tecnico buscarDisponible(Especialidad especialidad, String zona) {
        Nodo<Tecnico> actual = tecnicos.getCabeza();
        while (actual != null) {
            Tecnico t = actual.getDato();
            if (t.getEstado() == Estado.DISPONIBLE
                && t.getEspecialidad() == especialidad
                && t.getZona().equals(zona)) {
                return t;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Tecnico[] obtenerTodos() {
        Tecnico[] resultado = new Tecnico[tecnicos.getTamaño()];
        Nodo<Tecnico> actual = tecnicos.getCabeza();
        int i = 0;
        while (actual != null) {
            resultado[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public Tecnico[] obtenerPorEstado(Estado estado) {
        int total = 0;
        Nodo<Tecnico> actual = tecnicos.getCabeza();
        while (actual != null) {
            if (actual.getDato().getEstado() == estado) total++;
            actual = actual.getSiguiente();
        }

        Tecnico[] resultado = new Tecnico[total];
        actual = tecnicos.getCabeza();
        int i = 0;
        while (actual != null) {
            if (actual.getDato().getEstado() == estado) {
                resultado[i++] = actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public int getTotalTecnicos() {
        return tecnicos.getTamaño();
    }
}