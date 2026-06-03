package Control;

import Control.Vista.ControladorVista;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        ControladorPrincipal controlador = new ControladorPrincipal();
        VentanaPrincipal ventana = new VentanaPrincipal();
        ControladorVista controladorVista = new ControladorVista(ventana, controlador);
        ventana.mostrar();
    }
}