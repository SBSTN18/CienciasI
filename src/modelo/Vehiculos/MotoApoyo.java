package modelo.Vehiculos;

import modelo.Vehiculo;

public class MotoApoyo extends Vehiculo {

    public MotoApoyo() {
        super();
    }

    public MotoApoyo(Long id, String zona, boolean disponible, boolean activo) {
        super(id, zona, disponible, activo);
    }
}
