package modelo.Kits;

import java.util.UUID;
import modelo.Enums.Estado;

public class Repuesto {

    private String id;
    private String nombre;
    private int cantidad;
    private Estado estado;

    public Repuesto() {
    }

    public Repuesto(String nombre, int cantidad) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estado = Estado.DISPONIBLE;
    }

    public String getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Repuesto{id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", estado=" + estado + "}";
    }
}