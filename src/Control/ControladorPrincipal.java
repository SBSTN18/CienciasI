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

/**
 * Controlador principal del sistema AutoRescate 24/7.
 * Coordina la gestión de vehículos, técnicos, clientes, solicitudes,
 * inventario de kits y repuestos, operaciones y generación de reportes.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ControladorPrincipal {

    private ColaSolicitudControl colaSolicitudes;
    private ListaTecnicosControl listaTecnicos;
    private ListaVehiculosControl listaVehiculos;
    private ListaEnProceso listaEnProceso;
    private PilaOperacionesControl pilaOperaciones;
    private PilaKitsControl pilaKits;
    private PilaRepuestoControl pilaRepuesto;
    private ExportarCSVControl csvControl;

    /**
     * Inicializa todos los controladores del sistema.
     */
    public ControladorPrincipal() {
        this.colaSolicitudes = new ColaSolicitudControl();
        this.listaTecnicos = new ListaTecnicosControl();
        this.listaVehiculos = new ListaVehiculosControl();
        this.listaEnProceso = new ListaEnProceso();
        this.pilaOperaciones = new PilaOperacionesControl();
        this.pilaKits = new PilaKitsControl();
        this.pilaRepuesto = new PilaRepuestoControl();
        this.csvControl = new ExportarCSVControl();
    }

    /**
     * Registra un nuevo vehículo.
     *
     * @param tipo tipo de vehículo.
     * @param zona zona de operación.
     */
    public void registrarVehiculo(TipoVehiculo tipo, String zona) {
        listaVehiculos.agregarVehiculo(tipo, zona);
    }

    /**
     * Obtiene todos los vehículos registrados.
     *
     * @return arreglo de vehículos.
     */
    public Vehiculo[] obtenerTodosVehiculos() {
        return listaVehiculos.obtenerTodos();
    }

    /**
     * Obtiene los vehículos filtrados por estado.
     *
     * @param estado estado a consultar.
     * @return arreglo de vehículos.
     */
    public Vehiculo[] obtenerVehiculosPorEstado(Estado estado) {
        return listaVehiculos.obtenerPorEstado(estado);
    }

    /**
     * Busca un vehículo disponible según tipo y zona.
     *
     * @param tipo tipo requerido.
     * @param zona zona requerida.
     * @return vehículo encontrado o null.
     */
    public Vehiculo buscarVehiculoDisponible(TipoVehiculo tipo, String zona) {
        return listaVehiculos.buscarDisponible(tipo, zona);
    }

    /**
     * Obtiene la cantidad total de vehículos.
     *
     * @return total de vehículos registrados.
     */
    public int getTotalVehiculos() {
        return listaVehiculos.getTotalVehiculos();
    }

    /**
     * Registra un nuevo técnico.
     *
     * @param nombre nombre del técnico.
     * @param zona zona de operación.
     * @param especialidad especialidad técnica.
     */
    public void registrarTecnico(String nombre, String zona, Especialidad especialidad) {
        listaTecnicos.agregarTecnico(nombre, zona, especialidad);
    }

    /**
     * Obtiene todos los técnicos registrados.
     *
     * @return arreglo de técnicos.
     */
    public Tecnico[] obtenerTodosTecnicos() {
        return listaTecnicos.obtenerTodos();
    }

    /**
     * Obtiene técnicos filtrados por estado.
     *
     * @param estado estado a consultar.
     * @return arreglo de técnicos.
     */
    public Tecnico[] obtenerTecnicosPorEstado(Estado estado) {
        return listaTecnicos.obtenerPorEstado(estado);
    }

    /**
     * Busca un técnico disponible.
     *
     * @param especialidad especialidad requerida.
     * @param zona zona requerida.
     * @return técnico encontrado o null.
     */
    public Tecnico buscarTecnicoDisponible(Especialidad especialidad, String zona) {
        return listaTecnicos.buscarDisponible(especialidad, zona);
    }

    /**
     * Obtiene el total de técnicos registrados.
     *
     * @return cantidad de técnicos.
     */
    public int getTotalTecnicos() {
        return listaTecnicos.getTotalTecnicos();
    }

    /**
     * Registra un cliente.
     *
     * @param nombre nombre del cliente.
     * @param telefono teléfono de contacto.
     * @param tipo tipo de cliente.
     * @return cliente creado.
     */
    public Cliente registrarCliente(String nombre, String telefono, TipoCliente tipo) {
        return new Cliente(nombre, telefono, tipo);
    }

    /**
     * Crea una nueva solicitud de asistencia.
     *
     * @param zona zona donde se requiere el servicio.
     * @param servicio tipo de servicio solicitado.
     * @param prioridad prioridad de atención.
     * @param cliente cliente asociado.
     */
    public void crearSolicitud(String zona, TipoServicio servicio,
                               Prioridad prioridad, Cliente cliente) {
        Solicitud solicitud = new Solicitud(zona, servicio, prioridad, cliente);
        colaSolicitudes.agregarSolicitud(solicitud);
    }

    /**
     * Obtiene la siguiente solicitud según prioridad.
     *
     * @return siguiente solicitud disponible.
     */
    public Solicitud obtenerSiguienteSolicitud() {
        return colaSolicitudes.obtenerSiguienteSolicitud();
    }

    /**
     * Obtiene todas las solicitudes pendientes.
     *
     * @return arreglo de solicitudes pendientes.
     */
    public Solicitud[] obtenerSolicitudesPendientes() {
        return colaSolicitudes.obtenerPendientes();
    }

    /**
     * Verifica si existen solicitudes críticas pendientes.
     *
     * @return true si existen solicitudes críticas.
     */
    public boolean haySolicitudesCriticas() {
        return colaSolicitudes.hayCriticas();
    }

    /**
     * Obtiene el total de solicitudes pendientes.
     *
     * @return cantidad de solicitudes.
     */
    public int getTotalSolicitudes() {
        return colaSolicitudes.getTotalSolicitudes();
    }

    /**
     * Asigna un vehículo a una solicitud.
     *
     * @param solicitud solicitud a atender.
     * @param vehiculo vehículo asignado.
     * @return true si la asignación fue exitosa.
     */
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

    /**
     * Asigna un técnico a una solicitud.
     *
     * @param solicitud solicitud a atender.
     * @param tecnico técnico asignado.
     * @return true si la asignación fue exitosa.
     */
    public boolean asignarTecnico(Solicitud solicitud, Tecnico tecnico) {
        if (solicitud == null || tecnico == null) return false;
        if (tecnico.getEstado() != Estado.DISPONIBLE) return false;

        Estado estadoAnterior = tecnico.getEstado();
        solicitud.setTecnicoAsignado(tecnico);
        tecnico.setEstado(Estado.OCUPADO);
        pilaOperaciones.registrarAsignacionTecnico(solicitud, tecnico);
        pilaOperaciones.registrarCambioEstadoTecnico(tecnico, estadoAnterior);

        if (solicitud.tieneRecursosAsignados()) {
            listaEnProceso.agregarEnProceso(solicitud);
        }

        return true;
    }

    /**
     * Cierra una solicitud y libera los recursos asignados.
     *
     * @param solicitud solicitud a cerrar.
     * @return true si el cierre fue exitoso.
     */
    public boolean cerrarSolicitud(Solicitud solicitud) {
        if (solicitud == null) return false;
        if (!solicitud.tieneRecursosAsignados()) return false;

        pilaOperaciones.registrarCierreSolicitud(solicitud);
        solicitud.cerrar();
        solicitud.getVehiculoAsignado().setEstado(Estado.DISPONIBLE);
        solicitud.getTecnicoAsignado().setEstado(Estado.DISPONIBLE);
        colaSolicitudes.agregarCerrada(solicitud);
        listaEnProceso.cerrarSolicitud(solicitud);

        return true;
    }

    /**
     * Obtiene las solicitudes actualmente en proceso.
     *
     * @return arreglo de solicitudes en proceso.
     */
    public Solicitud[] obtenerSolicitudesEnProceso() {
        return listaEnProceso.obtenerEnProceso();
    }

    /**
     * Deshace la última operación registrada.
     *
     * @return true si la operación fue revertida.
     */
    public boolean deshacerUltimaOperacion() {
        return pilaOperaciones.deshacerUltimaOperacion();
    }

    /**
     * Obtiene la última operación realizada.
     *
     * @return última operación registrada.
     */
    public Operacion verUltimaOperacion() {
        return pilaOperaciones.verUltimaOperacion();
    }

    /**
     * Verifica si existen operaciones registradas.
     *
     * @return true si existen operaciones.
     */
    public boolean hayOperaciones() {
        return pilaOperaciones.hayOperaciones();
    }

    /**
     * Obtiene el total de operaciones registradas.
     *
     * @return cantidad de operaciones.
     */
    public int getTotalOperaciones() {
        return pilaOperaciones.getTotalOperaciones();
    }

    /**
     * Agrega un kit al inventario.
     *
     * @param cantidadElementos cantidad de elementos del kit.
     */
    public void agregarKit(int cantidadElementos) {
        pilaKits.agregarKit(cantidadElementos);
    }

    /**
     * Retira un kit del inventario.
     *
     * @return kit retirado.
     */
    public Kit retirarKit() {
        return pilaKits.retirarKit();
    }

    /**
     * Verifica si existen kits disponibles.
     *
     * @return true si hay kits.
     */
    public boolean hayKits() {
        return pilaKits.hayKits();
    }

    /**
     * Obtiene el total de kits almacenados.
     *
     * @return cantidad de kits.
     */
    public int getTotalKits() {
        return pilaKits.getTotalKits();
    }

    /**
     * Agrega un repuesto al inventario.
     *
     * @param nombre nombre del repuesto.
     * @param cantidad cantidad disponible.
     */
    public void agregarRepuesto(String nombre, int cantidad) {
        pilaRepuesto.agregarRepuesto(nombre, cantidad);
    }

    /**
     * Retira un repuesto del inventario.
     *
     * @return repuesto retirado.
     */
    public Repuesto retirarRepuesto() {
        return pilaRepuesto.retirarRepuesto();
    }

    /**
     * Verifica si existen repuestos disponibles.
     *
     * @return true si hay repuestos.
     */
    public boolean hayRepuestos() {
        return pilaRepuesto.hayRepuestos();
    }

    /**
     * Obtiene el total de repuestos almacenados.
     *
     * @return cantidad de repuestos.
     */
    public int getTotalRepuestos() {
        return pilaRepuesto.getTotalRepuestos();
    }

    /**
     * Exporta las solicitudes cerradas a un archivo CSV.
     *
     * @param rutaArchivo ruta de destino.
     * @return true si la exportación fue exitosa.
     */
    public boolean exportarCSV(String rutaArchivo) {
        return csvControl.exportarSolicitudes(
                colaSolicitudes.obtenerCerradas(),
                rutaArchivo
        );
    }
}