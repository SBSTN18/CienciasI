package aplicacion;

import vista.VentanaPrincipal;
import Control.ControladorPrincipal;
import Control.Vista.ControladorVista;

/**
 * Clase principal para iniciar la aplicación AutoRescate.
 * 
 * Esta clase es el punto de entrada de la aplicación.
 * Respeta el patrón MVC manteniendo la separación de responsabilidades:
 * - Crea la Vista (VentanaPrincipal)
 * - Crea el Controlador Principal (ControladorPrincipal)
 * - Crea el Controlador de Vista (ControladorVista)
 * - El controlador de vista coordina todo
 */
public class Main {
	
	/**
	 * Método principal que inicia la aplicación
	 * 
	 * @param args argumentos de línea de comandos (no usados)
	 */
	public static void main(String[] args) {
		
		new ControladorPrincipal();
			
	}
}
