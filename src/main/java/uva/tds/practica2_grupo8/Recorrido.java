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
	 * @throws IllegalArgumentException si las plazas disponibles son menores que 0 y mayores que 50 e autobues y menores que 0  y mayores que 250 en tren.
	 * @throws IllegalArgumentException si el numero de plazas es menor que 1 y mayor 50 en un autobus y menor que 1 y mayor que 250 en un tren.
	 * @throws IllegalArgumentException si la duracion en minutos es negativa.
	 */
	
	String id;
	String origen;
	String destino;
	String medioTransporte;
	float precio;
	LocalDate fecha;
	LocalTime hora;
	int plazasDisponibles;
	int plazasTotales;
	int minutos;
	
	public Recorrido (String id, String origen, String destino, String medioTransporte, float precio,LocalDate fecha,LocalTime hora,int plazasDisponibles, int plazasTotales, int minutos) throws IllegalArgumentException  {
		if(id == null) {
			throw new IllegalArgumentException("El id no puede ser nulo");
		}
		
		if(id.length()<1) {
			throw new IllegalArgumentException("El id tiene menos de un caracter");
		}
		if(origen == null) {
			throw new IllegalArgumentException("El origen no puede ser nulo");
		}
		
		if(origen.length()<1) {
			throw new IllegalArgumentException("El origen tiene menos de un caracter");
		}
		
		if(destino == null) {
			throw new IllegalArgumentException("El destino no puede ser nulo");
		}
		
		if(destino.length()<1) {
			throw new IllegalArgumentException("El destino tiene menos de un caracter");
		}
		
		if(medioTransporte == null) {
			throw new IllegalArgumentException("El medio de transporte no puede ser nulo");
		}
		if(!medioTransporte.equals("autobus") && !medioTransporte.equals("tren")) {
			throw new IllegalArgumentException("El medio de transporte debe ser autobus o tren");
		}
		
		if(precio < 0) {
			throw new IllegalArgumentException("El precio es menor que 0");
		}
		if(fecha == null) {
			throw new IllegalArgumentException("La fecha es nula");
		}
		if(hora == null) {
			throw new IllegalArgumentException("La hora es nula");
		}
		
		if(medioTransporte.equals("autobus")) {
			if((plazasDisponibles<0)||(plazasDisponibles>plazasTotales)) {
				throw new IllegalArgumentException("El numero de plazas disponibles es erroneo");
			}
		}else if(medioTransporte.equals("tren")) {
			if((plazasDisponibles<0)||(plazasDisponibles>plazasTotales)) {
				throw new IllegalArgumentException("El numero de plazas disponibles es erroneo");
			}
		}
		if(medioTransporte.equals("autobus")) {
			if((plazasTotales<1)||(plazasTotales>50)) {
				throw new IllegalArgumentException("El numero de plazas totales es erroneo");
			}
		}else if(medioTransporte.equals("tren")) {
			if((plazasTotales<1)||(plazasTotales>250)) {
				throw new IllegalArgumentException("El numero de plazas totales es erroneo");
			}
		}
		if(minutos<0) {
			throw new IllegalArgumentException("La duracion del trayecto es erronea");
		}
		this.id = id;
		this.destino = destino;
		this.origen = origen;
		this.medioTransporte = medioTransporte;
		this.precio = precio;
		this.fecha = fecha;
		this.hora = hora;
		this.plazasDisponibles = plazasDisponibles;
		this.plazasTotales = plazasTotales;
		this.minutos = minutos;
		
	}
	/**
	 * Devuelve el identificador del recorrido 
	 * @return identificador del recorrido
	 */
	public String getId() {
		return id;
	}
	/**
	 * Devuelve el origen del recorrido
	 * @return origen del recorrido
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * Devuelve el destino del recorrido
	 * @return destino del recorrido
	 */
	public String getDestino() {
		return destino;
	}
	/**
	 * Devuelve el medio de transporte del recorrido
	 * @return medio de transporte del recorrido
	 */
	public String getMedioTransporte() {
		return medioTransporte;
	}
	/**
	 * Devuelve el precio del recorrido
	 * @return precio del recorrido
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * Devuelve la fecha del recorrido 
	 * @return fecha del recorrido
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Devuelve la hora del recorrido
	 * @return hora del recorrido
	 */
	public LocalTime getHora() {
		return hora;
	}
	
	/**
	 * Actualiza la fecha del recorrido 
	 * @return fecha del recorrido
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Actualiza la hora del recorrido
	 * @return hora del recorrido
	 */
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	/**
	 * Devuelve las plazas dispnibles del recorrido
	 * @return plazas disponibles del recorrido
	 */
	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}
	
	public void setPlazasDisponibles(int plazasDisponiblesNuevas) {
		this.plazasDisponibles = plazasDisponiblesNuevas;
	}
	/**
	 * Devuelve las plazas totales del recorrido
	 * @return plazas totales del recorrido
	 */
	public int getPlazasTotales() {
		return plazasTotales;
	}
	/**
	 * Devuelve la duracion del recorrido
	 * @return duracion del recorrido
	 */
	public int getDuracion() {
		return minutos;
	}
	/*
	 * Override de equals() para comparar dos recorridos
	 * @return boolean que puede ser true o false 
	 */
	@Override
	public boolean equals(Object o) {
		
		if(o == this)
			return true;
		if(o == null)
			return false;
		
		if(!(o instanceof Recorrido))
			return false;
		
		Recorrido r = (Recorrido) o;
		
		if(!this.id.equals(r.id)) 
			return false;
		if(!this.destino.equals(r.destino)) 
			return false;
		if(!this.origen.equals(r.origen)) 
			return false;
		if(!this.medioTransporte.equals(r.medioTransporte))
			return false;
		if(this.precio != r.precio)
			return false;
		if(!this.fecha.equals(r.fecha))
			return false;
		if(!this.hora.equals(r.hora))
			return false;
		if(this.plazasTotales != r.plazasTotales)
			return false;
		if(this.minutos != r.minutos)
			return false;
		
		return true;
	}
}
