package Control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import modelo.Solicitud;

public class ExportarCSVControl {

    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean exportarSolicitudes(Solicitud[] solicitudes, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {

            writer.write("id,cliente,telefono,tipo_servicio,prioridad,zona,hora_inicio,hora_fin,estado");
            writer.newLine();

            for (int i = 0; i < solicitudes.length; i++) {
                Solicitud s = solicitudes[i];
                writer.write(
                    s.getId() + "," +
                    s.getCliente().getNombre() + "," +
                    s.getCliente().getTelefono() + "," +
                    s.getServicio() + "," +
                    s.getPrioridad() + "," +
                    s.getZona() + "," +
                    s.getHoraRegistro().format(FORMATTER) + "," +
                    s.getHoraFin().format(FORMATTER) + "," +
                    s.getEstado()
                );
                writer.newLine();
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}