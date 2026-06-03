package Control;

import modelo.Operacion;
import modelo.Solicitud;
import modelo.Vehiculo;
import modelo.Tecnico;
import modelo.Coleccion.Pila;
import modelo.Enums.Estado;
import modelo.Enums.TipoOperacion;

/**
 * Gestiona el historial de operaciones realizadas en el sistema
 * mediante una pila. Permite registrar acciones y deshacer la
 * última operación ejecutada.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class PilaOperacionesControl {

    private Pila<Operacion> operaciones;

    /**
     * Inicializa la pila de operaciones.
     */
    public PilaOperacionesControl() {
        this.operaciones = new Pila<>();
    }

    /**
     * Registra la asignación de un vehículo a una solicitud.
     *
     * @param solicitud solicitud afectada.
     * @param vehiculo vehículo asignado.
     */
    public void registrarAsignacionVehiculo(Solicitud solicitud, Vehiculo vehiculo) {
        Operacion op = new Operacion(
                TipoOperacion.ASIGNAR_VEHICULO,
                solicitud,
                vehiculo
        );
        operaciones.apilar(op);
    }

    /**
     * Registra la asignación de un técnico a una solicitud.
     *
     * @param solicitud solicitud afectada.
     * @param tecnico técnico asignado.
     */
    public void registrarAsignacionTecnico(Solicitud solicitud, Tecnico tecnico) {
        Operacion op = new Operacion(
                TipoOperacion.ASIGNAR_TECNICO,
                solicitud,
                tecnico
        );
        operaciones.apilar(op);
    }

    /**
     * Registra un cambio de estado realizado sobre un vehículo.
     *
     * @param vehiculo vehículo afectado.
     * @param estadoAnterior estado previo del vehículo.
     */
    public void registrarCambioEstadoVehiculo(Vehiculo vehiculo, Estado estadoAnterior) {
        Operacion op = new Operacion(
                TipoOperacion.CAMBIAR_ESTADO_VEHICULO,
                vehiculo,
                estadoAnterior
        );
        operaciones.apilar(op);
    }

    /**
     * Registra un cambio de estado realizado sobre un técnico.
     *
     * @param tecnico técnico afectado.
     * @param estadoAnterior estado previo del técnico.
     */
    public void registrarCambioEstadoTecnico(Tecnico tecnico, Estado estadoAnterior) {
        Operacion op = new Operacion(
                TipoOperacion.CAMBIAR_ESTADO_TECNICO,
                tecnico,
                estadoAnterior
        );
        operaciones.apilar(op);
    }

    /**
     * Registra el cierre de una solicitud.
     *
     * @param solicitud solicitud cerrada.
     */
    public void registrarCierreSolicitud(Solicitud solicitud) {
        Operacion op = new Operacion(
                TipoOperacion.CERRAR_SOLICITUD,
                solicitud,
                solicitud.getEstado()
        );
        operaciones.apilar(op);
    }

    /**
     * Deshace la última operación registrada.
     *
     * @return true si la operación fue revertida, false si no existen operaciones.
     */
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
                solicitud.setEstado(
                        (modelo.Enums.EstadoSolicitud) op.getEstadoAnterior()
                );
                break;
        }

        return true;
    }

    /**
     * Obtiene la última operación registrada sin eliminarla.
     *
     * @return última operación o null si la pila está vacía.
     */
    public Operacion verUltimaOperacion() {
        if (operaciones.esVacia()) {
            return null;
        }
        return operaciones.getCima();
    }

    /**
     * Verifica si existen operaciones registradas.
     *
     * @return true si hay operaciones almacenadas.
     */
    public boolean hayOperaciones() {
        return !operaciones.esVacia();
    }

    /**
     * Obtiene la cantidad total de operaciones registradas.
     *
     * @return total de operaciones.
     */
    public int getTotalOperaciones() {
        return operaciones.getTamaño();
    }
}