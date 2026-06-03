package modelo.Kits;

import java.util.UUID;

import modelo.Enums.Estado;

/**
 * Representa un kit de atención rápida utilizado en los servicios
 * de asistencia vehicular.
 * Cada kit posee una identificación única, una cantidad de elementos
 * y un estado de disponibilidad.
 * 
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Kit {

    private String id;
    private int cantidadElementos;
    private Estado estado;

    /**
     * Constructor vacío.
     */
    public Kit() {
    }

    /**
     * Crea un kit con la cantidad de elementos especificada.
     * El estado inicial del kit es DISPONIBLE.
     *
     * @param cantidadElementos cantidad de elementos que contiene el kit.
     */
    public Kit(int cantidadElementos) {
        this.id = UUID.randomUUID().toString();
        this.cantidadElementos = cantidadElementos;
        this.estado = Estado.DISPONIBLE;
    }

    /**
     * Obtiene el identificador del kit.
     *
     * @return identificador único del kit.
     */
    public String getId() {
        return id;
    }

    /**
     * Modifica el identificador del kit.
     *
     * @param id nuevo identificador.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la cantidad de elementos del kit.
     *
     * @return cantidad de elementos.
     */
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    /**
     * Modifica la cantidad de elementos del kit.
     *
     * @param cantidadElementos nueva cantidad de elementos.
     */
    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }

    /**
     * Obtiene el estado actual del kit.
     *
     * @return estado del kit.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del kit.
     *
     * @param estado nuevo estado.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación textual del kit.
     *
     * @return información resumida del kit.
     */
    @Override
    public String toString() {
        return "Kit:" + "id=" + id + ", cantidadElementos=" + cantidadElementos + ", estado=" + estado + '}';
    }

}