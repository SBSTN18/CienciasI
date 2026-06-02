package vista.paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel para mostrar todas las solicitudes en curso o pendientes.
 * Implementa la vista para listar solicitudes.
 * 
 * SOLID: Single Responsibility - Solo muestra solicitudes
 */
public class PanelSolicitudes extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTable tablaSolicitudes;
	private DefaultTableModel modeloTabla;
	private JButton btnActualizar;
	private JButton btnVerDetalles;
	private JButton btnCancelar;
	private JLabel lblTotal;
	
	public PanelSolicitudes() {
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(new Color(240, 240, 240));
		
		// Panel superior con información
		JPanel panelSuperior = crearPanelSuperior();
		add(panelSuperior, BorderLayout.NORTH);
		
		// Tabla de solicitudes
		JPanel panelTabla = crearPanelTabla();
		add(panelTabla, BorderLayout.CENTER);
		
		// Panel inferior con botones
		JPanel panelInferior = crearPanelInferior();
		add(panelInferior, BorderLayout.SOUTH);
	}
	
	private JPanel crearPanelSuperior() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));
		
		JLabel titulo = new JLabel("Solicitudes en Curso o Pendientes");
		titulo.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(titulo, BorderLayout.WEST);
		
		lblTotal = new JLabel("Total: 0");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblTotal, BorderLayout.EAST);
		
		return panel;
	}
	
	private JPanel crearPanelTabla() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));
		
		// Crear tabla
		String[] columnas = {"ID", "Cliente", "Tipo Servicio", "Estado", "Prioridad", "Zona", "Técnico Asignado", "Fecha Creación"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Tabla de solo lectura
			}
		};
		
		tablaSolicitudes = new JTable(modeloTabla);
		tablaSolicitudes.setFont(new Font("Arial", Font.PLAIN, 11));
		tablaSolicitudes.setRowHeight(25);
		tablaSolicitudes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
		tablaSolicitudes.getTableHeader().setBackground(new Color(200, 200, 200));
		tablaSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(tablaSolicitudes);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel crearPanelInferior() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panel.setBackground(new Color(240, 240, 240));
		
		btnActualizar = crearBoton("Actualizar");
		btnVerDetalles = crearBoton("Ver Detalles");
		btnCancelar = crearBoton("Cancelar Solicitud");
		
		panel.add(btnActualizar);
		panel.add(btnVerDetalles);
		panel.add(btnCancelar);
		
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
		boton.setPreferredSize(new Dimension(180, 35));
		return boton;
	}
	
	// ==================== Métodos de la Vista ====================
	
	/**
	 * Agrega una fila a la tabla de solicitudes
	 */
	public void agregarFila(String id, String cliente, String tipoServicio, String estado, 
	                        String prioridad, String zona, String tecnico, String fecha) {
		Object[] fila = {id, cliente, tipoServicio, estado, prioridad, zona, tecnico, fecha};
		modeloTabla.addRow(fila);
	}
	
	/**
	 * Limpia todas las filas de la tabla
	 */
	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
	}
	
	/**
	 * Actualiza el contador de solicitudes
	 */
	public void actualizarTotal(int total) {
		lblTotal.setText("Total: " + total);
	}
	
	/**
	 * Obtiene la fila seleccionada
	 */
	public int getFilaSeleccionada() {
		return tablaSolicitudes.getSelectedRow();
	}
	
	/**
	 * Obtiene el ID de la solicitud seleccionada
	 */
	public String getIdSolicitudSeleccionada() {
		int fila = getFilaSeleccionada();
		if (fila != -1) {
			return (String) modeloTabla.getValueAt(fila, 0);
		}
		return null;
	}
	
	/**
	 * Registra listener para el botón Actualizar
	 */
	public void registrarListenerActualizar(ActionListener listener) {
		btnActualizar.addActionListener(listener);
	}
	
	/**
	 * Registra listener para el botón Ver Detalles
	 */
	public void registrarListenerVerDetalles(ActionListener listener) {
		btnVerDetalles.addActionListener(listener);
	}
	
	/**
	 * Registra listener para el botón Cancelar
	 */
	public void registrarListenerCancelar(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	/**
	 * Muestra un mensaje de información
	 */
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Muestra un mensaje de error
	 */
	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Muestra un diálogo de confirmación
	 */
	public boolean confirmar(String mensaje) {
		int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Confirmación", 
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return opcion == JOptionPane.YES_OPTION;
	}
}
