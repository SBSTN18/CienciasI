package vista;

import modelo.Kits.Kit;
import modelo.Kits.Repuesto;

import javax.swing.*;
import java.awt.*;

public class PanelKits extends JPanel {

    // Kits
    private JTextField txtCantidadElementos;
    private JButton btnAgregarKit;
    private JButton btnRetirarKit;
    private JLabel lblTotalKits;

    // Repuestos
    private JTextField txtNombreRepuesto;
    private JTextField txtCantidadRepuesto;
    private JButton btnAgregarRepuesto;
    private JButton btnRetirarRepuesto;
    private JLabel lblTotalRepuestos;

    public PanelKits() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(1, 2, 10, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel Kits ----
        JPanel panelKits = new JPanel(new GridBagLayout());
        panelKits.setBorder(BorderFactory.createTitledBorder("Kits de Atención"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelKits.add(new JLabel("Cantidad de elementos:"), gbc);
        gbc.gridx = 1;
        txtCantidadElementos = new JTextField(10);
        panelKits.add(txtCantidadElementos, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        btnAgregarKit = new JButton("Agregar Kit");
        panelKits.add(btnAgregarKit, gbc);

        gbc.gridx = 1;
        btnRetirarKit = new JButton("Retirar Kit");
        panelKits.add(btnRetirarKit, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        lblTotalKits = new JLabel("Total kits: 0");
        panelKits.add(lblTotalKits, gbc);

        add(panelKits);

        // ---- Panel Repuestos ----
        JPanel panelRepuestos = new JPanel(new GridBagLayout());
        panelRepuestos.setBorder(BorderFactory.createTitledBorder("Repuestos"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelRepuestos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombreRepuesto = new JTextField(10);
        panelRepuestos.add(txtNombreRepuesto, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelRepuestos.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        txtCantidadRepuesto = new JTextField(10);
        panelRepuestos.add(txtCantidadRepuesto, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        btnAgregarRepuesto = new JButton("Agregar Repuesto");
        panelRepuestos.add(btnAgregarRepuesto, gbc);

        gbc.gridx = 1;
        btnRetirarRepuesto = new JButton("Retirar Repuesto");
        panelRepuestos.add(btnRetirarRepuesto, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        lblTotalRepuestos = new JLabel("Total repuestos: 0");
        panelRepuestos.add(lblTotalRepuestos, gbc);

        add(panelRepuestos);
    }

    public void actualizarInfo(int totalKits, int totalRepuestos) {
        lblTotalKits.setText("Total kits: " + totalKits);
        lblTotalRepuestos.setText("Total repuestos: " + totalRepuestos);
    }

    public void limpiarFormularioKit() {
        txtCantidadElementos.setText("");
    }

    public void limpiarFormularioRepuesto() {
        txtNombreRepuesto.setText("");
        txtCantidadRepuesto.setText("");
    }

    // ---- Getters para el controlador de vista ----
    public int getCantidadElementos() {
        try {
            return Integer.parseInt(txtCantidadElementos.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getNombreRepuesto() { return txtNombreRepuesto.getText().trim(); }

    public int getCantidadRepuesto() {
        try {
            return Integer.parseInt(txtCantidadRepuesto.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public JButton getBtnAgregarKit() { return btnAgregarKit; }
    public JButton getBtnRetirarKit() { return btnRetirarKit; }
    public JButton getBtnAgregarRepuesto() { return btnAgregarRepuesto; }
    public JButton getBtnRetirarRepuesto() { return btnRetirarRepuesto; }
}