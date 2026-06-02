package modelo.Kits;

import java.util.UUID;

import modelo.Enums.Estado;

public class Kit {

    private String id;
    private int cantidadElementos;
    private Estado estado;

    public Kit() {
    }

    public Kit(int cantidadElementos) {
        this.id = UUID.randomUUID().toString();
        this.cantidadElementos = cantidadElementos;
        this.estado = Estado.DISPONIBLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String toString() {
        return "Kit{" + "id=" + id + ", cantidadElementos=" + cantidadElementos + ", estado=" + estado + '}';
    }
    
}
