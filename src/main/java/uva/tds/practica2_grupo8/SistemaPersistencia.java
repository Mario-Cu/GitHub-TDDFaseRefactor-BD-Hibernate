package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que define los métodos necesarios para gestionar usuarios, recorridos y billetes a traves de una interfaz 
 * en una base de datos
 */
public class SistemaPersistencia {

	private IDatabaseManager databaseManager;
	
	/**
	 * Creación del Sistema
	 */
	public SistemaPersistencia(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	/**
	 * Añade un recorrido al arraylist de recorridos
	 * @param recorrido Recorrido que quieres añadir al sistema
	 * @throws IllegalStateException cuando se añade un recorrido con un identificador igual a otro
	 * @throws IllegalArgumentException cuando se añade un recorrido nulo
	 */
	public void añadirRecorrido(Recorrido recorrido) {
		for(Recorrido item : this.databaseManager.getRecorridos()) {
			if(item.getId() == recorrido.getId()){
				throw new IllegalStateException("El recorrido que intentas añadir ya existe en el sistema");
			}
		}
		try {
			this.databaseManager.addRecorrido(recorrido);
		} catch (IllegalArgumentException e1) {
			throw e1;
		}
	}
	
	/**
	 * Elimina un recorrido del arraylist de recorridos	
	 * @param id Identificador del recorridow
	 * @throws IllegalStateException cuando se elimina un recorrido con billetes asociados
	 * @throws IllegalArgumentException cuando se intenta eliminar un recorrido con identificador nulo
	 */
	public void eliminarRecorrido(String id) {
		for(Billete item : this.databaseManager.getBilletesDeRecorrido(id)) {
			if(item.getRecorrido().getId() == id){
				throw new IllegalStateException("El recorrido que intentas eliminar tiene un billete asociado");
			}
		}
		try {
			this.databaseManager.eliminarRecorrido(id);
		} catch (IllegalArgumentException e1) {
			throw e1;
		}

	}
	
	/**
	 * Actualiza el recorrido cuyo identificador coincida con el proporcionado. 
	 * @param recorrido El recorrido con los datos a actualizar
	 * @throws IllegalArgumentException si recorrido es nulo
	 * @throws IllegalStateException si no existe un recorrido con el identificador indicado en recorrido
	 */
	public void actualizarRecorrido(Recorrido recorrido) {
		if(this.databaseManager.getRecorrido(recorrido.getId()) == null){
			throw new IllegalStateException("El recorrido que intentas actualizar no existe en el sistema");
		}
		try {
			this.databaseManager.actualizarRecorrido(recorrido);
		} catch (IllegalArgumentException e1) {
			throw e1;
		}
	}
	
	
	/**
	 * Metodo para la compra de billetes en un sistema
	 * @param loc Localizador del billete
	 * @param usr Usuario que realiza la compra
	 * @param rec Recorrido al cual esta asociado el billete
	 * @param numBilletes Cantidad de billetes que busca comprar
	 * @throws IllegalArgumentException cuando localizador es nulo
	 * @throws IllegalArgumentException cuando usuario es nulo
	 * @throws IllegalArgumentException cuando recorrido es nulo
	 * @throws IllegalArgumentException cuando numBilletes es menor que 1
	 * @throws IllegalArgumentException cuando el numero de plazas del recorrido no tiene suficientes plazas libres
	 * @throws IllegalStateException cuando el recorrido no existe en el sistema
	 */
	public void comprarBilletes(String loc, Usuario usr, Recorrido rec, int numBilletes) {
		if(loc == null)
			throw new IllegalArgumentException("El localizador no puede ser nulo"); 
		if(usr == null) 
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		if(rec == null)
			throw new IllegalArgumentException("El recorrido no puede ser nulo");
		if(numBilletes < 1)
			throw new IllegalArgumentException("Tiene que comprarse al menos un billete");
		if(numBilletes > rec.getPlazasDisponibles())
			throw new IllegalArgumentException("No se pueden comprar mas billetes de los disponibles");
		if(this.databaseManager.getRecorrido(rec.getId()) == null)
			throw new IllegalStateException("El recorrido no se encuentra en el sistema");
		
		Billete billeteComprado;
		for(int i=0; i < numBilletes; i++) {
			billeteComprado = new Billete(loc,rec,usr);
			try{
				this.databaseManager.addBillete(billeteComprado);
			}catch(IllegalArgumentException e1) {
				throw e1;
			}
		}
	}
	
	/**
	 * Metodo para la devolucion de billetes
	 * @param loc Localizador del billete a devolver
	 * @param numBilletes Cantidad de billetes a devolver
	 * @throws IllegalArgumentException cuando localizador es nulo
	 * @throws IllegalArgumentException cuando numBilletes es menor que 1
	 * @throws IllegalStateException cuando localizador no coincide con un billete previamente comprado
	 */
	public void devolverBilletes(String loc, int numBilletes) {
		if(numBilletes < 1)
			throw new IllegalArgumentException("El numero de billetes no puede ser menor que 1");
		if(this.databaseManager.getBilletes(loc).isEmpty()) 
			throw new IllegalStateException("El localizador no coincide con el de un billete comprado");
		try {
			this.databaseManager.eliminarBilletes(loc);
		}catch(IllegalArgumentException e1) {
			throw e1;
		}

	}
	
	/**
	* Metodo que devuelve el precio total de los billetes de un usuario
	 * (Recordar que el precio de un billete de tren, tiene un 10% de descuento con respecto al precio del recorrido)
	 * @param locUsr localizador del usuario
	 * @throws IllegalArgumentException si el localizador de usuario es nulo
	 * @return precio total de los billetes en forma de float
	 */
	public float obtenerPrecioTotal(String locUsr) {
		if(locUsr == null) {
			throw new IllegalArgumentException("El localizador del usuario no puede ser nulo");
		}
		float precioTotal = 0;
		
		for(Billete item: this.databaseManager.getBilletesDeUsuario(locUsr)) {
			if(item.getUsuario().getNif() == locUsr) {
				if(item.getRecorrido().getMedioTransporte() == "tren") {
					precioTotal = (float) (precioTotal + (0.9*item.getRecorrido().getPrecio()));
				}else {
					precioTotal = precioTotal + item.getRecorrido().getPrecio();
				}
				
			}
		}
		return precioTotal;
	}
	
	
	/**
	 * Metodo que devuelve un arraylist con los recorridos en una fecha dada
	 * @param fecha Fecha en la que se buscan los recorridos
	 * @return arraylist con los recorridos en una determinada fecha
	 * @throws IllegalArgumentException si la fecha es nula
	 * 
	 */
	public ArrayList<Recorrido> getRecorridosPorFecha(LocalDate fecha) {
		if(fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		return this.databaseManager.getRecorridos(fecha);
	}
}
