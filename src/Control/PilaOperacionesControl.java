package Control;

import modelo.Operacion;
import modelo.Solicitud;
import modelo.Vehiculo;
import modelo.Tecnico;
import modelo.Coleccion.Pila;
import modelo.Enums.Estado;
import modelo.Enums.TipoOperacion;

public class PilaOperacionesControl {

    private Pila<Operacion> operaciones;

    public PilaOperacionesControl() {
        this.operaciones = new Pila<>();
    }

    public void registrarAsignacionVehiculo(Solicitud solicitud, Vehiculo vehiculo) {
        Operacion op = new Operacion(
            TipoOperacion.ASIGNAR_VEHICULO,
            solicitud,
            vehiculo
        );
        operaciones.apilar(op);
    }

    public void registrarAsignacionTecnico(Solicitud solicitud, Tecnico tecnico) {
        Operacion op = new Operacion(
            TipoOperacion.ASIGNAR_TECNICO,
            solicitud,
            tecnico
        );
        operaciones.apilar(op);
    }

    public void registrarCambioEstadoVehiculo(Vehiculo vehiculo, Estado estadoAnterior) {
        Operacion op = new Operacion(
            TipoOperacion.CAMBIAR_ESTADO_VEHICULO,
            vehiculo,
            estadoAnterior
        );
        operaciones.apilar(op);
    }

    public void registrarCambioEstadoTecnico(Tecnico tecnico, Estado estadoAnterior) {
        Operacion op = new Operacion(
            TipoOperacion.CAMBIAR_ESTADO_TECNICO,
            tecnico,
            estadoAnterior
        );
        operaciones.apilar(op);
    }

    public void registrarCierreSolicitud(Solicitud solicitud) {
        Operacion op = new Operacion(
            TipoOperacion.CERRAR_SOLICITUD,
            solicitud,
            solicitud.getEstado()
        );
        operaciones.apilar(op);
    }

    public boolean deshacerUltimaOperacion() {
        if (operaciones.esVacia()) {
            return false;
        }
        Operacion op = operaciones.desapilar();

        switch (op.getTipo()) {
            case ASIGNAR_VEHICULO:
                Solicitud sSolicitud = (Solicitud) op.getObjetoAfectado();
                sSolicitud.setVehiculoAsignado(null);
                Vehiculo v = (Vehiculo) op.getEstadoAnterior();
                v.setEstado(Estado.DISPONIBLE);
                break;

            case ASIGNAR_TECNICO:
                Solicitud tSolicitud = (Solicitud) op.getObjetoAfectado();
                tSolicitud.setTecnicoAsignado(null);
                Tecnico t = (Tecnico) op.getEstadoAnterior();
                t.setEstado(Estado.DISPONIBLE);
                break;

            case CAMBIAR_ESTADO_VEHICULO:
                Vehiculo vehiculo = (Vehiculo) op.getObjetoAfectado();
                vehiculo.setEstado((Estado) op.getEstadoAnterior());
                break;

            case CAMBIAR_ESTADO_TECNICO:
                Tecnico tecnico = (Tecnico) op.getObjetoAfectado();
                tecnico.setEstado((Estado) op.getEstadoAnterior());
                break;

            case CERRAR_SOLICITUD:
                Solicitud solicitud = (Solicitud) op.getObjetoAfectado();
                solicitud.setEstado((modelo.Enums.EstadoSolicitud) op.getEstadoAnterior());
                break;
        }
        return true;
    }

    public Operacion verUltimaOperacion() {
        if (operaciones.esVacia()) {
            return null;
        }
        return operaciones.getCima();
    }

    public boolean hayOperaciones() {
        return !operaciones.esVacia();
    }

    public int getTotalOperaciones() {
        return operaciones.getTamaño();
    }
}