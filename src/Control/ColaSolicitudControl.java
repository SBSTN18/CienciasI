package Control;

import modelo.Solicitud;
import modelo.Coleccion.Cola;
import modelo.Coleccion.Nodo;

/**
 * Gestiona las solicitudes del sistema mediante colas organizadas por prioridad.
 * Permite registrar solicitudes, obtener la siguiente solicitud a atender,
 * consultar solicitudes pendientes y almacenar las solicitudes cerradas.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ColaSolicitudControl {

    private Cola<Solicitud> pOrdinaria;
    private Cola<Solicitud> pAlta;
    private Cola<Solicitud> pCritica;
    private Cola<Solicitud> pEmergencia;
    private Cola<Solicitud> pCerradas;

    /**
     * Inicializa las colas de solicitudes según su nivel de prioridad.
     */
    public ColaSolicitudControl() {
        this.pOrdinaria = new Cola();
        this.pAlta = new Cola();
        this.pCritica = new Cola();
        this.pEmergencia = new Cola();
        this.pCerradas = new Cola<>();
    }

    /**
     * Agrega una solicitud a la cola correspondiente según su prioridad.
     *
     * @param solicitud solicitud a registrar.
     */
    public void agregarSolicitud(Solicitud solicitud) {
        switch (solicitud.getPrioridad()) {
            case ORDINARIA:
                pOrdinaria.encolar(solicitud);
                break;
            case ALTA:
                pAlta.encolar(solicitud);
                break;
            case CRITICA:
                pCritica.encolar(solicitud);
                break;
            case EMERGENCIA:
                pEmergencia.encolar(solicitud);
                break;
        }
    }

    /**
     * Agrega una solicitud a la cola de solicitudes cerradas.
     *
     * @param solicitud solicitud finalizada.
     */
    public void agregarCerrada(Solicitud solicitud) {
        pCerradas.encolar(solicitud);
    }

    /**
     * Obtiene la siguiente solicitud a atender respetando el orden de prioridad.
     *
     * @return solicitud con mayor prioridad disponible o null si no existen solicitudes.
     */
    public Solicitud obtenerSiguienteSolicitud() {
        if (!pEmergencia.estaVacia()) {
            return pEmergencia.desencolar();
        } else if (!pCritica.estaVacia()) {
            return pCritica.desencolar();
        } else if (!pAlta.estaVacia()) {
            return pAlta.desencolar();
        } else if (!pOrdinaria.estaVacia()) {
            return pOrdinaria.desencolar();
        } else {
            return null;
        }
    }

    /**
     * Verifica si existen solicitudes pendientes por atender.
     *
     * @return true si existen solicitudes pendientes, false en caso contrario.
     */
    public boolean haySolicitudes() {
        return !pOrdinaria.estaVacia()
                || !pAlta.estaVacia()
                || !pCritica.estaVacia()
                || !pEmergencia.estaVacia();
    }

    /**
     * Verifica si existen solicitudes críticas pendientes.
     *
     * @return true si existen solicitudes críticas, false en caso contrario.
     */
    public boolean hayCriticas() {
        return !pCritica.estaVacia();
    }

    /**
     * Obtiene el total de solicitudes pendientes.
     *
     * @return cantidad total de solicitudes pendientes.
     */
    public int getTotalSolicitudes() {
        return pOrdinaria.getTamaño()
                + pAlta.getTamaño()
                + pCritica.getTamaño()
                + pEmergencia.getTamaño();
    }

    /**
     * Obtiene todas las solicitudes pendientes ordenadas por prioridad.
     *
     * @return arreglo con las solicitudes pendientes.
     */
    public Solicitud[] obtenerPendientes() {
        int total = getTotalSolicitudes();
        Solicitud[] pendientes = new Solicitud[total];
        int i = 0;

        Nodo<Solicitud> actual = pEmergencia.getFrente();
        while (actual != null) {
            pendientes[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        actual = pCritica.getFrente();
        while (actual != null) {
            pendientes[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        actual = pAlta.getFrente();
        while (actual != null) {
            pendientes[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        actual = pOrdinaria.getFrente();
        while (actual != null) {
            pendientes[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        return pendientes;
    }

    /**
     * Obtiene todas las solicitudes cerradas registradas en el sistema.
     *
     * @return arreglo con las solicitudes cerradas.
     */
    public Solicitud[] obtenerCerradas() {
        int total = pCerradas.getTamaño();
        Solicitud[] cerradas = new Solicitud[total];
        int i = 0;

        Nodo<Solicitud> actual = pCerradas.getFrente();

        while (actual != null) {
            cerradas[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        return cerradas;
    }

}