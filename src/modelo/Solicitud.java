package modelo;

public class Solicitud {

    private Long id;
    private String zona;
    private String servicio;
    private Prioridad prioridad;
    private String cliente;

    public Solicitud() {
    }

    public Solicitud(Long id, String zona, String servicio, Prioridad prioridad, String cliente) {
        this.id = id;
        this.zona = zona;
        this.servicio = servicio;
        this.prioridad = prioridad;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getServicio() {
        return servicio;
    }   

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }   

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
}
