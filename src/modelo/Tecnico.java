package modelo;

import modelo.Enums.Especialidad;
import modelo.Enums.Estado;

/**
 * Representa un técnico de asistencia vehicular de AutoRescate 24/7.
 * Cada técnico posee una identificación única, una zona de operación,
 * una especialidad y un estado que indica su disponibilidad.
 *
 * @author TuNombre
 * @version 1.0
 */
public class Tecnico {

    
    private String id;
    private String nombre;
    private String zona;
    private Especialidad especialidad;
    private Estado estado;

    /**
     * Constructor vacío.
     */
    public Tecnico() {
    }

    /**
     * Crea un técnico con una identificación generada automáticamente.
     * El estado inicial es DISPONIBLE.
     *
     * @param nombre nombre del técnico.
     * @param zona zona de operación.
     * @param especialidad especialidad del técnico.
     */
    public Tecnico(String nombre, String zona, Especialidad especialidad) {
        this.id = "TC-" + java.util.UUID.randomUUID().toString().substring(0, 5);
        this.nombre = nombre;
        this.zona = zona;
        this.especialidad = especialidad;
        this.estado = Estado.DISPONIBLE;
    }

    /**
     * Obtiene el identificador del técnico.
     *
     * @return identificador único.
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica el identificador del técnico.
     *
     * @param id nuevo identificador.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del técnico.
     *
     * @return nombre del técnico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del técnico.
     *
     * @param nombre nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la zona de operación.
     *
     * @return zona asignada.
     */
    public String getZona() {
        return zona;
    }

    /**
     * Modifica la zona de operación.
     *
     * @param zona nueva zona.
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * Obtiene la especialidad del técnico.
     *
     * @return especialidad registrada.
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * Modifica la especialidad del técnico.
     *
     * @param especialidad nueva especialidad.
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el estado actual del técnico.
     *
     * @return estado del técnico.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del técnico.
     *
     * @param estado nuevo estado.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación textual del técnico.
     *
     * @return información básica del técnico.
     */
    @Override
    public String toString() {
        return "Tecnico: " + nombre + ", zona=" + zona + ", especialidad=" + especialidad;
    }
}