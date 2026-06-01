package modelo.Vehiculos;

import modelo.Vehiculo;

public class Liviano extends Vehiculo {

    public Liviano() {
        super();
    }

    public Liviano(Long id, String zona, boolean disponible, boolean activo) {
        super(id, zona, disponible, activo);
    }
    
}
