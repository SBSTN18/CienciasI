package vista.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para crear una nueva solicitud.
 * Implementa formulario para capturar datos de una solicitud.
 * 
 * SOLID: Single Responsibility - Solo gestiona creación de solicitudes
 */
public class PanelCrearSolicitud extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Componentes del formulario
	private JTextField txtNombreCliente;
	private JTextField txtTelefonoCliente;
	private JComboBox<String> cmbTipoCliente;
	private JTextField txtUbicacion;
	private JComboBox<String> cmbZona;
	private JComboBox<String> cmbTipoServicio;
	private JComboBox<String> cmbPrioridad;
	private JTextArea txtDescripcion;
	
	// Botones
	private JButton btnCrear;
	private JButton btnLimpiar;
	private JButton btnCancelar;
	
	public PanelCrearSolicitud() {
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(240, 240, 240));
		
		// Panel de título
		JPanel panelTitulo = crearPanelTitulo();
		add(panelTitulo, BorderLayout.NORTH);
		
		// Panel de formulario
		JPanel panelFormulario = crearPanelFormulario();
		add(new JScrollPane(panelFormulario), BorderLayout.CENTER);
		
		// Panel de botones
		JPanel panelBotones = crearPanelBotones();
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	private JPanel crearPanelTitulo() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		
		JLabel titulo = new JLabel("Crear Nueva Solicitud");
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(titulo);
		
		return panel;
	}
	
	private JPanel crearPanelFormulario() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Información del cliente
		agregarComponente(panel, gbc, "Nombre Cliente:", 0, 0);
		txtNombreCliente = new JTextField(20);
		agregarComponente(panel, gbc, txtNombreCliente, 1, 0);
		
		agregarComponente(panel, gbc, "Teléfono:", 0, 1);
		txtTelefonoCliente = new JTextField(20);
		agregarComponente(panel, gbc, txtTelefonoCliente, 1, 1);
		
		agregarComponente(panel, gbc, "Tipo Cliente:", 0, 2);
		cmbTipoCliente = new JComboBox<>(new String[]{"PARTICULAR", "CORPORATIVO"});
		agregarComponente(panel, gbc, cmbTipoCliente, 1, 2);
		
		// Ubicación y zona
		agregarComponente(panel, gbc, "Ubicación:", 0, 3);
		txtUbicacion = new JTextField(20);
		agregarComponente(panel, gbc, txtUbicacion, 1, 3);
		
		agregarComponente(panel, gbc, "Zona:", 0, 4);
		cmbZona = new JComboBox<>(new String[]{"NORTE", "SUR", "ESTE", "OESTE", "CENTRO"});
		agregarComponente(panel, gbc, cmbZona, 1, 4);
		
		// Tipo de servicio y prioridad
		agregarComponente(panel, gbc, "Tipo de Servicio:", 0, 5);
		cmbTipoServicio = new JComboBox<>(new String[]{
			"CAMBIO_RUEDA", "COMBUSTIBLE", "BATERIA", "MECANICA",
			"CHOQUE_LEVE", "CHOQUE_GRAVE", "TRASLADO"
		});
		agregarComponente(panel, gbc, cmbTipoServicio, 1, 5);
		
		agregarComponente(panel, gbc, "Prioridad:", 0, 6);
		cmbPrioridad = new JComboBox<>(new String[]{"ORDINARIA", "ALTA", "CRÍTICA", "EMERGENCIA"});
		agregarComponente(panel, gbc, cmbPrioridad, 1, 6);
		
		// Descripción
		agregarComponente(panel, gbc, "Descripción:", 0, 7);
		txtDescripcion = new JTextArea(4, 20);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
		agregarComponente(panel, gbc, scrollDesc, 1, 7);
	
		
		return panel;
	}
	
	private JPanel crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(new Color(240, 240, 240));
		
		btnCrear = crearBoton("Crear Solicitud", new Color(0, 150, 0));
		btnLimpiar = crearBoton("Limpiar", new Color(100, 100, 100));
		btnCancelar = crearBoton("Cancelar", new Color(200, 0, 0));
		
		panel.add(btnCrear);
		panel.add(btnLimpiar);
		panel.add(btnCancelar);
		
		return panel;
	}
	
	private JButton crearBoton(String texto, Color color) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Arial", Font.PLAIN, 12));
		boton.setForeground(Color.WHITE);
		boton.setBackground(color);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(true);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setPreferredSize(new Dimension(180, 35));
		return boton;
	}
	
	private void agregarComponente(JPanel panel, GridBagConstraints gbc, String etiqueta, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 0;
		JLabel label = new JLabel(etiqueta);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(label, gbc);
	}
	
	private void agregarComponente(JPanel panel, GridBagConstraints gbc, JComponent componente, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		panel.add(componente, gbc);
	}
	
	// ==================== Getters para datos del formulario ====================
	
	public String getNombreCliente() {
		return txtNombreCliente.getText();
	}
	
	public String getTelefonoCliente() {
		return txtTelefonoCliente.getText();
	}
	
	public String getTipoCliente() {
		return (String) cmbTipoCliente.getSelectedItem();
	}
	
	public String getUbicacion() {
		return txtUbicacion.getText();
	}
	
	public String getZona() {
		return (String) cmbZona.getSelectedItem();
	}
	
	public String getTipoServicio() {
		return (String) cmbTipoServicio.getSelectedItem();
	}
	
	public String getPrioridad() {
		return (String) cmbPrioridad.getSelectedItem();
	}
	
	public String getDescripcion() {
		return txtDescripcion.getText();
	}
	
	
	// ==================== Setters para cargar datos ====================
	
	
	public void limpiarFormulario() {
		txtNombreCliente.setText("");
		txtTelefonoCliente.setText("");
		cmbTipoCliente.setSelectedIndex(0);
		txtUbicacion.setText("");
		cmbZona.setSelectedIndex(0);
		cmbTipoServicio.setSelectedIndex(0);
		cmbPrioridad.setSelectedIndex(0);
		txtDescripcion.setText("");
	}
	
	// ==================== Listeners ====================
	
	public void registrarListenerCrear(ActionListener listener) {
		btnCrear.addActionListener(listener);
	}
	
	public void registrarListenerLimpiar(ActionListener listener) {
		btnLimpiar.addActionListener(listener);
	}
	
	public void registrarListenerCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	// ==================== Mensajes ====================
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean confirmar(String mensaje) {
		int respuesta = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
		return respuesta == JOptionPane.YES_OPTION;
	}
}
