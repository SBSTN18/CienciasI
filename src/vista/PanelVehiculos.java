package vista;

import modelo.Vehiculo;
import modelo.Enums.TipoVehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelVehiculos extends JPanel {

    private JComboBox<TipoVehiculo> comboTipo;
    private JTextField txtZona;
    private JButton btnRegistrar;
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTabla;

    public PanelVehiculos() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel formulario ----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Vehículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        comboTipo = new JComboBox<>(TipoVehiculo.values());
        panelFormulario.add(comboTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Zona:"), gbc);
        gbc.gridx = 1;
        txtZona = new JTextField(15);
        panelFormulario.add(txtZona, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        btnRegistrar = new JButton("Registrar Vehículo");
        panelFormulario.add(btnRegistrar, gbc);

        add(panelFormulario, BorderLayout.NORTH);

        // ---- Tabla ----
        String[] columnas = {"ID", "Tipo", "Zona", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaVehiculos = new JTable(modeloTabla);
        tablaVehiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
    }

    public void cargarTabla(Vehiculo[] vehiculos) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < vehiculos.length; i++) {
            Vehiculo v = vehiculos[i];
            modeloTabla.addRow(new Object[]{
                v.getId(),
                v.getTipo(),
                v.getZona(),
                v.getEstado()
            });
        }
    }

    // ---- Getters para el controlador de vista ----
    public TipoVehiculo getTipoSeleccionado() {
        return (TipoVehiculo) comboTipo.getSelectedItem();
    }

    public String getZona() {
        return txtZona.getText().trim();
    }

    public void limpiarFormulario() {
        txtZona.setText("");
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
}