package modelo;

import java.util.UUID;
import modelo.Enums.TipoCliente;

public class Cliente {

    private String id;
    private String nombre;
    private String telefono;
    private TipoCliente tipoCliente;

    public Cliente(String nombre, String telefono, TipoCliente tipoCliente) {
        this.id = "CL-" + UUID.randomUUID().toString().substring(0, 5);
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - " + tipoCliente;
    }
}