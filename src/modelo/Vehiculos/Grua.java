package modelo.Vehiculos;

import modelo.Vehiculo;

public class Grua extends Vehiculo {

    public Grua() {
        super();
    }

    public Grua(Long id, String zona, boolean disponible, boolean activo) {
        super(id, zona, disponible, activo);
    }
    
}
