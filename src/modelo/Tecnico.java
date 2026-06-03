package modelo;

import java.util.UUID;

import modelo.Enums.Especialidad;
import modelo.Enums.Estado;

public class Tecnico {

    private String id;
    private String nombre;
    private String zona;
    private Especialidad especialidad;
    private Estado estado;

    public Tecnico() {
    }

    public Tecnico(String nombre, String zona, Especialidad especialidad) {
        this.id = "TC-" + java.util.UUID.randomUUID().toString().substring(0, 5);
        this.nombre = nombre;
        this.zona = zona;
        this.especialidad = especialidad;
        this.estado = Estado.DISPONIBLE;
    }

    public String getId() {
        return id;
    } 

    public void setId(String id) {
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
    
    public String toString() {
        return "Tecnico: " + nombre + ", zona=" + zona + ", especialidad=" + especialidad;
    }
}
