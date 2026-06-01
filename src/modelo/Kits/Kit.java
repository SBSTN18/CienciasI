package modelo.Kits;

public class Kit {

    private Long id;
    private int cantidadElementos;
    private boolean disponible;

    public Kit() {
    }

    public Kit(Long id, int cantidadElementos, boolean disponible) {
        this.id = id;
        this.cantidadElementos = cantidadElementos;
        this.disponible = disponible;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
}
