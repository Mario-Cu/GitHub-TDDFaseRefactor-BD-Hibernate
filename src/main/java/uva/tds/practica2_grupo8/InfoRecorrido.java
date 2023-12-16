package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa la información asociada a un recorrido, incluyendo la fecha, hora, plazas disponibles,
 * plazas totales y la duración en minutos.
 * 
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 */
public class InfoRecorrido {
    private LocalDate fecha;
    private LocalTime hora;
    private int plazasDisponibles;
    private int plazasTotales;
    private int minutos;

    /**
     * Constructor de la clase InfoRecorrido.
     * 
     * @param fecha               Fecha en la que se realiza el recorrido.
     * @param hora                Hora en la que se realiza el recorrido.
     * @param plazasDisponibles   Número de plazas disponibles del vehículo en un momento.
     * @param plazasTotales       Número de plazas totales del vehículo con el que se realiza el recorrido.
     * @param minutos             Duración del trayecto en minutos.
     */
    public InfoRecorrido(LocalDate fecha, LocalTime hora, int plazasDisponibles, int plazasTotales, int minutos) {

        this.fecha = fecha;
        this.hora = hora;
        this.plazasDisponibles = plazasDisponibles;
        this.plazasTotales = plazasTotales;
        this.minutos = minutos;
    }

    /**
     * Devuelve la fecha del recorrido.
     * 
     * @return fecha del recorrido
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del recorrido.
     * 
     * @param fecha nueva fecha del recorrido
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la hora del recorrido.
     * 
     * @return hora del recorrido
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Establece la hora del recorrido.
     * 
     * @param hora nueva hora del recorrido
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * Devuelve el número de plazas disponibles del recorrido.
     * 
     * @return número de plazas disponibles del recorrido
     */
    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    /**
     * Establece el número de plazas disponibles del recorrido.
     * 
     * @param plazasDisponiblesNuevas nuevas plazas disponibles del recorrido
     */
    public void setPlazasDisponibles(int plazasDisponiblesNuevas) {
        this.plazasDisponibles = plazasDisponiblesNuevas;
    }

    /**
     * Devuelve el número de plazas totales del recorrido.
     * 
     * @return número de plazas totales del recorrido
     */
    public int getPlazasTotales() {
        return plazasTotales;
    }

    /**
     * Establece el número de plazas totales del recorrido.
     * 
     * @param plazasTotalesNuevas nuevas plazas totales del recorrido
     */
    public void setPlazasTotales(int plazasTotalesNuevas) {
        this.plazasTotales = plazasTotalesNuevas;
    }

    /**
     * Devuelve la duración del recorrido en minutos.
     * 
     * @return duración del recorrido en minutos
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Establece la duración del recorrido en minutos.
     * 
     * @param minutosNuevos nueva duración del recorrido en minutos
     */
    public void setMinutos(int minutosNuevos) {
        this.minutos = minutosNuevos;
    }
}