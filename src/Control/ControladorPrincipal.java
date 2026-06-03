package Control;

import modelo.Cliente;
import modelo.Solicitud;
import modelo.Tecnico;
import modelo.Vehiculo;
import modelo.Kits.Kit;
import modelo.Kits.Repuesto;
import modelo.Operacion;
import modelo.Enums.Estado;
import modelo.Enums.Especialidad;
import modelo.Enums.Prioridad;
import modelo.Enums.TipoCliente;
import modelo.Enums.TipoServicio;
import modelo.Enums.TipoVehiculo;

public class ControladorPrincipal {

    private ColaSolicitudControl colaSolicitudes;
    private ListaTecnicosControl listaTecnicos;
    private ListaVehiculosControl listaVehiculos;
    private PilaOperacionesControl pilaOperaciones;
    private PilaKitsControl pilaKits;
    private PilaRepuestoControl pilaRepuesto;
    private ExportarCSVControl csvControl;
    

    public ControladorPrincipal() {
        this.colaSolicitudes = new ColaSolicitudControl();
        this.listaTecnicos = new ListaTecnicosControl();
        this.listaVehiculos = new ListaVehiculosControl();
        this.pilaOperaciones = new PilaOperacionesControl();
        this.pilaKits = new PilaKitsControl();
        this.pilaRepuesto = new PilaRepuestoControl();
        this.csvControl = new ExportarCSVControl();
    }

    // ==================== PASO 1: VEHÍCULOS ====================

    public void registrarVehiculo(TipoVehiculo tipo, String zona) {
        listaVehiculos.agregarVehiculo(tipo, zona);
    }

    public Vehiculo[] obtenerTodosVehiculos() {
        return listaVehiculos.obtenerTodos();
    }

    public Vehiculo[] obtenerVehiculosPorEstado(Estado estado) {
        return listaVehiculos.obtenerPorEstado(estado);
    }

    public Vehiculo buscarVehiculoDisponible(TipoVehiculo tipo, String zona) {
        return listaVehiculos.buscarDisponible(tipo, zona);
    }

    public int getTotalVehiculos() {
        return listaVehiculos.getTotalVehiculos();
    }

    // ==================== PASO 2: TÉCNICOS ====================

    public void registrarTecnico(String nombre, String zona, Especialidad especialidad) {
        listaTecnicos.agregarTecnico(nombre, zona, especialidad);
    }

    public Tecnico[] obtenerTodosTecnicos() {
        return listaTecnicos.obtenerTodos();
    }

    public Tecnico[] obtenerTecnicosPorEstado(Estado estado) {
        return listaTecnicos.obtenerPorEstado(estado);
    }

    public Tecnico buscarTecnicoDisponible(Especialidad especialidad, String zona) {
        return listaTecnicos.buscarDisponible(especialidad, zona);
    }

    public int getTotalTecnicos() {
        return listaTecnicos.getTotalTecnicos();
    }

    // ==================== PASO 3: CLIENTES ====================

    public Cliente registrarCliente(String nombre, String telefono, TipoCliente tipo) {
        return new Cliente(nombre, telefono, tipo);
    }

    // ==================== PASO 4: SOLICITUDES ====================

    public void crearSolicitud(String zona, TipoServicio servicio,
                               Prioridad prioridad, Cliente cliente) {
        Solicitud solicitud = new Solicitud(zona, servicio, prioridad, cliente);
        colaSolicitudes.agregarSolicitud(solicitud);
    }

    public Solicitud obtenerSiguienteSolicitud() {
        return colaSolicitudes.obtenerSiguienteSolicitud();
    }

    public Solicitud[] obtenerSolicitudesPendientes() {
        return colaSolicitudes.obtenerPendientes();
    }

    public boolean haySolicitudesCriticas() {
        return colaSolicitudes.hayCriticas();
    }

    public int getTotalSolicitudes() {
        return colaSolicitudes.getTotalSolicitudes();
    }

    // ==================== PASO 5: ASIGNAR RECURSOS ====================

    public boolean asignarVehiculo(Solicitud solicitud, Vehiculo vehiculo) {
        if (solicitud == null || vehiculo == null) return false;
        if (vehiculo.getEstado() != Estado.DISPONIBLE) return false;

        Estado estadoAnterior = vehiculo.getEstado();
        solicitud.setVehiculoAsignado(vehiculo);
        vehiculo.setEstado(Estado.OCUPADO);
        pilaOperaciones.registrarAsignacionVehiculo(solicitud, vehiculo);
        pilaOperaciones.registrarCambioEstadoVehiculo(vehiculo, estadoAnterior);
        return true;
    }

    public boolean asignarTecnico(Solicitud solicitud, Tecnico tecnico) {
        if (solicitud == null || tecnico == null) return false;
        if (tecnico.getEstado() != Estado.DISPONIBLE) return false;

        Estado estadoAnterior = tecnico.getEstado();
        solicitud.setTecnicoAsignado(tecnico);
        tecnico.setEstado(Estado.OCUPADO);
        pilaOperaciones.registrarAsignacionTecnico(solicitud, tecnico);
        pilaOperaciones.registrarCambioEstadoTecnico(tecnico, estadoAnterior);
        return true;
    }

    // ==================== PASO 6: CERRAR SOLICITUD ====================

    public boolean cerrarSolicitud(Solicitud solicitud) {
        if (solicitud == null) return false;
        if (!solicitud.tieneRecursosAsignados()) return false;

        pilaOperaciones.registrarCierreSolicitud(solicitud);
        solicitud.cerrar();
        solicitud.getVehiculoAsignado().setEstado(Estado.DISPONIBLE);
        solicitud.getTecnicoAsignado().setEstado(Estado.DISPONIBLE);
        colaSolicitudes.agregarCerrada(solicitud); 
        return true;
    }

    // ==================== PASO 7: DESHACER OPERACIONES ====================

    public boolean deshacerUltimaOperacion() {
        return pilaOperaciones.deshacerUltimaOperacion();
    }

    public Operacion verUltimaOperacion() {
        return pilaOperaciones.verUltimaOperacion();
    }

    public boolean hayOperaciones() {
        return pilaOperaciones.hayOperaciones();
    }

    public int getTotalOperaciones() {
        return pilaOperaciones.getTotalOperaciones();
    }

    // ==================== PASO 8: KITS ====================

    public void agregarKit(int cantidadElementos) {
        pilaKits.agregarKit(cantidadElementos);
    }

    public Kit retirarKit() {
        return pilaKits.retirarKit();
    }

    public boolean hayKits() {
        return pilaKits.hayKits();
    }

    public int getTotalKits() {
        return pilaKits.getTotalKits();
    }

    // ==================== PASO 8: REPUESTOS ====================

    public void agregarRepuesto(String nombre, int cantidad) {
        pilaRepuesto.agregarRepuesto(nombre, cantidad);
    }

    public Repuesto retirarRepuesto() {
        return pilaRepuesto.retirarRepuesto();
    }

    public boolean hayRepuestos() {
        return pilaRepuesto.hayRepuestos();
    }

    public int getTotalRepuestos() {
        return pilaRepuesto.getTotalRepuestos();
    }

    // ==================== PASO 9: EXPORTAR CSV ====================

    public boolean exportarCSV(String rutaArchivo) {
    return csvControl.exportarSolicitudes(colaSolicitudes.obtenerCerradas(), rutaArchivo);
}
}