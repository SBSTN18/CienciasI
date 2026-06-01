package modelo.Vehiculos;

import modelo.Vehiculo;

public class Camioneta extends Vehiculo {

    public Camioneta() {
        super();
    }

    public Camioneta(Long id, String zona, boolean disponible, boolean activo) {
        super(id, zona, disponible, activo);
    }
    
}
