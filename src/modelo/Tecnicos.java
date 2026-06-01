package modelo;

public class Tecnicos {

    private Long id;
    private String nombre;
    private String zona;
    private Especialidad especialidad;
    private Estado estado;

    public Tecnicos() {
    }

    public Tecnicos(Long id, String nombre, String zona, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.especialidad = especialidad;
        this.estado = Estado.ACTIVO;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }   

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
}
