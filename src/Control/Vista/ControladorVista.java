package Control.Vista;

import vista.VentanaPrincipal;
import vista.paneles.*;
import modelo.*;
import modelo.Enums.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.ControladorPrincipal;

/**
 * Controlador de la Vista en el patrón MVC.
 * 
 * Responsabilidades:
 * - Manejar eventos de la interfaz gráfica
 * - Coordinar la navegación entre paneles
 * - Procesar datos de formularios
 * - Comunicar con el modelo (futuro)
 * 
 * SOLID: Single Responsibility - Solo gestiona la lógica de la Vista
 * NO contiene lógica de negocio, solo coordinación de UI
 */
public class ControladorVista {
	
	private VentanaPrincipal ventana;
	private ControladorPrincipal controlador;
	
	// Paneles actuales
	private PanelSolicitudes panelSolicitudes;
	private PanelCrearSolicitud panelCrearSolicitud;
	private PanelVehiculos panelVehiculos;
	private PanelCrearVehiculos panelCrearVehiculos;
	private PanelOperaciones panelOperaciones;
	private PanelTecnicos panelTecnicos;
	
	/**
	 * Constructor del controlador
	 * 
	 * @param ventana la ventana principal de la aplicación
	 * @param controlador el controlador principal que coordina el modelo
	 */
	public ControladorVista(VentanaPrincipal ventana, ControladorPrincipal controlador) {
		this.ventana = ventana;
		this.controlador = controlador;
		inicializar();
	}
	
	/**
	 * Inicializa el controlador registrando todos los listeners
	 */
	private void inicializar() {
		registrarListenersNavegacion();
		cargarPanelInicial();
	}
	
	/**
	 * Registra los listeners para los botones de navegación principal
	 */
	private void registrarListenersNavegacion() {
		ventana.registrarListenerNavegacion(
			e -> navegarASolicitudes(),
			e -> navegarACrearSolicitud(),
			e -> navegarAVehiculos(),
			e -> navegarACrearVehiculos(),
			e -> navegarAOperaciones(),
			e -> navegarATecnicos()
		);
	}
	
	/**
	 * Carga el panel inicial (solicitudes)
	 */
	private void cargarPanelInicial() {
		navegarASolicitudes();
	}
	
	// ==================== Métodos de Navegación ====================
	
	/**
	 * Navega al panel de solicitudes
	 */
	private void navegarASolicitudes() {
		panelSolicitudes = ventana.mostrarPanelSolicitudes();
		registrarListenersPanelSolicitudes();
		cargarDatosSolicitudes();
	}
	
	/**
	 * Navega al panel de crear solicitud
	 */
	private void navegarACrearSolicitud() {
		panelCrearSolicitud = ventana.mostrarPanelCrearSolicitud();
		registrarListenersPanelCrearSolicitud();
		cargarComboBoxesSolicitud();
	}
	
	/**
	 * Navega al panel de vehículos
	 */
	private void navegarAVehiculos() {
		panelVehiculos = ventana.mostrarPanelVehiculos();
		registrarListenersPanelVehiculos();
		cargarDatosVehiculos();
	}
	
	/**
	 * Navega al panel de crear vehículos
	 */
	private void navegarACrearVehiculos() {
		panelCrearVehiculos = ventana.mostrarPanelCrearVehiculos();
		registrarListenersPanelCrearVehiculos();
	}
	
	/**
	 * Navega al panel de operaciones
	 */
	private void navegarAOperaciones() {
		panelOperaciones = ventana.mostrarPanelOperaciones();
		registrarListenersPanelOperaciones();
		cargarDatosOperaciones();
	}
	
	/**
	 * Navega al panel de técnicos
	 */
	private void navegarATecnicos() {
		panelTecnicos = ventana.mostrarPanelTecnicos();
		registrarListenersPanelTecnicos();
		cargarDatosTecnicos();
	}
	
	// ==================== Listeners Panel Solicitudes ====================
	
	private void registrarListenersPanelSolicitudes() {
		if (panelSolicitudes == null) return;
		
		panelSolicitudes.registrarListenerActualizar(e -> cargarDatosSolicitudes());
		panelSolicitudes.registrarListenerVerDetalles(e -> verDetallesSolicitud());
		panelSolicitudes.registrarListenerCancelar(e -> cancelarSolicitud());
	}
	
	private void cargarDatosSolicitudes() {
		if (panelSolicitudes == null || controlador == null) return;
		
		panelSolicitudes.limpiarTabla();
		
		// Cargar solicitudes desde el controlador principal
		Solicitud[] solicitudes = controlador.obtenerSolicitudesPendientes();
		if (solicitudes != null) {
			for (Solicitud sol : solicitudes) {
				if (sol != null) {
					String tecnico = "Sin asignar"; // TODO: Obtener técnico asignado
					panelSolicitudes.agregarFila(
						sol.getId().toString(),
						sol.getCliente().getNombre(),
						sol.getServicio().toString(),
						sol.getEstado().toString(),
						sol.getPrioridad().toString(),
						sol.getZona(),
						tecnico,
						sol.getHoraRegistro().toString()
					);
				}
			}
		}
		
		panelSolicitudes.actualizarTotal(controlador.getTotalSolicitudes());
	}
	
	private void verDetallesSolicitud() {
		String id = panelSolicitudes.getIdSolicitudSeleccionada();
		if (id == null) {
			panelSolicitudes.mostrarError("Selecciona una solicitud primero");
			return;
		}
		// TODO: Cargar detalles del modelo
		panelSolicitudes.mostrarMensaje("Detalles de solicitud: " + id);
	}
	
	private void cancelarSolicitud() {
		String id = panelSolicitudes.getIdSolicitudSeleccionada();
		if (id == null) {
			panelSolicitudes.mostrarError("Selecciona una solicitud primero");
			return;
		}
		if (panelSolicitudes.confirmar("¿Deseas cancelar la solicitud " + id + "?")) {
			// TODO: Cancelar en el modelo
			panelSolicitudes.mostrarMensaje("Solicitud cancelada");
			cargarDatosSolicitudes();
		}
	}
	
	// ==================== Listeners Panel Crear Solicitud ====================
	
	private void registrarListenersPanelCrearSolicitud() {
		if (panelCrearSolicitud == null) return;
		
		panelCrearSolicitud.registrarListenerCrear(e -> crearSolicitud());
		panelCrearSolicitud.registrarListenerLimpiar(e -> limpiarFormularioSolicitud());
		panelCrearSolicitud.registrarListenerCancelar(e -> navegarASolicitudes());
	}
	
	private void cargarComboBoxesSolicitud() {
		if (panelCrearSolicitud == null) return;
		
		// TODO: Cargar datos reales del modelo
		// Por ahora está con datos de ejemplo en el panel
	}
	
	private void crearSolicitud() {
		if (panelCrearSolicitud == null || controlador == null) return;
		
		String nombre = panelCrearSolicitud.getNombreCliente();
		if (nombre.isEmpty()) {
			panelCrearSolicitud.mostrarError("El nombre del cliente es requerido");
			return;
		}
		
		String ubicacion = panelCrearSolicitud.getUbicacion();
		if (ubicacion.isEmpty()) {
			panelCrearSolicitud.mostrarError("La ubicación es requerida");
			return;
		}
		
		if (panelCrearSolicitud.confirmar("¿Crear solicitud para " + nombre + "?")) {
			try {
				// Obtener datos del formulario
				String zona = panelCrearSolicitud.getZona();
				String tipoServicioStr = panelCrearSolicitud.getTipoServicio();
				String prioridadStr = panelCrearSolicitud.getPrioridad();
				
				// Convertir a enums
				TipoServicio tipoServicio = TipoServicio.valueOf(tipoServicioStr);
				Prioridad prioridad = Prioridad.valueOf(prioridadStr);
				
				// Crear cliente
                controlador.agregarCliente(nombre, panelCrearSolicitud.getTelefonoCliente(), panelCrearSolicitud.getTipoCliente());
				Cliente cliente = new Cliente(nombre, panelCrearSolicitud.getTelefonoCliente(), panelCrearSolicitud.getTipoCliente());
				
				// Crear solicitud
				Solicitud solicitud = new Solicitud(zona, tipoServicio, prioridad, cliente);
				
				// Agregar al controlador
				controlador.agregarSolicitud(solicitud);
				
				panelCrearSolicitud.mostrarMensaje("Solicitud creada exitosamente");
				limpiarFormularioSolicitud();
				navegarASolicitudes();
			} catch (IllegalArgumentException e) {
				panelCrearSolicitud.mostrarError("Error: Valores inválidos en el formulario");
			}
		}
	}
	
	private void limpiarFormularioSolicitud() {
		if (panelCrearSolicitud == null) return;
		panelCrearSolicitud.limpiarFormulario();
	}
	
	// ==================== Listeners Panel Vehículos ====================
	
	private void registrarListenersPanelVehiculos() {
		if (panelVehiculos == null) return;
		
		panelVehiculos.registrarListenerActualizar(e -> cargarDatosVehiculos());
		panelVehiculos.registrarListenerAsignar(e -> asignarVehiculo());
	}
	
	private void cargarDatosVehiculos() {
		if (panelVehiculos == null || controlador == null) return;
		
		panelVehiculos.limpiarTabla();
		
		// Cargar vehículos desde el controlador principal
		Vehiculo[] vehiculos = controlador.obtenerTodosVehiculos();
		if (vehiculos != null) {
			for (Vehiculo v : vehiculos) {
				if (v != null) {
					panelVehiculos.agregarFila(
						v.getId().toString(),
						v.getTipo().toString(),
						v.getEstado().toString(),
						v.getZona()
					);
				}
			}
		}
		
		Vehiculo[] disponibles = controlador.obtenerVehiculosDisponibles();
		int totalDisponibles = disponibles != null ? disponibles.length : 0;
		panelVehiculos.actualizarEstadisticas(controlador.getTotalVehiculos(), totalDisponibles);
	}
	
	private void verDetallesVehiculo() {
		String id = panelVehiculos.getIdVehiculoSeleccionado();
		if (id == null) {
			panelVehiculos.mostrarError("Selecciona un vehículo primero");
			return;
		}
		// TODO: Cargar detalles del modelo
		panelVehiculos.mostrarMensaje("Detalles del vehículo: " + id);
	}
	
	private void asignarVehiculo() {
		String id = panelVehiculos.getIdVehiculoSeleccionado();
		if (id == null) {
			panelVehiculos.mostrarError("Selecciona un vehículo primero");
			return;
		}
		// TODO: Ir a panel de solicitudes y asignar vehículo
		panelVehiculos.mostrarMensaje("Asignando vehículo: " + id);
	}
	
	// ==================== Listeners Panel Crear Vehículos ====================
	
	private void registrarListenersPanelCrearVehiculos() {
		if (panelCrearVehiculos == null) return;
		
		panelCrearVehiculos.registrarListenerCrearVehiculo(e -> crearVehiculo());
		panelCrearVehiculos.registrarListenerLimpiarVehiculo(e -> limpiarFormularioVehiculo());
		panelCrearVehiculos.registrarListenerCrearTecnico(e -> crearTecnico());
		panelCrearVehiculos.registrarListenerLimpiarTecnico(e -> limpiarFormularioTecnico());
	}
	
	private void crearVehiculo() {
		if (panelCrearVehiculos == null || controlador == null) return;
		
		String placa = panelCrearVehiculos.getPlacaVehiculo();
		if (placa.isEmpty()) {
			panelCrearVehiculos.mostrarError("La placa es requerida");
			return;
		}
		
		String zona = panelCrearVehiculos.getZonaVehiculo();
		String tipoStr = panelCrearVehiculos.getTipoVehiculo();
		
		try {
			TipoVehiculo tipo = TipoVehiculo.valueOf(tipoStr);
			
			if (panelCrearVehiculos.confirmar("¿Crear vehículo con placa " + placa + "?")) {
				controlador.agregarVehiculo(tipo, zona);
				panelCrearVehiculos.mostrarMensaje("Vehículo creado exitosamente");
				limpiarFormularioVehiculo();
			}
		} catch (IllegalArgumentException e) {
			panelCrearVehiculos.mostrarError("Tipo de vehículo inválido");
		}
	}
	
	private void limpiarFormularioVehiculo() {
		if (panelCrearVehiculos == null) return;
		panelCrearVehiculos.limpiarFormularioVehiculo();
	}
	
	private void crearTecnico() {
		if (panelCrearVehiculos == null || controlador == null) return;
		
		String nombre = panelCrearVehiculos.getNombreTecnico();
		if (nombre.isEmpty()) {
			panelCrearVehiculos.mostrarError("El nombre del técnico es requerido");
			return;
		}
		
		String zona = panelCrearVehiculos.getZonaTecnico();
		String especialidadStr = panelCrearVehiculos.getEspecialidadTecnico();
		
		try {
			Especialidad especialidad = Especialidad.valueOf(especialidadStr);
			
			if (panelCrearVehiculos.confirmar("¿Crear técnico " + nombre + "?")) {
				controlador.agregarTecnico(nombre, zona, especialidad);
				panelCrearVehiculos.mostrarMensaje("Técnico creado exitosamente");
				limpiarFormularioTecnico();
			}
		} catch (IllegalArgumentException e) {
			panelCrearVehiculos.mostrarError("Especialidad inválida");
		}
	}
	
	private void limpiarFormularioTecnico() {
		if (panelCrearVehiculos == null) return;
		panelCrearVehiculos.limpiarFormularioTecnico();
	}
	
	// ==================== Listeners Panel Operaciones ====================
	
	private void registrarListenersPanelOperaciones() {
		if (panelOperaciones == null) return;
		
		panelOperaciones.registrarListenerActualizar(e -> cargarDatosOperaciones());
		panelOperaciones.registrarListenerVerDetalles(e -> verDetallesOperacion());
		panelOperaciones.registrarListenerDeshacer(e -> deshacerOperacion());
	}
	
	private void cargarDatosOperaciones() {
		if (panelOperaciones == null || controlador == null) return;
		
		panelOperaciones.limpiarTabla();
		
		// Cargar operaciones desde el controlador principal
		Operacion[] operaciones = controlador.obtenerTodasOperaciones();
		if (operaciones != null) {
			for (int i = 0; i < operaciones.length; i++) {
				Operacion op = operaciones[i];
				if (op != null) {
					panelOperaciones.agregarOperacion(
						String.valueOf(i + 1),
						op.getTipo().toString(),
						"Objeto", // TODO: Obtener del modelo
						op.getEstadoAnterior().toString(),
						op.getHora().toString()
					);
				}
			}
		}
		
		panelOperaciones.actualizarTotal(controlador.getTotalOperaciones());
	}
	
	private void verDetallesOperacion() {
		String orden = panelOperaciones.getOrdenSeleccionada();
		if (orden == null) {
			panelOperaciones.mostrarError("Selecciona una operación primero");
			return;
		}
		// TODO: Cargar detalles del modelo
	}
	
	private void deshacerOperacion() {
		// TODO: Deshacer operación del modelo
		panelOperaciones.mostrarMensaje("Operación deshecha");
		cargarDatosOperaciones();
	}
	
	// ==================== Listeners Panel Técnicos ====================
	
	private void registrarListenersPanelTecnicos() {
		if (panelTecnicos == null) return;
		
		panelTecnicos.registrarListenerActualizar(e -> cargarDatosTecnicos());
		panelTecnicos.registrarListenerAsignar(e -> asignarTecnico());
	}
	
	private void cargarDatosTecnicos() {
		if (panelTecnicos == null || controlador == null) return;
		
		panelTecnicos.limpiarTabla();
		
		// Cargar técnicos desde el controlador principal
		Tecnico[] tecnicos = controlador.obtenerTodosTecnicos();
		if (tecnicos != null) {
			for (Tecnico t : tecnicos) {
				if (t != null) {
					panelTecnicos.agregarFila(
						t.getId().toString(),
						t.getNombre(),
						t.getEspecialidad().toString(),
						t.getEstado().toString(),
						t.getZona()
					);
				}
			}
		}
		
		Tecnico[] disponibles = controlador.obtenerTecnicosDisponibles();
		int totalDisponibles = disponibles != null ? disponibles.length : 0;
		panelTecnicos.actualizarEstadisticas(controlador.getTotalTecnicos(), totalDisponibles);
	}
	
	private void verDetallesTecnico() {
		String id = panelTecnicos.getIdTecnicoSeleccionado();
		if (id == null) {
			panelTecnicos.mostrarError("Selecciona un técnico primero");
			return;
		}
		// TODO: Cargar detalles del modelo
		panelTecnicos.mostrarMensaje("Detalles del técnico: " + id);
	}
	
	private void asignarTecnico() {
		String id = panelTecnicos.getIdTecnicoSeleccionado();
		if (id == null) {
			panelTecnicos.mostrarError("Selecciona un técnico primero");
			return;
		}
		// TODO: Ir a panel de solicitudes y asignar técnico
		panelTecnicos.mostrarMensaje("Asignando técnico: " + id);
	}
	
	/**
	 * Obtiene la ventana para acceso externo si es necesario
	 */
	public VentanaPrincipal getVentana() {
		return ventana;
	}
}
