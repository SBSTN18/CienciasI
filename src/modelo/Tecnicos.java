package modelo;

public class Tecnicos {

    private Long id;
    private String nombre;
    private String zona;
    private String especialidad;
    private boolean activo;
    private boolean disponible;

    public Tecnicos() {
    }

    public Tecnicos(Long id, String nombre, String zona, String especialidad, boolean activo, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.especialidad = especialidad;
        this.activo = activo;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    } 

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }   

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }   

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
