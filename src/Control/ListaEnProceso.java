package Control;

import modelo.Solicitud;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;

/**
 * Gestiona las solicitudes que actualmente se encuentran en proceso
 * de atención dentro del sistema.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ListaEnProceso {

    private Lista<Solicitud> enProceso;

    /**
     * Inicializa la lista de solicitudes en proceso.
     */
    public ListaEnProceso() {
        this.enProceso = new Lista<>();
    }

    /**
     * Agrega una solicitud a la lista de solicitudes en proceso.
     *
     * @param solicitud solicitud que será agregada.
     */
    public void agregarEnProceso(Solicitud solicitud) {
        enProceso.agregar(solicitud);
    }

    /**
     * Obtiene todas las solicitudes que se encuentran en proceso.
     *
     * @return arreglo de solicitudes en proceso.
     */
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

    /**
     * Elimina una solicitud de la lista de solicitudes en proceso.
     *
     * @param solicitud solicitud que se desea cerrar.
     */
    public void cerrarSolicitud(Solicitud solicitud) {
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

    /**
     * Obtiene la cantidad de solicitudes actualmente en proceso.
     *
     * @return total de solicitudes en proceso.
     */
    public int getTotalEnProceso() {
        return enProceso.getTamaño();
    }
}