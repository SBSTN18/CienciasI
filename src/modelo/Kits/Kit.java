package modelo.Kits;

import modelo.Estado;

public class Kit {

    private Long id;
    private int cantidadElementos;
    private Estado estado;

    public Kit() {
    }

    public Kit(Long id, int cantidadElementos, Estado estado) {
        this.id = id;
        this.cantidadElementos = cantidadElementos;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
}
