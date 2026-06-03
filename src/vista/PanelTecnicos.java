package vista;

import modelo.Tecnico;
import modelo.Enums.Especialidad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTecnicos extends JPanel {

    private JTextField txtNombre;
    private JTextField txtZona;
    private JComboBox<Especialidad> comboEspecialidad;
    private JButton btnRegistrar;
    private JTable tablaTecnicos;
    private DefaultTableModel modeloTabla;

    public PanelTecnicos() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel formulario ----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Técnico"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Zona:"), gbc);
        gbc.gridx = 1;
        txtZona = new JTextField(15);
        panelFormulario.add(txtZona, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Especialidad:"), gbc);
        gbc.gridx = 1;
        comboEspecialidad = new JComboBox<>(Especialidad.values());
        panelFormulario.add(comboEspecialidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnRegistrar = new JButton("Registrar Técnico");
        panelFormulario.add(btnRegistrar, gbc);

        add(panelFormulario, BorderLayout.NORTH);

        // ---- Tabla ----
        String[] columnas = {"ID", "Nombre", "Zona", "Especialidad", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTecnicos = new JTable(modeloTabla);
        tablaTecnicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaTecnicos), BorderLayout.CENTER);
    }

    public void cargarTabla(Tecnico[] tecnicos) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < tecnicos.length; i++) {
            Tecnico t = tecnicos[i];
            modeloTabla.addRow(new Object[]{
                t.getId(),
                t.getNombre(),
                t.getZona(),
                t.getEspecialidad(),
                t.getEstado()
            });
        }
    }

    // ---- Getters para el controlador de vista ----
    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getZona() {
        return txtZona.getText().trim();
    }

    public Especialidad getEspecialidadSeleccionada() {
        return (Especialidad) comboEspecialidad.getSelectedItem();
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtZona.setText("");
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
}