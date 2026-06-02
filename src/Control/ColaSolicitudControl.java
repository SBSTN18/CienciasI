package Control;

import modelo.Solicitud;
import modelo.Coleccion.Cola;
import modelo.Coleccion.Nodo;

public class ColaSolicitudControl {
    
    private Cola<Solicitud> pOrdinaria;
    private Cola<Solicitud> pAlta;
    private Cola<Solicitud> pCritica;
    private Cola<Solicitud> pEmergencia;

    public ColaSolicitudControl() {
        this.pOrdinaria = new Cola();
        this.pAlta = new Cola();
        this.pCritica = new Cola();
        this.pEmergencia = new Cola();
    }

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

    public boolean haySolicitudes() {
        return !pOrdinaria.estaVacia() || !pAlta.estaVacia() || !pCritica.estaVacia() || !pEmergencia.estaVacia();
    }

    public boolean hayCriticas() {
        return !pCritica.estaVacia();
    }

    public int getTotalSolicitudes() {
        return pOrdinaria.getTamaño() + pAlta.getTamaño() + pCritica.getTamaño() + pEmergencia.getTamaño();
    }

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
}
