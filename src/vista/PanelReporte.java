package vista;

import javax.swing.*;
import java.awt.*;

public class PanelReporte extends JPanel {

    private JTextField txtRutaArchivo;
    private JButton btnExportar;
    private JButton btnSeleccionarRuta;
    private JLabel lblEstado;

    public PanelReporte() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel formulario ----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Exportar Reporte del Día"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Ruta del archivo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtRutaArchivo = new JTextField(25);
        txtRutaArchivo.setEditable(false);
        panelFormulario.add(txtRutaArchivo, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        btnSeleccionarRuta = new JButton("Seleccionar");
        panelFormulario.add(btnSeleccionarRuta, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        btnExportar = new JButton("Exportar CSV");
        panelFormulario.add(btnExportar, gbc);

        gbc.gridy = 2;
        lblEstado = new JLabel(" ");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        panelFormulario.add(lblEstado, gbc);

        add(panelFormulario, BorderLayout.NORTH);
    }

    public void mostrarEstado(String mensaje, boolean exito) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(exito ? new Color(0, 150, 0) : Color.RED);
    }

    public void setRutaArchivo(String ruta) {
        txtRutaArchivo.setText(ruta);
    }

    // ---- Getters para el controlador de vista ----
    public String getRutaArchivo() { return txtRutaArchivo.getText().trim(); }

    public JButton getBtnExportar() { return btnExportar; }
    public JButton getBtnSeleccionarRuta() { return btnSeleccionarRuta; }
}