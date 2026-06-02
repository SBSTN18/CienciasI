package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ventana principal de la aplicación AutoRescate.
 * Implementa el patrón MVC como componente de Vista.
 * 
 * Responsabilidades:
 * - Gestionar la interfaz gráfica principal
 * - Mantener referencias a paneles
 * - Navegar entre paneles
 * 
 * SOLID: Single Responsibility - Solo gestiona la ventana principal
 */
public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int ANCHO = 1200;
	private static final int ALTO = 700;
	
	// Componentes principales
	private JPanel panelNavegacion;
	private JPanel panelContenido;
	private CardLayout layoutContenido;
	
	// Paneles de la aplicación
	private JPanel panelActual;
	
	// Botones de navegación
	private JButton btnSolicitudes;
	private JButton btnCrearSolicitud;
	private JButton btnVehiculos;
	private JButton btnCrearVehiculos;
	private JButton btnOperaciones;
	private JButton btnTecnicos;
	
	public VentanaPrincipal() {
		inicializarVentana();
		crearPanelNavegacion();
		crearPanelContenido();

	}
	
	/**
	 * Inicializa las propiedades básicas de la ventana
	 */
	private void inicializarVentana() {
		setTitle("AutoRescate - Sistema de Gestión de Rescates");
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLayout(new BorderLayout(10, 10));
	}
	
	/**
	 * Crea el panel de navegación con botones
	 */
	private void crearPanelNavegacion() {
		panelNavegacion = new JPanel();
		panelNavegacion.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panelNavegacion.setBackground(new Color(45, 45, 48));
		panelNavegacion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnSolicitudes = crearBoton("Solicitudes");
		btnCrearSolicitud = crearBoton("Nueva Solicitud");
		btnVehiculos = crearBoton("Vehículos Libres");
		btnCrearVehiculos = crearBoton("Crear Vehículos/Técnicos");
		btnOperaciones = crearBoton("Operaciones");
		btnTecnicos = crearBoton("Técnicos Activos");
		
		panelNavegacion.add(btnSolicitudes);
		panelNavegacion.add(btnCrearSolicitud);
		panelNavegacion.add(btnVehiculos);
		panelNavegacion.add(btnCrearVehiculos);
		panelNavegacion.add(btnOperaciones);
		panelNavegacion.add(btnTecnicos);
		
		add(panelNavegacion, BorderLayout.NORTH);
	}
	
	/**
	 * Crea el panel de contenido con CardLayout para cambiar vistas
	 */
	private void crearPanelContenido() {
		layoutContenido = new CardLayout();
		panelContenido = new JPanel(layoutContenido);
		panelContenido.setBackground(new Color(240, 240, 240));
		
		add(panelContenido, BorderLayout.CENTER);
	}
	
	/**
	 * Crea un botón estilizado
	 */
	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Arial", Font.PLAIN, 12));
		boton.setForeground(Color.WHITE);
		boton.setBackground(new Color(0, 120, 215));
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(true);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setPreferredSize(new Dimension(160, 40));
		
		return boton;
	}
	
	// ==================== Métodos públicos para navegación ====================
	
	/**
	 * Registra los listeners para los botones de navegación
	 * NOTA: Esto es llamado por el Controlador, no por la Vista
	 */
	public void registrarListenerNavegacion(java.awt.event.ActionListener solicitudes,
	                                       java.awt.event.ActionListener crearSolicitud,
	                                       java.awt.event.ActionListener vehiculos,
	                                       java.awt.event.ActionListener crearVehiculos,
	                                       java.awt.event.ActionListener operaciones,
	                                       java.awt.event.ActionListener tecnicos) {
		btnSolicitudes.addActionListener(solicitudes);
		btnCrearSolicitud.addActionListener(crearSolicitud);
		btnVehiculos.addActionListener(vehiculos);
		btnCrearVehiculos.addActionListener(crearVehiculos);
		btnOperaciones.addActionListener(operaciones);
		btnTecnicos.addActionListener(tecnicos);
	}
	
	/**
	 * Obtiene el botón de solicitudes para que el controlador lo maneje
	 */
	public JButton getBtnSolicitudes() {
		return btnSolicitudes;
	}
	
	/**
	 * Obtiene el botón de crear solicitud
	 */
	public JButton getBtnCrearSolicitud() {
		return btnCrearSolicitud;
	}
	
	/**
	 * Obtiene el botón de vehículos
	 */
	public JButton getBtnVehiculos() {
		return btnVehiculos;
	}
	
	/**
	 * Obtiene el botón de crear vehículos
	 */
	public JButton getBtnCrearVehiculos() {
		return btnCrearVehiculos;
	}
	
	/**
	 * Obtiene el botón de operaciones
	 */
	public JButton getBtnOperaciones() {
		return btnOperaciones;
	}
	
	/**
	 * Obtiene el botón de técnicos
	 */
	public JButton getBtnTecnicos() {
		return btnTecnicos;
	}
	
	/**
	 * Cambia a panel de solicitudes
	 */
	public vista.paneles.PanelSolicitudes mostrarPanelSolicitudes() {
		vista.paneles.PanelSolicitudes panel = new vista.paneles.PanelSolicitudes();
		cambiarPanel("solicitudes", panel);
		return panel;
	}
	
	/**
	 * Cambia a panel de crear solicitud
	 */
	public vista.paneles.PanelCrearSolicitud mostrarPanelCrearSolicitud() {
		vista.paneles.PanelCrearSolicitud panel = new vista.paneles.PanelCrearSolicitud();
		cambiarPanel("crear_solicitud", panel);
		return panel;
	}
	
	/**
	 * Cambia a panel de vehículos
	 */
	public vista.paneles.PanelVehiculos mostrarPanelVehiculos() {
		vista.paneles.PanelVehiculos panel = new vista.paneles.PanelVehiculos();
		cambiarPanel("vehiculos", panel);
		return panel;
	}
	
	/**
	 * Cambia a panel de crear vehículos
	 */
	public vista.paneles.PanelCrearVehiculos mostrarPanelCrearVehiculos() {
		vista.paneles.PanelCrearVehiculos panel = new vista.paneles.PanelCrearVehiculos();
		cambiarPanel("crear_vehiculos", panel);
		return panel;
	}
	
	/**
	 * Cambia a panel de operaciones
	 */
	public vista.paneles.PanelOperaciones mostrarPanelOperaciones() {
		vista.paneles.PanelOperaciones panel = new vista.paneles.PanelOperaciones();
		cambiarPanel("operaciones", panel);
		return panel;
	}
	
	/**
	 * Cambia a panel de técnicos
	 */
	public vista.paneles.PanelTecnicos mostrarPanelTecnicos() {
		vista.paneles.PanelTecnicos panel = new vista.paneles.PanelTecnicos();
		cambiarPanel("tecnicos", panel);
		return panel;
	}
	
	/**
	 * Cambia el panel actual mostrado en el área de contenido
	 * Implementa el patrón de CardLayout para navegación eficiente
	 */
	private void cambiarPanel(String nombre, JPanel panel) {
		panelContenido.add(panel, nombre);
		layoutContenido.show(panelContenido, nombre);
		panelActual = panel;
	}
	
	/**
	 * Obtiene el panel de contenido (para el controlador si es necesario)
	 */
	public JPanel getPanelContenido() {
		return panelContenido;
	}
	
	/**
	 * Obtiene el panel actual
	 */
	public JPanel getPanelActual() {
		return panelActual;
	}
	
	/**
	 * Muestra la ventana principal
	 */
	public void mostrar() {
		setVisible(true);
	}
}
