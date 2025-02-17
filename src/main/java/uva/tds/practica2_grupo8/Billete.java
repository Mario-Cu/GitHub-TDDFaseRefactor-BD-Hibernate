package uva.tds.practica2_grupo8;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa un Billete.
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 * 
 */
@Entity
@Table(name = "BILLETE")
public class Billete {
	
	@EmbeddedId
	BilleteId id;
	@Enumerated(EnumType.STRING)
	EstadoBillete estado;
	@ManyToOne()
	@JoinColumn(name = "ID_RECORRIDO",referencedColumnName = "id")
	Recorrido recorrido;
	@ManyToOne()
	@JoinColumn(name = "NIF_USUARIO",referencedColumnName = "nif")
	Usuario usuario;

	/**
	 * Creacion de un billete vacio
	 */
	public Billete() {
		
	}
	
	/**
	 * Creacion de un billete
	 * @param id Identificador con el que encontramos el billete
	 * @param recorrido	Objeto el cual nos indica el recorrido al que está asociado el billete
	 * @param usuario Objeto el cual nos indica a que usuario le pertenece el billete
	 * @throws IllegalArgumentException si la longitud del localizador es menor que 1
	 * @throws IllegalArgumentException si la longitud del localizador es mayor que 8
	 * @throws IllegalArgumentException si localizador es nulo
	 * @throws IllegalArgumentException si recorrido es nulo
	 * @throws IllegalArgumentException si usuario es nulo
	 */
	public Billete(BilleteId id, Recorrido recorrido, Usuario usuario) {
		if(id == null) {
			throw new IllegalArgumentException("El localizador no puede ser nulo");
		}
		if(recorrido == null ) {
			throw new IllegalArgumentException("El recorrido  no puede ser nulo");
		}
		if(usuario == null) {
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		}
		if(id.getLocalizador().length()<1) {
			throw new IllegalArgumentException("El localizador tiene al menos 1 caracter");
		}
		if(id.getLocalizador().length()>8) {
			throw new IllegalArgumentException("El localizador no puede tener mas de 8 caracteres");
		}
		this.id = id;
		this.recorrido = recorrido;
		this.usuario = usuario;
	
		
	}
	
	/**
	 * Metodo que devuelve el localizador del billete
	 * @return localizador del billete
	 */
	public BilleteId getId() {
		return this.id;
	}
	
	/**
	 * Metodo que devuelve el estado del billete
	 * @return localizador del billete
	 */
	public EstadoBillete getEstado() {
		return this.estado;
	}
	
	/**
	 * Metodo que settea el estado del billete
	 * @param estado del billete
	 */
	public void setEstado(EstadoBillete estado) {
		this.estado = estado;
	}

	/**
	 * Metodo que devuelve el recorrido del billete
	 * @return recorrido del billete
	 */
	public Recorrido getRecorrido() {
		return this.recorrido;
	}

	/**
	 * Metodo que devuelve el usuario del billete
	 * @return usuario del billete
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	/**
	 * Override de hashCode obligado por override de equals 
	 */
	@Override 
	public int hashCode() {
		return 0;
	}
	
	
	/*
	 * Override de equals() para comparar dos billetes
	 * @return boolean que puede ser true o false 
	 */
	@Override
	public boolean equals(Object o) {
		Boolean valor = true;
		
		if(!(o instanceof Billete)) 
			return false;
		Billete b = (Billete) o;
		if( !this.id.equals(b.id) || !this.recorrido.equals(b.recorrido) || !this.usuario.equals(b.usuario))
			valor = false;
		
		return valor;
	}
    
}
