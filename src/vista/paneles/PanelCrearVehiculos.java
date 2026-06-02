package vista.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para crear nuevos vehículos o técnicos.
 * Implementa formularios para ambos casos.
 * 
 * SOLID: Single Responsibility - Gestiona creación de recursos
 */
public class PanelCrearVehiculos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// Componentes
	private JTabbedPane pestanas;
	private JPanel panelVehiculo;
	private JPanel panelTecnico;
	
	// Componentes para Vehículos
	private JComboBox<String> cmbTipoVehiculo;
	private JTextField txtPlacaVehiculo;
	private JTextField txtModeloVehiculo;
	private JTextField txtAnoVehiculo;
	private JComboBox<String> cmbZonaVehiculo;
	private JComboBox<String> cmbEspecialidadVehiculo;
	private JButton btnCrearVehiculo;
	private JButton btnLimpiarVehiculo;
	
	// Componentes para Técnicos
	private JTextField txtNombreTecnico;
	private JTextField txtCedulaTecnico;
	private JComboBox<String> cmbEspecialidadTecnico;
	private JComboBox<String> cmbZonaTecnico;
	private JTextField txtTelefonoTecnico;
	private JButton btnCrearTecnico;
	private JButton btnLimpiarTecnico;
	
	public PanelCrearVehiculos() {
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(240, 240, 240));
		
		// Título
		JLabel titulo = new JLabel("Crear Nuevos Recursos");
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		add(titulo, BorderLayout.NORTH);
		
		// Pestañas
		pestanas = new JTabbedPane();
		pestanas.setBackground(new Color(240, 240, 240));
		
		panelVehiculo = crearPanelVehiculo();
		panelTecnico = crearPanelTecnico();
		
		pestanas.addTab("Crear Vehículo", panelVehiculo);
		pestanas.addTab("Crear Técnico", panelTecnico);
		
		add(pestanas, BorderLayout.CENTER);
	}
	
	// ==================== PANEL DE VEHÍCULOS ====================
	
	private JPanel crearPanelVehiculo() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Tipo de vehículo
		agregarEtiqueta(panel, gbc, "Tipo de Vehículo:", 0, 0);
		cmbTipoVehiculo = new JComboBox<>(new String[]{
			"MotoApoyo", "Camioneta", "Grúa", "Liviano"
		});
		agregarComponente(panel, gbc, cmbTipoVehiculo, 1, 0);
		
		// Placa
		agregarEtiqueta(panel, gbc, "Placa:", 0, 1);
		txtPlacaVehiculo = new JTextField(15);
		agregarComponente(panel, gbc, txtPlacaVehiculo, 1, 1);
		
		// Modelo
		agregarEtiqueta(panel, gbc, "Modelo:", 0, 2);
		txtModeloVehiculo = new JTextField(15);
		agregarComponente(panel, gbc, txtModeloVehiculo, 1, 2);
		
		// Año
		agregarEtiqueta(panel, gbc, "Año:", 0, 3);
		txtAnoVehiculo = new JTextField(15);
		agregarComponente(panel, gbc, txtAnoVehiculo, 1, 3);
		
		// Zona
		agregarEtiqueta(panel, gbc, "Zona:", 0, 4);
		cmbZonaVehiculo = new JComboBox<>(new String[]{
			"NORTE", "SUR", "ESTE", "OESTE", "CENTRO"
		});
		agregarComponente(panel, gbc, cmbZonaVehiculo, 1, 4);
		
		// Especialidad
		agregarEtiqueta(panel, gbc, "Especialidad:", 0, 5);
		cmbEspecialidadVehiculo = new JComboBox<>(new String[]{
			"MECANICA", "ELECTRICA", "NEUMATICOS", "GRUA"
		});
		agregarComponente(panel, gbc, cmbEspecialidadVehiculo, 1, 5);
		
		// Botones
		JPanel panelBotonesVehiculo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBotonesVehiculo.setBackground(new Color(240, 240, 240));
		
		btnCrearVehiculo = crearBoton("Crear Vehículo", new Color(0, 150, 0));
		btnLimpiarVehiculo = crearBoton("Limpiar", new Color(100, 100, 100));
		
		panelBotonesVehiculo.add(btnCrearVehiculo);
		panelBotonesVehiculo.add(btnLimpiarVehiculo);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelBotonesVehiculo, gbc);
		
		return panel;
	}
	
	// ==================== PANEL DE TÉCNICOS ====================
	
	private JPanel crearPanelTecnico() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Nombre
		agregarEtiqueta(panel, gbc, "Nombre Técnico:", 0, 0);
		txtNombreTecnico = new JTextField(15);
		agregarComponente(panel, gbc, txtNombreTecnico, 1, 0);
		
		// Cédula
		agregarEtiqueta(panel, gbc, "Cédula:", 0, 1);
		txtCedulaTecnico = new JTextField(15);
		agregarComponente(panel, gbc, txtCedulaTecnico, 1, 1);
		
		// Teléfono
		agregarEtiqueta(panel, gbc, "Teléfono:", 0, 2);
		txtTelefonoTecnico = new JTextField(15);
		agregarComponente(panel, gbc, txtTelefonoTecnico, 1, 2);
		
		// Especialidad
		agregarEtiqueta(panel, gbc, "Especialidad:", 0, 3);
		cmbEspecialidadTecnico = new JComboBox<>(new String[]{
			"MECANICA", "ELECTRICA", "NEUMATICOS", "GRUA"
		});
		agregarComponente(panel, gbc, cmbEspecialidadTecnico, 1, 3);
		
		// Zona
		agregarEtiqueta(panel, gbc, "Zona:", 0, 4);
		cmbZonaTecnico = new JComboBox<>(new String[]{
			"NORTE", "SUR", "ESTE", "OESTE", "CENTRO"
		});
		agregarComponente(panel, gbc, cmbZonaTecnico, 1, 4);
		
		// Botones
		JPanel panelBotonesTecnico = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelBotonesTecnico.setBackground(new Color(240, 240, 240));
		
		btnCrearTecnico = crearBoton("Crear Técnico", new Color(0, 150, 0));
		btnLimpiarTecnico = crearBoton("Limpiar", new Color(100, 100, 100));
		
		panelBotonesTecnico.add(btnCrearTecnico);
		panelBotonesTecnico.add(btnLimpiarTecnico);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelBotonesTecnico, gbc);
		
		return panel;
	}
	
	// ==================== Utilidades ====================
	
	private JButton crearBoton(String texto, Color color) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Arial", Font.PLAIN, 12));
		boton.setForeground(Color.WHITE);
		boton.setBackground(color);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(true);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setPreferredSize(new Dimension(160, 35));
		return boton;
	}
	
	private void agregarEtiqueta(JPanel panel, GridBagConstraints gbc, String texto, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 0;
		JLabel label = new JLabel(texto);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(label, gbc);
	}
	
	private void agregarComponente(JPanel panel, GridBagConstraints gbc, JComponent comp, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		panel.add(comp, gbc);
	}
	
	// ==================== Getters Vehículos ====================
	
	public String getTipoVehiculo() {
		return (String) cmbTipoVehiculo.getSelectedItem();
	}
	
	public String getPlacaVehiculo() {
		return txtPlacaVehiculo.getText();
	}
	
	public String getModeloVehiculo() {
		return txtModeloVehiculo.getText();
	}
	
	public String getAnoVehiculo() {
		return txtAnoVehiculo.getText();
	}
	
	public String getZonaVehiculo() {
		return (String) cmbZonaVehiculo.getSelectedItem();
	}
	
	public String getEspecialidadVehiculo() {
		return (String) cmbEspecialidadVehiculo.getSelectedItem();
	}
	
	public void limpiarFormularioVehiculo() {
		cmbTipoVehiculo.setSelectedIndex(0);
		txtPlacaVehiculo.setText("");
		txtModeloVehiculo.setText("");
		txtAnoVehiculo.setText("");
		cmbZonaVehiculo.setSelectedIndex(0);
		cmbEspecialidadVehiculo.setSelectedIndex(0);
	}
	
	// ==================== Getters Técnicos ====================
	
	public String getNombreTecnico() {
		return txtNombreTecnico.getText();
	}
	
	public String getCedulaTecnico() {
		return txtCedulaTecnico.getText();
	}
	
	public String getTelefonoTecnico() {
		return txtTelefonoTecnico.getText();
	}
	
	public String getEspecialidadTecnico() {
		return (String) cmbEspecialidadTecnico.getSelectedItem();
	}
	
	public String getZonaTecnico() {
		return (String) cmbZonaTecnico.getSelectedItem();
	}
	
	public void limpiarFormularioTecnico() {
		txtNombreTecnico.setText("");
		txtCedulaTecnico.setText("");
		txtTelefonoTecnico.setText("");
		cmbEspecialidadTecnico.setSelectedIndex(0);
		cmbZonaTecnico.setSelectedIndex(0);
	}
	
	// ==================== Listeners ====================
	
	public void registrarListenerCrearVehiculo(ActionListener listener) {
		btnCrearVehiculo.addActionListener(listener);
	}
	
	public void registrarListenerLimpiarVehiculo(ActionListener listener) {
		btnLimpiarVehiculo.addActionListener(listener);
	}
	
	public void registrarListenerCrearTecnico(ActionListener listener) {
		btnCrearTecnico.addActionListener(listener);
	}
	
	public void registrarListenerLimpiarTecnico(ActionListener listener) {
		btnLimpiarTecnico.addActionListener(listener);
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
