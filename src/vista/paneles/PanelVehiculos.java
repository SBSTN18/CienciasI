package vista.paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para mostrar vehículos libres disponibles.
 * Permite visualizar el estado de los vehículos.
 * 
 * SOLID: Single Responsibility - Solo muestra vehículos disponibles
 */
public class PanelVehiculos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tablaVehiculos;
	private DefaultTableModel modeloTabla;
	private JButton btnActualizar;
	private JButton btnAsignar;
	private JLabel lblTotal;
	private JLabel lblLibres;
	
	public PanelVehiculos() {
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(240, 240, 240));
		
		// Panel superior con información
		JPanel panelSuperior = crearPanelSuperior();
		add(panelSuperior, BorderLayout.NORTH);
		
		// Tabla de vehículos
		JPanel panelTabla = crearPanelTabla();
		add(panelTabla, BorderLayout.CENTER);
		
		// Panel inferior con botones
		JPanel panelInferior = crearPanelInferior();
		add(panelInferior, BorderLayout.SOUTH);
	}
	
	private JPanel crearPanelSuperior() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));
		
		JLabel titulo = new JLabel("Vehículos Disponibles");
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(titulo, BorderLayout.WEST);
		
		JPanel panelEstadisticas = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
		panelEstadisticas.setBackground(new Color(240, 240, 240));
		
		lblTotal = new JLabel("Total: 0");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		panelEstadisticas.add(lblTotal);
		
		lblLibres = new JLabel("Libres: 0");
		lblLibres.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLibres.setForeground(new Color(0, 150, 0));
		panelEstadisticas.add(lblLibres);
		
		panel.add(panelEstadisticas, BorderLayout.EAST);
		
		return panel;
	}
	
	private JPanel crearPanelTabla() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));
		
		// Crear tabla
		String[] columnas = {"ID", "Tipo", "Estado", "Zona"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaVehiculos = new JTable(modeloTabla);
		tablaVehiculos.setFont(new Font("Arial", Font.PLAIN, 11));
		tablaVehiculos.setRowHeight(25);
		tablaVehiculos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tablaVehiculos.getTableHeader().setBackground(new Color(200, 200, 200));
		tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tablaVehiculos);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel crearPanelInferior() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(new Color(240, 240, 240));
		
		btnActualizar = crearBoton("Actualizar");
		btnAsignar = crearBoton("Asignar a Solicitud");
		
		panel.add(btnActualizar);
		panel.add(btnAsignar);
		
		return panel;
	}
	
	private JButton crearBoton(String texto) {
		JButton boton = new JButton(texto);
		boton.setFont(new Font("Arial", Font.PLAIN, 12));
		boton.setForeground(Color.WHITE);
		boton.setBackground(new Color(0, 120, 215));
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(true);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setPreferredSize(new Dimension(200, 35));
		return boton;
	}
	
	// ==================== Métodos de la Vista ====================
	
	/**
	 * Agrega una fila a la tabla de vehículos
	 */
	public void agregarFila(String id, String tipo,
	                        String estado, String zona) {
		Object[] fila = {id, tipo, estado, zona};
		modeloTabla.addRow(fila);
	}
	
	/**
	 * Limpia la tabla
	 */
	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
	}
	
	/**
	 * Actualiza las estadísticas
	 */
	public void actualizarEstadisticas(int total, int libres) {
		lblTotal.setText("Total: " + total);
		lblLibres.setText("Libres: " + libres);
	}
	
	/**
	 * Obtiene el ID del vehículo seleccionado
	 */
	public String getIdVehiculoSeleccionado() {
		int fila = tablaVehiculos.getSelectedRow();
		if (fila != -1) {
			return (String) modeloTabla.getValueAt(fila, 0);
		}
		return null;
	}
	
	/**
	 * Obtiene la fila seleccionada
	 */
	public int getFilaSeleccionada() {
		return tablaVehiculos.getSelectedRow();
	}
	
	// ==================== Listeners ====================
	
	public void registrarListenerActualizar(ActionListener listener) {
		btnActualizar.addActionListener(listener);
	}
	
	public void registrarListenerAsignar(ActionListener listener) {
		btnAsignar.addActionListener(listener);
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
