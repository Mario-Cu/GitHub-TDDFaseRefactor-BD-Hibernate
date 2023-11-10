package uva.tds.practica2_grupo8;


import java.time.LocalDate;
import java.time.LocalTime;

public class Recorrido {

	/**
	 * Creacion de un recorrido
	 * @author mardano (Mario Danov Ivanov)
	 * @param id Identificador del recorrido. No puede tener menos que un caracter
	 * @param origen Desde donde sale el recorrido. No puede tener menos que un caracter
	 * @param destino Hasta donde llega el recorrido. No puede tener menos que un caracter
	 * @param medioTransporte Vehiculo con el que se realiza el recorrido. Tiene que ser autobus o tren
	 * @param precio Cuanto precio cuesta el recorrido. Debe ser un numero mayor o igual a 0.
	 * @param fecha Fecha en LocalDate en que se realiza el recorrido
	 * @param hora Hora el LocalTime en el que se realiza el recorrido
	 * @param plazasDisponibles Numero de plazas disponibles del vehiculo en un momento
	 * @param plazasTotales Numero de plazas totales del vehiculo con el que se realiza el recorrido. Entre 1 y 50 para autobus y entre 1 y 250 para el tren.
	 * @param minutos Duracion del trayecto en minutos.
	 * @throws IllegalArgumentException si la longitud de el id, el origen o el destino es menor que 1
	 * @throws IllegalArgumentException si el medio de transporte no es autobus o tren
	 * @throws IllegalArgumentException si el precio es menor que 0
	 * @throws IllegalArgumentException si la fecha o la hora o ambas son nulas
	 * @throws IllegalArgumentException si el numero de plazas es menor que 1 y mayor 50 en un autobus y menor que 1 y mayor que 250 en un tren.
	 * @throws IllegalArgumentException si la duracion en minutos es negativa.
	 */
	public Recorrido (String id, String origen, String destino, String medioTransporte, float precio,LocalDate fecha,LocalTime hora,int plazasDisponibles, int plazasTotales, int minutos) {
		
	}
	/**
	 * Devuelve el identificador del recorrido 
	 * @return identificador del recorrido
	 */
	public String getId() {
		return null;
	}
	/**
	 * Devuelve el origen del recorrido
	 * @return origen del recorrido
	 */
	public String getOrigen() {
		return null;
	}
	/**
	 * Devuelve el destino del recorrido
	 * @return destino del recorrido
	 */
	public String getDestino() {
		return null;
	}
	/**
	 * Devuelve el medio de transporte del recorrido
	 * @return medio de transporte del recorrido
	 */
	public String getMedioTransporte() {
		return null;
	}
	/**
	 * Devuelve el precio del recorrido
	 * @return precio del recorrido
	 */
	public float getPrecio() {
		return 0;
	}
	/**
	 * Devuelve la fecha del recorrido 
	 * @return fecha del recorrido
	 */
	public LocalDate getFecha() {
		return null;
	}
	/**
	 * Devuelve la hora del recorrido
	 * @return hora del recorrido
	 */
	public LocalTime getHora() {
		return null;
	}
	/**
	 * Devuelve las plazas dispnibles del recorrido
	 * @return plazas disponibles del recorrido
	 */
	public int getPlazasDisponibles() {
		return 0;
	}
	/**
	 * Devuelve las plazas totales del recorrido
	 * @return plazas totales del recorrido
	 */
	public int getPlazasTotales() {
		return 0;
	}
	/**
	 * Devuelve la duracion del recorrido
	 * @return duracion del recorrido
	 */
	public int getDuracion() {
		return 0;
	}
}
