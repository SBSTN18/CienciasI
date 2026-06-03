package modelo;

import java.util.UUID;
import modelo.Enums.TipoCliente;

/**
 * Representa un cliente de la empresa AutoRescate 24/7.
 * Puede ser un cliente particular, una empresa de transporte o una aseguradora.
 * El tipo de cliente puede influir en la prioridad de atención de sus solicitudes.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Cliente {

    
    private String id;
    private String nombre;
    private String telefono;
    private TipoCliente tipoCliente;

    /**
     * Crea un nuevo cliente con todos sus datos.
     * El identificador único se genera automáticamente con el prefijo CL-.
     *
     * @param nombre      Nombre completo o razón social del cliente.
     * @param telefono    Número de teléfono de contacto.
     * @param tipoCliente Tipo de cliente.
     */
    public Cliente(String nombre, String telefono, TipoCliente tipoCliente) {
        this.id = "CL-" + UUID.randomUUID().toString().substring(0, 5);
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
    }

    /**
     * Retorna el identificador único del cliente.
     *
     * @return ID del cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna el nombre o razón social del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre o razón social del cliente.
     *
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el teléfono de contacto del cliente.
     *
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono de contacto del cliente.
     *
     * @param telefono Nuevo teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna el tipo de cliente.
     *
     * @return Tipo de cliente.
     */
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Establece el tipo de cliente.
     *
     * @param tipoCliente Nuevo tipo de cliente.
     */
    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * Retorna una representación en texto del cliente
     * con su ID, nombre y tipo de cliente.
     *
     * @return Cadena con los datos principales del cliente.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - " + tipoCliente;
    }
}