package vista.paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para gestionar la pila de operaciones.
 * Permite ver el historial y deshacer operaciones.
 * 
 * SOLID: Single Responsibility - Solo gestiona operaciones
 */
public class PanelOperaciones extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tablaOperaciones;
	private DefaultTableModel modeloTabla;
	private JButton btnActualizar;
	private JButton btnDeshacer;
	private JButton btnVerDetalles;
	private JLabel lblTotalOperaciones;
	private JTextArea txtDetallesOperacion;
	
	public PanelOperaciones() {
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(240, 240, 240));
		
		// Panel superior
		JPanel panelSuperior = crearPanelSuperior();
		add(panelSuperior, BorderLayout.NORTH);
		
		// Panel central con tabla y detalles
		JPanel panelCentral = crearPanelCentral();
		add(panelCentral, BorderLayout.CENTER);
		
		// Panel inferior con botones
		JPanel panelInferior = crearPanelInferior();
		add(panelInferior, BorderLayout.SOUTH);
	}
	
	private JPanel crearPanelSuperior() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));
		
		JLabel titulo = new JLabel("Gestión de Operaciones (Pila)");
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(titulo, BorderLayout.WEST);
		
		lblTotalOperaciones = new JLabel("Total en Pila: 0");
		lblTotalOperaciones.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblTotalOperaciones, BorderLayout.EAST);
		
		return panel;
	}
	
	private JPanel crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(new Color(240, 240, 240));
		
		// Tabla de operaciones
		String[] columnas = {"Orden", "Tipo Operación", "Objeto", "Estado Anterior", "Fecha/Hora"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaOperaciones = new JTable(modeloTabla);
		tablaOperaciones.setFont(new Font("Arial", Font.PLAIN, 11));
		tablaOperaciones.setRowHeight(25);
		tablaOperaciones.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tablaOperaciones.getTableHeader().setBackground(new Color(200, 200, 200));
		tablaOperaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tablaOperaciones);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Historial de Operaciones"));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		// Panel de detalles
		JPanel panelDetalles = new JPanel(new BorderLayout(10, 10));
		panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de la Última Operación"));
		panelDetalles.setBackground(new Color(240, 240, 240));
		
		txtDetallesOperacion = new JTextArea(5, 40);
		txtDetallesOperacion.setEditable(false);
		txtDetallesOperacion.setFont(new Font("Courier New", Font.PLAIN, 11));
		txtDetallesOperacion.setLineWrap(true);
		txtDetallesOperacion.setWrapStyleWord(true);
		
		JScrollPane scrollDetalles = new JScrollPane(txtDetallesOperacion);
		panelDetalles.add(scrollDetalles, BorderLayout.CENTER);
		
		panel.add(panelDetalles, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel crearPanelInferior() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(new Color(240, 240, 240));
		
		btnActualizar = crearBoton("Actualizar", new Color(0, 120, 215));
		btnVerDetalles = crearBoton("Ver Detalles", new Color(0, 120, 215));
		btnDeshacer = crearBoton("Deshacer Última Operación", new Color(200, 100, 0));
		
		panel.add(btnActualizar);
		panel.add(btnVerDetalles);
		
		// Separador visual
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		
		panel.add(btnDeshacer);
		
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
		boton.setPreferredSize(new Dimension(200, 35));
		return boton;
	}
	
	// ==================== Métodos de la Vista ====================
	
	/**
	 * Agrega una operación a la tabla
	 */
	public void agregarOperacion(String orden, String tipoOperacion, String objeto,
	                             String estadoAnterior, String fechaHora) {
		Object[] fila = {orden, tipoOperacion, objeto, estadoAnterior, fechaHora};
		modeloTabla.addRow(fila);
	}
	
	/**
	 * Limpia la tabla
	 */
	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
	}
	
	/**
	 * Actualiza el contador de operaciones
	 */
	public void actualizarTotal(int total) {
		lblTotalOperaciones.setText("Total en Pila: " + total);
	}
	
	/**
	 * Actualiza los detalles de la última operación
	 */
	public void actualizarDetalles(String detalles) {
		txtDetallesOperacion.setText(detalles);
	}
	
	/**
	 * Obtiene la fila seleccionada
	 */
	public int getFilaSeleccionada() {
		return tablaOperaciones.getSelectedRow();
	}
	
	/**
	 * Obtiene el orden de la operación seleccionada
	 */
	public String getOrdenSeleccionada() {
		int fila = getFilaSeleccionada();
		if (fila != -1) {
			return (String) modeloTabla.getValueAt(fila, 0);
		}
		return null;
	}
	
	/**
	 * Verifica si la pila está vacía
	 */
	public void mostrarPilaVacia() {
		JOptionPane.showMessageDialog(this, 
			"La pila de operaciones está vacía. No hay operaciones para deshacer.",
			"Pila Vacía",
			JOptionPane.INFORMATION_MESSAGE);
	}
	
	// ==================== Listeners ====================
	
	public void registrarListenerActualizar(ActionListener listener) {
		btnActualizar.addActionListener(listener);
	}
	
	public void registrarListenerVerDetalles(ActionListener listener) {
		btnVerDetalles.addActionListener(listener);
	}
	
	public void registrarListenerDeshacer(ActionListener listener) {
		btnDeshacer.addActionListener(listener);
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
