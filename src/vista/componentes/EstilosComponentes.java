package vista.componentes;

import javax.swing.*;
import java.awt.*;

/**
 * Clase de utilidades para estilos comunes en la interfaz gráfica.
 * Implementa el patrón Utility para evitar repetición de código.
 * 
 * SOLID: Single Responsibility - Solo proporciona estilos
 */
public class EstilosComponentes {
	
	// Colores de la aplicación
	public static final Color COLOR_FONDO_PRINCIPAL = new Color(240, 240, 240);
	public static final Color COLOR_FONDO_OSCURO = new Color(45, 45, 48);
	public static final Color COLOR_BOTON_PRIMARIO = new Color(0, 120, 215);
	public static final Color COLOR_BOTON_EXITO = new Color(0, 150, 0);
	public static final Color COLOR_BOTON_ADVERTENCIA = new Color(200, 100, 0);
	public static final Color COLOR_BOTON_PELIGRO = new Color(200, 0, 0);
	public static final Color COLOR_BOTON_NEUTRO = new Color(100, 100, 100);
	public static final Color COLOR_TEXTO_OSCURO = Color.WHITE;
	public static final Color COLOR_CABECERA_TABLA = new Color(200, 200, 200);
	
	// Fuentes de la aplicación
	public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 16);
	public static final Font FUENTE_SUBTITULO = new Font("Arial", Font.BOLD, 14);
	public static final Font FUENTE_ETIQUETA = new Font("Arial", Font.PLAIN, 12);
	public static final Font FUENTE_TABLA = new Font("Arial", Font.PLAIN, 11);
	public static final Font FUENTE_CABECERA_TABLA = new Font("Arial", Font.BOLD, 12);
	public static final Font FUENTE_MONOESPACIADA = new Font("Courier New", Font.PLAIN, 11);
	
	// Tamaños de componentes
	public static final Dimension TAMAÑO_BOTON_PEQUENO = new Dimension(140, 35);
	public static final Dimension TAMAÑO_BOTON_MEDIO = new Dimension(160, 35);
	public static final Dimension TAMAÑO_BOTON_GRANDE = new Dimension(200, 35);
	public static final int ALTURA_FILA_TABLA = 25;
	public static final int ESPACIADO_INTERIOR = 10;
	
	/**
	 * Crea un botón estilizado con colores primarios
	 */
	public static JButton crearBotonPrimario(String texto) {
		return crearBoton(texto, COLOR_BOTON_PRIMARIO, TAMAÑO_BOTON_MEDIO);
	}
	
	/**
	 * Crea un botón estilizado para acciones de éxito (crear, guardar)
	 */
	public static JButton crearBotonExito(String texto) {
		return crearBoton(texto, COLOR_BOTON_EXITO, TAMAÑO_BOTON_MEDIO);
	}
	
	/**
	 * Crea un botón estilizado para acciones de advertencia
	 */
	public static JButton crearBotonAdvertencia(String texto) {
		return crearBoton(texto, COLOR_BOTON_ADVERTENCIA, TAMAÑO_BOTON_MEDIO);
	}
	
	/**
	 * Crea un botón estilizado para acciones peligrosas (eliminar, cancelar)
	 */
	public static JButton crearBotonPeligro(String texto) {
		return crearBoton(texto, COLOR_BOTON_PELIGRO, TAMAÑO_BOTON_MEDIO);
	}
	
	/**
	 * Crea un botón estilizado con color personalizado
	 */
	public static JButton crearBoton(String texto, Color color, Dimension tamaño) {
		JButton boton = new JButton(texto);
		boton.setFont(FUENTE_ETIQUETA);
		boton.setForeground(COLOR_TEXTO_OSCURO);
		boton.setBackground(color);
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setOpaque(true);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.setPreferredSize(tamaño);
		return boton;
	}
	
	/**
	 * Configura una tabla con estilos estándar
	 */
	public static void configurarTabla(JTable tabla) {
		tabla.setFont(FUENTE_TABLA);
		tabla.setRowHeight(ALTURA_FILA_TABLA);
		tabla.getTableHeader().setFont(FUENTE_CABECERA_TABLA);
		tabla.getTableHeader().setBackground(COLOR_CABECERA_TABLA);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	/**
	 * Crea un panel con estilos estándar
	 */
	public static JPanel crearPanelEstandar() {
		JPanel panel = new JPanel();
		panel.setBackground(COLOR_FONDO_PRINCIPAL);
		panel.setBorder(BorderFactory.createEmptyBorder(
			ESPACIADO_INTERIOR, ESPACIADO_INTERIOR, 
			ESPACIADO_INTERIOR, ESPACIADO_INTERIOR));
		return panel;
	}
	
	/**
	 * Crea una etiqueta con estilos estándar
	 */
	public static JLabel crearEtiqueta(String texto, Font fuente) {
		JLabel etiqueta = new JLabel(texto);
		etiqueta.setFont(fuente);
		return etiqueta;
	}
	
	/**
	 * Crea un título estándar
	 */
	public static JLabel crearTitulo(String texto) {
		return crearEtiqueta(texto, FUENTE_TITULO);
	}
	
	/**
	 * Crea un subtítulo estándar
	 */
	public static JLabel crearSubtitulo(String texto) {
		return crearEtiqueta(texto, FUENTE_SUBTITULO);
	}
}
