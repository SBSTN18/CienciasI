package Control;

import modelo.Tecnico;
import modelo.Coleccion.Lista;
import modelo.Coleccion.Nodo;
import modelo.Enums.Estado;
import modelo.Enums.Especialidad;

/**
 * Gestiona el registro y consulta de técnicos del sistema.
 * Permite agregar técnicos, buscar técnicos disponibles y
 * realizar consultas según su estado.
 *
 * @author AutoRescate 24/7
 * @version 1.0
 */
public class ListaTecnicosControl {

    private Lista<Tecnico> tecnicos;

    /**
     * Inicializa la lista de técnicos.
     */
    public ListaTecnicosControl() {
        this.tecnicos = new Lista<>();
    }

    /**
     * Registra un nuevo técnico en el sistema.
     *
     * @param nombre nombre del técnico.
     * @param zona zona de operación.
     * @param especialidad especialidad del técnico.
     */
    public void agregarTecnico(String nombre, String zona, Especialidad especialidad) {
        Tecnico tecnico = new Tecnico(nombre, zona, especialidad);
        tecnicos.agregar(tecnico);
    }

    /**
     * Busca un técnico disponible que coincida con la especialidad
     * y la zona especificadas.
     *
     * @param especialidad especialidad requerida.
     * @param zona zona requerida.
     * @return técnico disponible o null si no se encuentra uno.
     */
    public Tecnico buscarDisponible(Especialidad especialidad, String zona) {
        Nodo<Tecnico> actual = tecnicos.getCabeza();

        while (actual != null) {
            Tecnico t = actual.getDato();

            if (t.getEstado() == Estado.DISPONIBLE
                    && t.getEspecialidad() == especialidad
                    && t.getZona().equals(zona)) {
                return t;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    /**
     * Obtiene todos los técnicos registrados.
     *
     * @return arreglo con todos los técnicos.
     */
    public Tecnico[] obtenerTodos() {
        Tecnico[] resultado = new Tecnico[tecnicos.getTamaño()];
        Nodo<Tecnico> actual = tecnicos.getCabeza();
        int i = 0;

        while (actual != null) {
            resultado[i++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        return resultado;
    }

    /**
     * Obtiene los técnicos que se encuentran en un estado específico.
     *
     * @param estado estado a consultar.
     * @return arreglo de técnicos que cumplen la condición.
     */
    public Tecnico[] obtenerPorEstado(Estado estado) {
        int total = 0;
        Nodo<Tecnico> actual = tecnicos.getCabeza();

        while (actual != null) {
            if (actual.getDato().getEstado() == estado) {
                total++;
            }
            actual = actual.getSiguiente();
        }

        Tecnico[] resultado = new Tecnico[total];
        actual = tecnicos.getCabeza();
        int i = 0;

        while (actual != null) {
            if (actual.getDato().getEstado() == estado) {
                resultado[i++] = actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return resultado;
    }

    /**
     * Obtiene la cantidad total de técnicos registrados.
     *
     * @return total de técnicos.
     */
    public int getTotalTecnicos() {
        return tecnicos.getTamaño();
    }
}