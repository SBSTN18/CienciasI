package modelo;

import java.time.LocalDateTime;
import modelo.Enums.TipoOperacion;

/**
 * Representa una operación realizada en el sistema AutoRescate 24/7.
 * Almacena el tipo de operación, el objeto afectado y su estado anterior,
 * permitiendo al supervisor revertir operaciones erróneas respetando
 * el orden en que fueron realizadas. Regla de negocio #17.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class Operacion {

    
    private TipoOperacion tipo;
    private Object objetoAfectado;
    private Object estadoAnterior;
    private LocalDateTime hora;

    /**
     * Crea una nueva operación registrando el momento exacto en que se realiza.
     *
     * @param tipo            Tipo de operación realizada.
     * @param objetoAfectado  Objeto sobre el cual se realizó la operación.
     * @param estadoAnterior  Estado anterior del objeto antes de la operación.
     */
    public Operacion(TipoOperacion tipo, Object objetoAfectado, Object estadoAnterior) {
        this.tipo = tipo;
        this.objetoAfectado = objetoAfectado;
        this.estadoAnterior = estadoAnterior;
        this.hora = LocalDateTime.now();
    }

    /**
     * Retorna el tipo de operación realizada.
     *
     * @return Tipo de operación.
     */
    public TipoOperacion getTipo() { return tipo; }

    /**
     * Retorna el objeto afectado por la operación.
     * Puede ser un Vehiculo, Tecnico o Solicitud.
     *
     * @return Objeto afectado.
     */
    public Object getObjetoAfectado() { return objetoAfectado; }

    /**
     * Retorna el estado anterior del objeto antes de la operación.
     * Se usa para restaurar el estado al deshacer.
     *
     * @return Estado anterior del objeto.
     */
    public Object getEstadoAnterior() { return estadoAnterior; }

    /**
     * Retorna la fecha y hora en que se realizó la operación.
     *
     * @return Fecha y hora de la operación.
     */
    public LocalDateTime getHora() { return hora; }

    /**
     * Retorna una representación en texto de la operación
     * con su tipo y hora de registro.
     *
     * @return Cadena con el tipo y hora de la operación.
     */
    @Override
    public String toString() {
        return "Operacion{tipo=" + tipo + ", hora=" + hora + "}";
    }
}