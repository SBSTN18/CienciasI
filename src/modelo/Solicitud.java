package modelo;

import java.time.LocalDateTime;
import java.util.UUID;

import modelo.Enums.*;

/**
 * Representa una solicitud de servicio en AutoRescate 24/7.
 * Toda solicitud debe estar asociada a un cliente y zona. Regla #7.
 */
public class Solicitud {

    private String id;
    private String zona;
    private TipoServicio servicio;
    private Prioridad prioridad;
    private EstadoSolicitud estado;
    private Cliente cliente;
    private Vehiculo vehiculoAsignado;
    private Tecnicos tecnicoAsignado;
    private LocalDateTime horaRegistro;
    private LocalDateTime horaFin;

    public Solicitud() {
        this.id = UUID.randomUUID().toString();
        this.horaRegistro = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    /**
     * Crea una solicitud pendiente con hora de registro automática.
     * Reglas #7, #8, #10.
     */
    public Solicitud(String zona, TipoServicio servicio, 
                     Prioridad prioridad, Cliente cliente) {
        this.id = UUID.randomUUID().toString();
        this.zona = zona;
        this.servicio = servicio;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.horaRegistro = LocalDateTime.now();
        this.vehiculoAsignado = null;
        this.tecnicoAsignado = null;
    }

    /**
     * Verifica si la solicitud tiene recursos asignados. Regla #8.
     */
    public boolean tieneRecursosAsignados() {
        return vehiculoAsignado != null && tecnicoAsignado != null;
    }

    /**     
     * Cierra la solicitud registrando la hora de fin. Regla #9.
     */
    public void cerrar() {
        if (!tieneRecursosAsignados()) {
            throw new RuntimeException("No se puede cerrar sin recursos asignados");
        }
        this.estado = EstadoSolicitud.CERRADA;
        this.horaFin = LocalDateTime.now();
    }

    public String getId() { return id; }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public TipoServicio getServicio() { return servicio; }
    public void setServicio(TipoServicio servicio) { this.servicio = servicio; }

    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vehiculo getVehiculoAsignado() { return vehiculoAsignado; }
    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) { 
        this.vehiculoAsignado = vehiculoAsignado; 
    }

    public Tecnicos getTecnicoAsignado() { return tecnicoAsignado; }
    public void setTecnicoAsignado(Tecnicos tecnicoAsignado) { 
        this.tecnicoAsignado = tecnicoAsignado; 
    }

    public LocalDateTime getHoraRegistro() { return horaRegistro; }

    public LocalDateTime getHoraFin() { return horaFin; }
}