package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * Clase que representa la información asociada a un recorrido, incluyendo la fecha, hora, plazas disponibles,
 * plazas totales y la duración en minutos.
 * 
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 */
@Entity
public class InfoRecorrido {
	@Id
	@Column(name ="recorrido_id")
	private String id;
    private LocalDate fecha;
    private LocalTime hora;
    private int plazasDisponibles;
    private int plazasTotales;
    private int minutos;
    @OneToOne()
    @MapsId
    @JoinColumn(name = "recorrido_id",referencedColumnName = "id")
    private Recorrido recorrido;
    
    /**
     * Constructor vacio de InfoRecorrido
     */
    public InfoRecorrido() {
    	
    }
    
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
     * @param minutosNuevos nueva duración del recorrido en minutos
     */
    public void setMinutos(int minutosNuevos) {
        this.minutos = minutosNuevos;
    }
    
    /**
     * Establece la duración del recorrido en minutos.
     * @param recorrido nuevo recorrido
     */
    public void setRecorrido(Recorrido recorrido) {
    	this.recorrido = recorrido;
    }
    
    /**
     * Obtiene el recorrido solicitado
     * @return recorrido asociado
     */
    public Recorrido getRecorrido() {
    	return recorrido;
    }
    
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
		
		if(!(o instanceof InfoRecorrido)) {
			return false;
		}
		InfoRecorrido r = (InfoRecorrido) o;
		if( !this.fecha.equals(r.fecha) 
			|| !this.hora.equals(r.hora)
			|| this.plazasDisponibles != r.plazasDisponibles 
			|| this.plazasTotales != r.plazasTotales 
			|| this.minutos != r.minutos) {
			valor = false;
		}
		return valor;	
    }

}
