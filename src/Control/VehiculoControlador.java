package Control;

import java.util.UUID;

import modelo.TipoVehiculo;
import modelo.Vehiculo;

public class VehiculoControlador {

    private Vehiculo vehiculo;

    public VehiculoControlador() {
    }

    public void crearVehiculo(String zona, TipoVehiculo tipo){
        this.vehiculo = new Vehiculo(UUID.randomUUID().toString(), zona, tipo);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    
    
}
