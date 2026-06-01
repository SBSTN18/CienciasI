package modelo;

public class Vehiculo {

    private String id;
    private boolean disponible;
    private boolean activo;
    private String zona;
    private Tecnicos tecnico;

    public Vehiculo() {
    }

    public Vehiculo(String id, String zona, boolean disponible, boolean activo){
        this.id = id;
        this.zona = zona;
        this.disponible = disponible;
        this.activo = activo;
        this.tecnico = null;
    } 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }   

    public Tecnicos getTecnico() {
        return tecnico;
    }   

    public void setTecnico(Tecnicos tecnico) {
        this.tecnico = tecnico;
    }

}
