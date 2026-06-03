package modelo.Kits;

import java.util.UUID;
import modelo.Enums.Estado;

/**
 * Representa un repuesto utilizado en las operaciones de asistencia vehicular.
 * Cada repuesto posee una identificación única, un nombre, una cantidad
 * disponible y un estado asociado.
 * 
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Repuesto {

    private String id;
    private String nombre;
    private int cantidad;
    private Estado estado;

    /**
     * Constructor vacío.
     */
    public Repuesto() {
    }

    /**
     * Crea un repuesto con nombre y cantidad especificados.
     * El estado inicial del repuesto es DISPONIBLE.
     *
     * @param nombre nombre del repuesto.
     * @param cantidad cantidad disponible.
     */
    public Repuesto(String nombre, int cantidad) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estado = Estado.DISPONIBLE;
    }

    /**
     * Obtiene el identificador del repuesto.
     *
     * @return identificador único.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del repuesto.
     *
     * @return nombre del repuesto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del repuesto.
     *
     * @param nombre nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad disponible del repuesto.
     *
     * @return cantidad disponible.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Modifica la cantidad disponible del repuesto.
     *
     * @param cantidad nueva cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el estado actual del repuesto.
     *
     * @return estado del repuesto.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del repuesto.
     *
     * @param estado nuevo estado.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación textual del repuesto.
     *
     * @return información resumida del repuesto.
     */
    @Override
    public String toString() {
        return "Repuesto{id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", estado=" + estado + "}";
    }
}