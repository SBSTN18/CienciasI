package Control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import modelo.Solicitud;

/**
 * Gestiona la exportación de solicitudes a archivos CSV.
 * Permite generar reportes con la información de las solicitudes
 * cerradas registradas en el sistema.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ExportarCSVControl {

    /**
     * Formato utilizado para representar fechas y horas en el archivo CSV.
     */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Exporta un conjunto de solicitudes a un archivo CSV.
     *
     * @param solicitudes solicitudes que se desean exportar.
     * @param rutaArchivo ruta donde se generará el archivo.
     * @return true si la exportación fue exitosa, false en caso contrario.
     */
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