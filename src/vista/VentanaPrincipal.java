package vista;

import Control.Vista.ControladorVista;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema AutoRescate 24/7.
 * Contiene los diferentes paneles de gestión organizados mediante pestañas
 * y proporciona métodos para mostrar mensajes al usuario.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {

    private PanelVehiculos panelVehiculos;
    private PanelTecnicos panelTecnicos;
    private PanelSolicitudes panelSolicitudes;
    private PanelKits panelKits;
    private PanelReporte panelReporte;

    private JTabbedPane tabbedPane;

    /**
     * Crea e inicializa la ventana principal.
     */
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * Inicializa todos los componentes gráficos de la ventana.
     */
    private void initComponents() {
        setTitle("AutoRescate 24/7");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblTitulo = new JLabel("AutoRescate 24/7", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(30, 30, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setPreferredSize(new Dimension(0, 60));
        add(lblTitulo, BorderLayout.NORTH);

        // Paneles
        panelVehiculos = new PanelVehiculos();
        panelTecnicos = new PanelTecnicos();
        panelSolicitudes = new PanelSolicitudes();
        panelKits = new PanelKits();
        panelReporte = new PanelReporte();

        // Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Vehículos", panelVehiculos);
        tabbedPane.addTab("Técnicos", panelTecnicos);
        tabbedPane.addTab("Solicitudes", panelSolicitudes);
        tabbedPane.addTab("Kits y Repuestos", panelKits);
        tabbedPane.addTab("Reporte", panelReporte);
        add(tabbedPane, BorderLayout.CENTER);

        // Footer
        JLabel lblFooter = new JLabel("Sistema de Gestión de Asistencia Vehicular", SwingConstants.CENTER);
        lblFooter.setOpaque(true);
        lblFooter.setBackground(new Color(30, 30, 30));
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setPreferredSize(new Dimension(0, 30));
        add(lblFooter, BorderLayout.SOUTH);
    }

    /**
     * Hace visible la ventana principal.
     */
    public void mostrar() {
        setVisible(true);
    }

    /**
     * Muestra un mensaje de error al usuario.
     *
     * @param mensaje texto del mensaje.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje informativo al usuario.
     *
     * @param mensaje texto del mensaje.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de alerta al usuario.
     *
     * @param mensaje texto de la alerta.
     */
    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Obtiene el panel de gestión de vehículos.
     *
     * @return panel de vehículos.
     */
    public PanelVehiculos getPanelVehiculos() {
        return panelVehiculos;
    }

    /**
     * Obtiene el panel de gestión de técnicos.
     *
     * @return panel de técnicos.
     */
    public PanelTecnicos getPanelTecnicos() {
        return panelTecnicos;
    }

    /**
     * Obtiene el panel de gestión de solicitudes.
     *
     * @return panel de solicitudes.
     */
    public PanelSolicitudes getPanelSolicitudes() {
        return panelSolicitudes;
    }

    /**
     * Obtiene el panel de gestión de kits y repuestos.
     *
     * @return panel de kits y repuestos.
     */
    public PanelKits getPanelKits() {
        return panelKits;
    }

    /**
     * Obtiene el panel de reportes.
     *
     * @return panel de reportes.
     */
    public PanelReporte getPanelReporte() {
        return panelReporte;
    }
}