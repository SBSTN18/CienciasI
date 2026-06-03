package Control;

import modelo.Solicitud;
import modelo.Tecnico;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;
import modelo.Enums.Especialidad;

public class ListaEnProceso {

    private Lista<Solicitud> enProceso;

    public ListaEnProceso() {
        this.enProceso = new Lista<>();
    }

    public void agregarEnProceso(Solicitud solicitud) {
        enProceso.agregar(solicitud);
    }

    public Solicitud[] obtenerEnProceso() {
        Solicitud[] resultado = new Solicitud[enProceso.getTamaño()];
        Nodo<Solicitud> actual = enProceso.getCabeza();
        int i = 0;
        while (actual != null) {
            resultado[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public void cerrarSolicitud(Solicitud solicitud) {
        // Eliminar de enProceso
        Nodo<Solicitud> actual = enProceso.getCabeza();
        Nodo<Solicitud> anterior = null;
        while (actual != null) {
            if (actual.getDato().getId().equals(solicitud.getId())) {
                if (anterior == null) {
                    enProceso.setCabeza(actual.getSiguiente());
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                break;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }

    public int getTotalEnProceso() {
        return enProceso.getTamaño();
    }
}
