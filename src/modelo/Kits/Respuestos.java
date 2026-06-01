package modelo.Kits;

public class Respuestos {

    private boolean estado;
    private boolean disponible;

    public Respuestos() {
    }

    public Respuestos(boolean estado, boolean disponible) {
        this.estado = estado;
        this.disponible = disponible;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
}
