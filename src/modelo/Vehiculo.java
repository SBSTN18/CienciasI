package modelo;

public class Vehiculo {

    private String id;
    private Estado estado;
    private TipoVehiculo tipo;
    private String zona;
    private Tecnicos tecnico;

    public Vehiculo() {
    }

    public Vehiculo(String id, String zona, TipoVehiculo tipo) {
        this.id = id;
        this.zona = zona;
        this.tipo = tipo;
        this.estado = Estado.ACTIVO;
        this.tecnico = null;
    } 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

}
