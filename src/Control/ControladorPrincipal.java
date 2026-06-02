package Control;

import modelo.Solicitud;
import modelo.Tecnico;
import modelo.Vehiculo;
import Control.Vista.ControladorVista;
import modelo.Operacion;
import modelo.Enums.Estado;
import modelo.Enums.Especialidad;
import modelo.Enums.TipoVehiculo;
import vista.VentanaPrincipal;
import modelo.Enums.EstadoSolicitud;
import modelo.Enums.Prioridad;

/**
 * Controlador Principal de la aplicación.
 * Actúa como intermediario entre la Vista y los demás controladores.
 * 
 * Responsabilidades:
 * - Encapsular todos los controladores del modelo
 * - Proporcionar una interfaz única para la Vista
 * - Coordinar operaciones entre controladores
 * 
 * SOLID: Facade Pattern - Proporciona interfaz simplificada
 * Solo el ControladorVista accede a este controlador
 */
public class ControladorPrincipal {
	
	// Controladores del modelo
	private ColaSolicitudControl colaSolicitudes;
	private ListaTecnicosControl listaTecnicos;
	private ListaVehiculosControl listaVehiculos;
	private PilaOperacionesControl pilaOperaciones;
	private PilaKitsControl pilaKits;
	private PilaRepuestoControl pilaRepuesto;
	
	/**
	 * Constructor que inicializa todos los controladores
	 */
	public ControladorPrincipal() {
		
		this.colaSolicitudes = new ColaSolicitudControl();
		this.listaTecnicos = new ListaTecnicosControl();
		this.listaVehiculos = new ListaVehiculosControl();
		this.pilaOperaciones = new PilaOperacionesControl();
		this.pilaKits = new PilaKitsControl();
		this.pilaRepuesto = new PilaRepuestoControl();

		VentanaPrincipal ventana = new VentanaPrincipal();
		ControladorVista controladorVista = new ControladorVista(ventana, this);
		ventana.mostrar();
	}
	
	// ==================== SOLICITUDES ====================
	
	/**
	 * Obtiene todas las solicitudes pendientes ordenadas por prioridad
	 */
	public Solicitud[] obtenerSolicitudesPendientes() {
		return colaSolicitudes.obtenerPendientes();
	}
	
	/**
	 * Obtiene el total de solicitudes
	 */
	public int getTotalSolicitudes() {
		return colaSolicitudes.getTotalSolicitudes();
	}
	
	/**
	 * Obtiene la siguiente solicitud a procesar
	 */
	public Solicitud obtenerSiguienteSolicitud() {
		return colaSolicitudes.obtenerSiguienteSolicitud();
	}
	
	/**
	 * Agrega una nueva solicitud a la cola
	 */
	public void agregarSolicitud(Solicitud solicitud) {
		colaSolicitudes.agregarSolicitud(solicitud);
		registrarOperacionCrearSolicitud(solicitud);
	}
	
	/**
	 * Verifica si hay solicitudes críticas
	 */
	public boolean haySolicitudesCriticas() {
		return colaSolicitudes.hayCriticas();
	}

	
	
	// ==================== VEHÍCULOS ====================
	
	/**
	 * Obtiene todos los vehículos
	 */
	public Vehiculo[] obtenerTodosVehiculos() {
		return listaVehiculos.obtenerTodos();
	}
	
	/**
	 * Obtiene vehículos disponibles
	 */
	public Vehiculo[] obtenerVehiculosDisponibles() {
		return listaVehiculos.obtenerPorEstado(Estado.DISPONIBLE);
	}
	
	/**
	 * Obtiene el total de vehículos
	 */
	public int getTotalVehiculos() {
		return listaVehiculos.getTotalVehiculos();
	}
	
	/**
	 * Busca un vehículo disponible por tipo y zona
	 */
	public Vehiculo buscarVehiculoDisponible(TipoVehiculo tipo, String zona) {
		return listaVehiculos.buscarDisponible(tipo, zona);
	}
	
	/**
	 * Agrega un nuevo vehículo
	 */
	public void agregarVehiculo(TipoVehiculo tipo, String zona) {
		listaVehiculos.agregarVehiculo(tipo, zona);
	}
	
	/**
	 * Asigna un vehículo a una solicitud
	 */
	public void asignarVehiculoASolicitud(Solicitud solicitud, Vehiculo vehiculo) {
		if (vehiculo != null) {
			// TODO: Establecer vehículo en solicitud
			// TODO: Cambiar estado del vehículo
			registrarOperacionAsignacionVehiculo(solicitud, vehiculo);
		}
	}
	
	// ==================== TÉCNICOS ====================
	
	/**
	 * Obtiene todos los técnicos
	 */
	public Tecnico[] obtenerTodosTecnicos() {
		return listaTecnicos.obtenerTodos();
	}
	
	/**
	 * Obtiene técnicos disponibles
	 */
	public Tecnico[] obtenerTecnicosDisponibles() {
		return listaTecnicos.obtenerPorEstado(Estado.DISPONIBLE);
	}
	
	/**
	 * Obtiene el total de técnicos
	 */
	public int getTotalTecnicos() {
		return listaTecnicos.getTotalTecnicos();
	}
	
	/**
	 * Busca un técnico disponible por especialidad y zona
	 */
	public Tecnico buscarTecnicoDisponible(Especialidad especialidad, String zona) {
		return listaTecnicos.buscarDisponible(especialidad, zona);
	}
	
	/**
	 * Agrega un nuevo técnico
	 */
	public void agregarTecnico(String nombre, String zona, Especialidad especialidad) {
		listaTecnicos.agregarTecnico(nombre, zona, especialidad);
	}
	
	/**
	 * Asigna un técnico a una solicitud
	 */
	public void asignarTecnicoASolicitud(Solicitud solicitud, Tecnico tecnico) {
		if (tecnico != null) {
			// TODO: Establecer técnico en solicitud
			// TODO: Cambiar estado del técnico
			registrarOperacionAsignacionTecnico(solicitud, tecnico);
		}
	}
	
	// ==================== OPERACIONES ====================
	
	/**
	 * Obtiene todas las operaciones registradas
	 */
	public Operacion[] obtenerTodasOperaciones() {
		// TODO: Implementar método en PilaOperacionesControl
		return new Operacion[0];
	}
	
	/**
	 * Obtiene el total de operaciones
	 */
	public int getTotalOperaciones() {
		// TODO: Implementar método en PilaOperacionesControl
		return 0;
	}
	
	/**
	 * Deshace la última operación
	 */
	public void deshacerUltimaOperacion() {
		pilaOperaciones.deshacerUltimaOperacion();
	}
	
	/**
	 * Registra operación de creación de solicitud
	 */
	private void registrarOperacionCrearSolicitud(Solicitud solicitud) {
		// TODO: Implementar registro en PilaOperacionesControl
	}
	
	/**
	 * Registra operación de asignación de vehículo
	 */
	private void registrarOperacionAsignacionVehiculo(Solicitud solicitud, Vehiculo vehiculo) {
		pilaOperaciones.registrarAsignacionVehiculo(solicitud, vehiculo);
	}
	
	/**
	 * Registra operación de asignación de técnico
	 */
	private void registrarOperacionAsignacionTecnico(Solicitud solicitud, Tecnico tecnico) {
		pilaOperaciones.registrarAsignacionTecnico(solicitud, tecnico);
	}
	
	/**
	 * Obtiene acceso directo a los controladores si es necesario (uso interno)
	 */
	protected ColaSolicitudControl getColaSolicitudes() {
		return colaSolicitudes;
	}
	
	protected ListaTecnicosControl getListaTecnicos() {
		return listaTecnicos;
	}
	
	protected ListaVehiculosControl getListaVehiculos() {
		return listaVehiculos;
	}
	
	protected PilaOperacionesControl getPilaOperaciones() {
		return pilaOperaciones;
	}
}
