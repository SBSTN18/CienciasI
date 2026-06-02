package modelo;

import java.time.LocalDateTime;
import modelo.Enums.TipoOperacion;

public class Operacion {

    private TipoOperacion tipo;
    private Object objetoAfectado;
    private Object estadoAnterior;
    private LocalDateTime hora;

    public Operacion(TipoOperacion tipo, Object objetoAfectado, Object estadoAnterior) {
        this.tipo = tipo;
        this.objetoAfectado = objetoAfectado;
        this.estadoAnterior = estadoAnterior;
        this.hora = LocalDateTime.now();
    }

    public TipoOperacion getTipo() { return tipo; }

    public Object getObjetoAfectado() { return objetoAfectado; }

    public Object getEstadoAnterior() { return estadoAnterior; }

    public LocalDateTime getHora() { return hora; }

    @Override
    public String toString() {
        return "Operacion{tipo=" + tipo + ", hora=" + hora + "}";
    }
}