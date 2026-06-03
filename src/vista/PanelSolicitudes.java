package vista;

import modelo.Solicitud;
import modelo.Vehiculo;
import modelo.Tecnico;
import modelo.Enums.Prioridad;
import modelo.Enums.TipoCliente;
import modelo.Enums.TipoServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelSolicitudes extends JPanel {

    // Formulario nueva solicitud
    private JTextField txtZona;
    private JTextField txtNombreCliente;
    private JTextField txtTelefonoCliente;
    private JComboBox<TipoServicio> comboServicio;
    private JComboBox<Prioridad> comboPrioridad;
    private JComboBox<TipoCliente> comboTipoCliente;
    private JButton btnCrearSolicitud;

    // Asignacion de recursos
    private JComboBox<Vehiculo> comboVehiculos;
    private JComboBox<Tecnico> comboTecnicos;
    private JButton btnSiguienteSolicitud;
    private JButton btnAsignarRecursos;
    private JButton btnCerrarSolicitud;
    private JLabel lblSolicitudActual;

    // Tabla
    private JTable tablaSolicitudes;
    private DefaultTableModel modeloTabla;

    private Solicitud solicitudActual;

    public PanelSolicitudes() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel formulario nueva solicitud ----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Nueva Solicitud"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Zona:"), gbc);
        gbc.gridx = 1;
        txtZona = new JTextField(15);
        panelFormulario.add(txtZona, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Servicio:"), gbc);
        gbc.gridx = 1;
        comboServicio = new JComboBox<>(TipoServicio.values());
        panelFormulario.add(comboServicio, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Prioridad:"), gbc);
        gbc.gridx = 1;
        comboPrioridad = new JComboBox<>(Prioridad.values());
        panelFormulario.add(comboPrioridad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        txtNombreCliente = new JTextField(15);
        panelFormulario.add(txtNombreCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefonoCliente = new JTextField(15);
        panelFormulario.add(txtTelefonoCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Tipo Cliente:"), gbc);
        gbc.gridx = 1;
        comboTipoCliente = new JComboBox<>(TipoCliente.values());
        panelFormulario.add(comboTipoCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        btnCrearSolicitud = new JButton("Crear Solicitud");
        panelFormulario.add(btnCrearSolicitud, gbc);

        // ---- Panel asignacion ----
        JPanel panelAsignacion = new JPanel(new GridBagLayout());
        panelAsignacion.setBorder(BorderFactory.createTitledBorder("Asignar Recursos"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        lblSolicitudActual = new JLabel("Sin solicitud seleccionada");
        panelAsignacion.add(lblSolicitudActual, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panelAsignacion.add(new JLabel("Vehículo:"), gbc);
        gbc.gridx = 1;
        comboVehiculos = new JComboBox<>();
        panelAsignacion.add(comboVehiculos, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelAsignacion.add(new JLabel("Técnico:"), gbc);
        gbc.gridx = 1;
        comboTecnicos = new JComboBox<>();
        panelAsignacion.add(comboTecnicos, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        btnSiguienteSolicitud = new JButton("Siguiente Solicitud");
        panelAsignacion.add(btnSiguienteSolicitud, gbc);

        gbc.gridx = 1;
        btnAsignarRecursos = new JButton("Asignar Recursos");
        panelAsignacion.add(btnAsignarRecursos, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnCerrarSolicitud = new JButton("Cerrar Solicitud");
        panelAsignacion.add(btnCerrarSolicitud, gbc);

        // ---- Panel superior ----
        JPanel panelSuperior = new JPanel(new GridLayout(1, 2, 10, 0));
        panelSuperior.add(panelFormulario);
        panelSuperior.add(panelAsignacion);
        add(panelSuperior, BorderLayout.NORTH);

        // ---- Tabla ----
        String[] columnas = {"ID", "Zona", "Servicio", "Prioridad", "Cliente", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaSolicitudes = new JTable(modeloTabla);
        tablaSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tablaSolicitudes), BorderLayout.CENTER);
    }

    public void cargarTabla(Solicitud[] solicitudes) {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < solicitudes.length; i++) {
            Solicitud s = solicitudes[i];
            modeloTabla.addRow(new Object[]{
                s.getId(),
                s.getZona(),
                s.getServicio(),
                s.getPrioridad(),
                s.getCliente().getNombre(),
                s.getEstado()
            });
        }
    }

    public void mostrarSolicitud(Solicitud solicitud) {
        this.solicitudActual = solicitud;
        lblSolicitudActual.setText("Solicitud: " + solicitud.getId() +
            " | " + solicitud.getServicio() +
            " | " + solicitud.getPrioridad());
    }

    public void cargarVehiculosDisponibles(Vehiculo[] vehiculos) {
        comboVehiculos.removeAllItems();
        for (int i = 0; i < vehiculos.length; i++) {
            comboVehiculos.addItem(vehiculos[i]);
        }
    }

    public void cargarTecnicosDisponibles(Tecnico[] tecnicos) {
        comboTecnicos.removeAllItems();
        for (int i = 0; i < tecnicos.length; i++) {
            comboTecnicos.addItem(tecnicos[i]);
        }
    }

    public void limpiarFormulario() {
        txtZona.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
    }

    // ---- Getters para el controlador de vista ----
    public String getZona() { return txtZona.getText().trim(); }
    public String getNombreCliente() { return txtNombreCliente.getText().trim(); }
    public String getTelefonoCliente() { return txtTelefonoCliente.getText().trim(); }
    public TipoServicio getServicioSeleccionado() { return (TipoServicio) comboServicio.getSelectedItem(); }
    public Prioridad getPrioridadSeleccionada() { return (Prioridad) comboPrioridad.getSelectedItem(); }
    public TipoCliente getTipoClienteSeleccionado() { return (TipoCliente) comboTipoCliente.getSelectedItem(); }
    public Vehiculo getVehiculoSeleccionado() { return (Vehiculo) comboVehiculos.getSelectedItem(); }
    public Tecnico getTecnicoSeleccionado() { return (Tecnico) comboTecnicos.getSelectedItem(); }
    public Solicitud getSolicitudActual() { return solicitudActual; }

    public JButton getBtnCrearSolicitud() { return btnCrearSolicitud; }
    public JButton getBtnSiguienteSolicitud() { return btnSiguienteSolicitud; }
    public JButton getBtnAsignarRecursos() { return btnAsignarRecursos; }
    public JButton getBtnCerrarSolicitud() { return btnCerrarSolicitud; }
}