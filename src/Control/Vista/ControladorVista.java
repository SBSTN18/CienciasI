package Control.Vista;

import Control.ControladorPrincipal;
import modelo.Solicitud;
import modelo.Tecnico;
import modelo.Vehiculo;
import modelo.Enums.Estado;
import modelo.Enums.Especialidad;
import modelo.Enums.Prioridad;
import modelo.Enums.TipoCliente;
import modelo.Enums.TipoServicio;
import modelo.Enums.TipoVehiculo;
import modelo.Cliente;
import modelo.Operacion;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.io.File;

/**
 * Controlador encargado de gestionar la interacción entre la vista
 * principal y la lógica de negocio del sistema AutoRescate 24/7.
 * Administra los eventos de los paneles, validaciones de datos
 * y actualización de la interfaz gráfica.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ControladorVista {

    private ControladorPrincipal controlador;
    private VentanaPrincipal ventana;

    /**
     * Inicializa el controlador de la interfaz.
     *
     * @param ventana ventana principal del sistema.
     * @param controlador controlador principal de negocio.
     */ 
    public ControladorVista(VentanaPrincipal ventana, ControladorPrincipal controlador) {
        this.ventana = ventana;
        this.controlador = controlador;
        initEventos();
    }

    /**
     * Registra todos los eventos de la interfaz.
     */
    private void initEventos() {
        initEventosVehiculos();
        initEventosTecnicos();
        initEventosSolicitudes();
        initEventosKits();
        initEventosReporte();
    }

    /**
     * Configura los eventos del panel de vehículos.
     */
    private void initEventosVehiculos() {
        ventana.getPanelVehiculos().getBtnRegistrar().addActionListener(e -> {
            registrarVehiculo();
        });
    }

    /**
     * Registra un vehículo en el sistema.
     */
    private void registrarVehiculo() {
        TipoVehiculo tipo = ventana.getPanelVehiculos().getTipoSeleccionado();
        String zona = ventana.getPanelVehiculos().getZona();
        if (zona.isEmpty()) {
            ventana.mostrarError("La zona no puede estar vacía");
            return;
        }
        controlador.registrarVehiculo(tipo, zona);
        ventana.mostrarMensaje("Vehículo registrado correctamente");
        ventana.getPanelVehiculos().limpiarFormulario();
        cargarTablaVehiculos();
    }

    /**
     * Actualiza la tabla de vehículos registrados.
     */
    private void cargarTablaVehiculos() {
        Vehiculo[] vehiculos = controlador.obtenerTodosVehiculos();
        ventana.getPanelVehiculos().cargarTabla(vehiculos);
    }


    /**
     * Carga los vehículos disponibles para asignación.
     */
    private void cargarVehiculosDisponibles() {
        Vehiculo[] disponibles = controlador.obtenerVehiculosPorEstado(Estado.DISPONIBLE);
        ventana.getPanelSolicitudes().cargarVehiculosDisponibles(disponibles);
    }

    
    /**
     * Configura los eventos del panel de técnicos.
     */
    private void initEventosTecnicos() {
        ventana.getPanelTecnicos().getBtnRegistrar().addActionListener(e -> {
            registrarTecnico();
        });
    }

    /**
     * Registra un técnico en el sistema.
     */
    private void registrarTecnico() {
        String nombre = ventana.getPanelTecnicos().getNombre();
        String zona = ventana.getPanelTecnicos().getZona();
        Especialidad especialidad = ventana.getPanelTecnicos().getEspecialidadSeleccionada();
        if (nombre.isEmpty()) {
            ventana.mostrarError("El nombre no puede estar vacío");
            return;
        }
        if (zona.isEmpty()) {
            ventana.mostrarError("La zona no puede estar vacía");
            return;
        }
        controlador.registrarTecnico(nombre, zona, especialidad);
        ventana.mostrarMensaje("Técnico registrado correctamente");
        ventana.getPanelTecnicos().limpiarFormulario();
        cargarTablaTecnicos();
    }

    /**
     * Actualiza la tabla de técnicos registrados.
     */
    private void cargarTablaTecnicos() {
        Tecnico[] tecnicos = controlador.obtenerTodosTecnicos();
        ventana.getPanelTecnicos().cargarTabla(tecnicos);
    }

    /**
     * Carga los técnicos disponibles para asignación.
     */
    private void cargarTecnicosDisponibles() {
        Tecnico[] disponibles = controlador.obtenerTecnicosPorEstado(Estado.DISPONIBLE);
        ventana.getPanelSolicitudes().cargarTecnicosDisponibles(disponibles);
    }

    /**
     * Configura los eventos del panel de solicitudes.
     */
    private void initEventosSolicitudes() {
        ventana.getPanelSolicitudes().getBtnCrearSolicitud().addActionListener(e -> {
            crearSolicitud();
        });
        ventana.getPanelSolicitudes().getBtnSiguienteSolicitud().addActionListener(e -> {
            obtenerSiguienteSolicitud();
        });
        ventana.getPanelSolicitudes().getBtnAsignarRecursos().addActionListener(e -> {
            asignarRecursos();
        });
        ventana.getPanelSolicitudes().getBtnCerrarSolicitud().addActionListener(e -> {
            cerrarSolicitud();
        });
		
    }

    /**
     * Registra una nueva solicitud de servicio.
     */
    private void crearSolicitud() {
        String zona = ventana.getPanelSolicitudes().getZona();
        String nombreCliente = ventana.getPanelSolicitudes().getNombreCliente();
        String telefonoCliente = ventana.getPanelSolicitudes().getTelefonoCliente();
        TipoServicio servicio = ventana.getPanelSolicitudes().getServicioSeleccionado();
        Prioridad prioridad = ventana.getPanelSolicitudes().getPrioridadSeleccionada();
        TipoCliente tipoCliente = ventana.getPanelSolicitudes().getTipoClienteSeleccionado();

        if (zona.isEmpty()) {
            ventana.mostrarError("La zona no puede estar vacía");
            return;
        }
        if (nombreCliente.isEmpty()) {
            ventana.mostrarError("El nombre del cliente no puede estar vacío");
            return;
        }

        Cliente cliente = controlador.registrarCliente(nombreCliente, telefonoCliente, tipoCliente);
        controlador.crearSolicitud(zona, servicio, prioridad, cliente);
        ventana.mostrarMensaje("Solicitud creada correctamente");
        ventana.getPanelSolicitudes().limpiarFormulario();
        cargarTablaSolicitudes();

        if (controlador.haySolicitudesCriticas()) {
            ventana.mostrarAlerta("Hay solicitudes críticas pendientes");
        }
    }

    /**
     * Obtiene la siguiente solicitud pendiente según prioridad.
     */
    private void obtenerSiguienteSolicitud() {
        Solicitud siguiente = controlador.obtenerSiguienteSolicitud();
        if (siguiente == null) {
            ventana.mostrarError("No hay solicitudes pendientes");
            return;
        }
        ventana.getPanelSolicitudes().mostrarSolicitud(siguiente);
        cargarVehiculosDisponibles();
        cargarTecnicosDisponibles();
    }

    /**
     * Asigna vehículo y técnico a una solicitud.
     */
    private void asignarRecursos() {
        Solicitud solicitud = ventana.getPanelSolicitudes().getSolicitudActual();
        Vehiculo vehiculo = ventana.getPanelSolicitudes().getVehiculoSeleccionado();
        Tecnico tecnico = ventana.getPanelSolicitudes().getTecnicoSeleccionado();

        if (solicitud == null) {
            ventana.mostrarError("Debe obtener la siguiente solicitud primero");
            return;
        }
        if (vehiculo == null) {
            ventana.mostrarError("No hay vehículos disponibles");
            return;
        }
        if (tecnico == null) {
            ventana.mostrarError("No hay técnicos disponibles");
            return;
        }

        boolean vehiculoAsignado = controlador.asignarVehiculo(solicitud, vehiculo);
        if (!vehiculoAsignado) {
            ventana.mostrarError("El vehículo no está disponible");
            return;
        }
        boolean tecnicoAsignado = controlador.asignarTecnico(solicitud, tecnico);
        if (!tecnicoAsignado) {
            ventana.mostrarError("El técnico no está disponible");
            return;
        }

        ventana.mostrarMensaje("Recursos asignados correctamente");
		ventana.getPanelSolicitudes().limpiarAsignacion();
        cargarTablaSolicitudes();
        cargarTablaVehiculos();
        cargarTablaTecnicos();
        cargarSolicitudesEnProceso();
    }

    /**
     * Cierra una solicitud en proceso.
     */
    private void cerrarSolicitud() {
        Solicitud solicitud = ventana.getPanelSolicitudes().getSolicitudACerrar();
		if (solicitud == null) {
			ventana.mostrarError("No hay solicitud activa");
			return;
		}
		boolean cerrada = controlador.cerrarSolicitud(solicitud);
		if (!cerrada) {
			ventana.mostrarError("La solicitud no tiene recursos asignados");
			return;
		}
		ventana.mostrarMensaje("Solicitud cerrada correctamente");
		ventana.getPanelSolicitudes().limpiarAsignacion();
		cargarTablaSolicitudes();
		cargarTablaVehiculos();
		cargarTablaTecnicos();
		cargarSolicitudesEnProceso();
    }

    /**
     * Actualiza la lista de solicitudes en proceso.
     */
	private void cargarSolicitudesEnProceso() {
		Solicitud[] enProceso = controlador.obtenerSolicitudesEnProceso();
		ventana.getPanelSolicitudes().cargarSolicitudesEnProceso(enProceso);
	}

    /**
     * Actualiza la tabla de solicitudes pendientes.
     */
    private void cargarTablaSolicitudes() {
        Solicitud[] solicitudes = controlador.obtenerSolicitudesPendientes();
        ventana.getPanelSolicitudes().cargarTabla(solicitudes);
    }

    /**
     * Configura los eventos del panel de kits y repuestos.
     */
    private void initEventosKits() {
        ventana.getPanelKits().getBtnAgregarKit().addActionListener(e -> {
            agregarKit();
        });
        ventana.getPanelKits().getBtnRetirarKit().addActionListener(e -> {
            retirarKit();
        });
        ventana.getPanelKits().getBtnAgregarRepuesto().addActionListener(e -> {
            agregarRepuesto();
        });
        ventana.getPanelKits().getBtnRetirarRepuesto().addActionListener(e -> {
            retirarRepuesto();
        });
    }

    /**
     * Agrega un kit al inventario.
     */
    private void agregarKit() {
        int cantidad = ventana.getPanelKits().getCantidadElementos();
        if (cantidad <= 0) {
            ventana.mostrarError("La cantidad debe ser mayor a 0");
            return;
        }
        controlador.agregarKit(cantidad);
        ventana.mostrarMensaje("Kit agregado correctamente");
        ventana.getPanelKits().limpiarFormularioKit();
        actualizarInfoKits();
    }

    /**
     * Retira un kit del inventario.
     */
    private void retirarKit() {
        if (!controlador.hayKits()) {
            ventana.mostrarError("No hay kits disponibles");
            return;
        }
        controlador.retirarKit();
        ventana.mostrarMensaje("Kit retirado correctamente");
        actualizarInfoKits();
    }

    /**
     * Agrega un repuesto al inventario.
     */
    private void agregarRepuesto() {
        String nombre = ventana.getPanelKits().getNombreRepuesto();
        int cantidad = ventana.getPanelKits().getCantidadRepuesto();
        if (nombre.isEmpty()) {
            ventana.mostrarError("El nombre del repuesto no puede estar vacío");
            return;
        }
        if (cantidad <= 0) {
            ventana.mostrarError("La cantidad debe ser mayor a 0");
            return;
        }
        controlador.agregarRepuesto(nombre, cantidad);
        ventana.mostrarMensaje("Repuesto agregado correctamente");
        ventana.getPanelKits().limpiarFormularioRepuesto();
        actualizarInfoKits();
    }

    /**
     * Retira un repuesto del inventario.
     */
    private void retirarRepuesto() {
        if (!controlador.hayRepuestos()) {
            ventana.mostrarError("No hay repuestos disponibles");
            return;
        }
        controlador.retirarRepuesto();
        ventana.mostrarMensaje("Repuesto retirado correctamente");
        actualizarInfoKits();
    }

    /**
     * Actualiza la información mostrada del inventario.
     */
    private void actualizarInfoKits() {
        ventana.getPanelKits().actualizarInfo(
            controlador.getTotalKits(),
            controlador.getTotalRepuestos()
        );
    }

    /**
     * Configura los eventos del panel de reportes.
     */
    private void initEventosReporte() {
        ventana.getPanelReporte().getBtnSeleccionarRuta().addActionListener(e -> {
            seleccionarRuta();
        });
        ventana.getPanelReporte().getBtnExportar().addActionListener(e -> {
            exportarCSV();
        });
    }

    /**
     * Permite seleccionar la ubicación donde se guardará el reporte.
     */
    private void seleccionarRuta() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte CSV");
        fileChooser.setSelectedFile(new File("reporte_dia.csv"));
        int resultado = fileChooser.showSaveDialog(ventana);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            ventana.getPanelReporte().setRutaArchivo(
                fileChooser.getSelectedFile().getAbsolutePath()
            );
        }
    }

    /**
     * Exporta el reporte diario en formato CSV.
     */
    private void exportarCSV() {
        String ruta = ventana.getPanelReporte().getRutaArchivo();
        if (ruta.isEmpty()) {
            ventana.mostrarError("Debe seleccionar una ruta para el archivo");
            return;
        }
        boolean exportado = controlador.exportarCSV(ruta);
        if (exportado) {
            ventana.getPanelReporte().mostrarEstado("Reporte exportado correctamente", true);
        } else {
            ventana.getPanelReporte().mostrarEstado("Error al exportar el reporte", false);
        }
    }
}