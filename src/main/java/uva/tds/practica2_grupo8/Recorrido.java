package uva.tds.practica2_grupo8;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * Clase que representa un Recorrido.
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 */
	
@Entity	 
@Table(name = "RECORRIDO")
public class Recorrido {
	
	@Id
	String id;
	String origen;
	String destino;
	String medioTransporte;
	float precio;
	@OneToOne(mappedBy = "recorrido", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	InfoRecorrido infoRecorrido;
	private static final String AUTOBUS = "autobus";
	private static final String TREN = "tren";
	@OneToMany(mappedBy = "recorrido",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	List<Billete> billetes; 


	/**
	 * Constructor vacio de un recorrido
	 */
	public Recorrido() {
		
	}
	
	
	/**
	 * Creacion de un recorrido
	 * @author mardano (Mario Danov Ivanov)
	 * @param id Identificador del recorrido. No puede tener menos que un caracter
	 * @param origen Desde donde sale el recorrido. No puede tener menos que un caracter
	 * @param destino Hasta donde llega el recorrido. No puede tener menos que un caracter
	 * @param medioTransporte Vehiculo con el que se realiza el recorrido. Tiene que ser autobus o tren
	 * @param precio Cuanto precio cuesta el recorrido. Debe ser un numero mayor o igual a 0.
	 * @param infoRecorrido Objeto de informacion del recorrido.
	 * @throws IllegalArgumentException si la longitud de el id, el origen o el destino es menor que 1
	 * @throws IllegalArgumentException si el medio de transporte no es autobus o tren
	 * @throws IllegalArgumentException si el precio es menor que 0
	 * @throws IllegalArgumentException si la fecha o la hora o ambas son nulas
	 * @throws IllegalArgumentException si las plazas disponibles son menores que 0 y mayores que 50 e autobues y menores que 0  y mayores que 250 en tren.
	 * @throws IllegalArgumentException si el numero de plazas es menor que 1 y mayor 50 en un autobus y menor que 1 y mayor que 250 en un tren.
	 * @throws IllegalArgumentException si la duracion en minutos es negativa.
	 */
	public Recorrido (String id, String origen, String destino, String medioTransporte, float precio, InfoRecorrido infoRecorrido) throws IllegalArgumentException  {
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
		if(!medioTransporte.equals(AUTOBUS) && !medioTransporte.equals(TREN)) {
			throw new IllegalArgumentException("El medio de transporte debe ser autobus o tren");
		}
		
		if(precio < 0) {
			throw new IllegalArgumentException("El precio es menor que 0");
		}
		
		comprobacionesInfoRecorrido(medioTransporte,infoRecorrido);
		
		this.id = id;
		this.destino = destino;
		this.origen = origen;
		this.medioTransporte = medioTransporte;
		this.precio = precio;
		this.infoRecorrido = infoRecorrido;
		
	}
	
	private static boolean valido(int plazasDisponibles, int plazasTotales) {
		Boolean valor = false;  
		if(plazasDisponibles>plazasTotales || plazasDisponibles < 0) {
			  valor = true;
		}
		return valor;
	}
	
	private static void comprobacionesInfoRecorrido(String medioTransporte, InfoRecorrido infoRecorrido) {

		if(infoRecorrido.getFecha() == null) {
			throw new IllegalArgumentException("La fecha es nula");
		}
		if(infoRecorrido.getHora() == null) {
			throw new IllegalArgumentException("La hora es nula");
		}
		
		if(medioTransporte.equals(AUTOBUS) && valido(infoRecorrido.getPlazasDisponibles(),infoRecorrido.getPlazasTotales())) {
			throw new IllegalArgumentException("El numero de plazas disponibles es erroneo");
		}
		if(medioTransporte.equals(TREN) && valido(infoRecorrido.getPlazasDisponibles(),infoRecorrido.getPlazasTotales())) {
			throw new IllegalArgumentException("El numero de plazas disponibles es erroneo");
		}
		if(medioTransporte.equals(AUTOBUS)  && valido2(infoRecorrido.getPlazasTotales(),50)) {
				throw new IllegalArgumentException("El numero de plazas totales es erroneo");
		}
		if(medioTransporte.equals(TREN) && valido2(infoRecorrido.getPlazasTotales(),250)) {
			throw new IllegalArgumentException("El numero de plazas totales es erroneo");
		}
		if(infoRecorrido.getMinutos()<0) {
			throw new IllegalArgumentException("La duracion del trayecto es erronea");
		}
	}
	
	private static boolean valido2(int plazasTotales, int valormax) {
		Boolean valor = false;  
		if(plazasTotales>valormax || plazasTotales < 1) {
			  valor = true;
		}
		return valor;
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
     * Devuelve la información asociada al recorrido.
     * 
     * @return información asociada al recorrido
     */
    public InfoRecorrido getInfoRecorrido() {
        return infoRecorrido;
    }
    
	/*
	 * Override de equals() para comparar dos recorridos
	 * @return boolean que puede ser true o false 
	 */
	
	/**
	 * Override de hashCode obligado por override de equals 
	 */
	@Override 
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Override de equals
	 */
	@Override
	public boolean equals(Object o) {
		Boolean valor = true;
		
		if(!(o instanceof Recorrido)) {
			return false;
		}
		Recorrido r = (Recorrido) o;
		if( !this.id.equals(r.id) ) {
			valor = false;
		}
		return valor;
	}
	
}
