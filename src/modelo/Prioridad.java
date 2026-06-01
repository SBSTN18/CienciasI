package modelo;

public enum Prioridad {
    ORDINARIA (1),
    ALTA (2),
    CRITICA (3),
    EMERGENCIA (4);

    private final int valor;

    Prioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }


}
