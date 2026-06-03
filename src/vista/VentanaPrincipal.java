package vista;

import Control.Vista.ControladorVista;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelVehiculos panelVehiculos;
    private PanelTecnicos panelTecnicos;
    private PanelSolicitudes panelSolicitudes;
    private PanelKits panelKits;
    private PanelReporte panelReporte;

    private JTabbedPane tabbedPane;

    public VentanaPrincipal() {
        initComponents();
    }

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

    public void mostrar() {
        setVisible(true);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
    }

    public PanelVehiculos getPanelVehiculos() { return panelVehiculos; }
    public PanelTecnicos getPanelTecnicos() { return panelTecnicos; }
    public PanelSolicitudes getPanelSolicitudes() { return panelSolicitudes; }
    public PanelKits getPanelKits() { return panelKits; }
    public PanelReporte getPanelReporte() { return panelReporte; }
}