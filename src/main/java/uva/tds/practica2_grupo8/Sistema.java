package uva.tds.practica2_grupo8;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Sistema {
	ArrayList<Recorrido> recorridos;
	ArrayList<Billete> billetes;
	/**
	 * @author marcobr (Mario Cobreros del Caz)
	 * @author mardano (Mario Danov Ivanov)
	 * Clase que representa un sistema.
	 */

	public Sistema() {
		this.recorridos = new ArrayList<Recorrido>();
		this.billetes  = new ArrayList<Billete>();
	}
	/**
	 * Devuelve el arraylist que guardara los recorridos
	 * @return arraylist que guardara los recorridos
	 */
	public ArrayList<Recorrido> getRecorridos(){
		return recorridos;
	}
	
	/**
	 * Devuelve el arraylist que guardara los billetes
	 * @return arraylist que guardara los billetes
	 */
	public ArrayList<Billete> getBilletes(){
		return billetes;
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
		
	}
	
	
	/**
	 * Añade un recorrido al arraylist de recorridos
	 * @param recorrido 
	 * @throws IllegalStateException cuando se añade un recorrido con un identificador igual a otro
	 * @throws IllegalArgumentException cuando se añade un recorrido nulo
	 */
	public void añadirRecorrido(Recorrido recorrido) {
		if(recorrido == null) {
			throw new IllegalArgumentException("El recorrido no puede ser nulo");
		}
		for(Recorrido item : recorridos) {
			if(item.getId() == recorrido.getId()){
				throw new IllegalStateException("El recorrido que intentas añadir ya existe en el sistema");
			}
		}
		
		recorridos.add(recorrido);
	}
	
	
	/**
	 * Elimina un recorrido del arraylist de recorridos	 
	 * @throws IllegalStateException cuando se elimina un recorrido con billetes asociados
	 * @throws IllegalArgumentException cuando se intenta eliminar un recorrido con identificador nulo
	 */
	public void eliminarRecorrido(String id) {
		if(id == null) {
			throw new IllegalArgumentException("El identificador no puede ser nulo");
		}
		for(Billete item : billetes) {
			if(item.getRecorrido().getId() == id){
				throw new IllegalStateException("El recorrido que intentas eliminar tiene un billete asociado");
			}
		}
		int indiceRecorrido = getIndexRecorridoPorId(id);
		recorridos.remove(indiceRecorrido);
	}
	
	/**
	 * Metodo privado que obtiene el indice donde se encuentra el recorrido a traves de su id
	 * 
	 */
	private int getIndexRecorridoPorId(String id) {
		int indice = -1;
		for(Recorrido item : recorridos) {
			if(item.getId() == id) {
				indice = recorridos.indexOf(item);
			}
		}
		return indice;
	}
	
	/**
	 * Metodo privado que obtiene el recorrido a traves de su id
	 * 
	 */
	private Recorrido getRecorridoPorId(String id) {
		Recorrido solucion = null;
		for(Recorrido item : recorridos) {
			if(item.getId() == id) {
				solucion = item;
			}
		}
		return solucion;
	}
	
	
	/**
	 * Actualiza la fecha de un recorrido
	 * @param id identificador del recorrido
	 * @param fecha fecha nueva del reocorrido
	 * @throws IllegalStateException cuando localizador es nulo
	 * @throws IllegalStateException cuando fecha es nula
	 */
	public void actualizarFechaRecorrido(String id,LocalDate fecha) {
		if(id == null) {
			throw new IllegalStateException("El identificador no puede ser nulo");
		}
		if(fecha == null) {
			throw new IllegalStateException("La fecha no puede ser nulo");
		}
		Recorrido recorrido = getRecorridoPorId(id);
		recorrido.setFecha(fecha);
	}
	
	
	/**
	 * Actualiza la hora de un recorrido 
	 * @param id identificador del recorrido
	 * @param hora hora nueva del recorrido
	 * @throws IllegalStateException cuando identificador es nulo
	 * @throws IllegalStateException cuando hora es nula
	 */
	public void actualizarHoraRecorrido(String id,LocalTime hora) {
		if(id == null) {
			throw new IllegalStateException("El identificador no puede ser nulo");
		}
		if(hora == null) {
			throw new IllegalStateException("La hora no puede ser nulo");
		}
		Recorrido recorrido = getRecorridoPorId(id);
		recorrido.setHora(hora);
	}

	/**
	 * Metodo que permite comprar billetesReservados
	 * @param loc Localizador de los billetes
	 * @throws IllegalArgumentException cuando localizador es nulo
	 */
	public void comprarBilletesReservados(String loc) {

	}
	
	/**
	 * Metodo que devuelve una lista de los billetes Reservados
	 * @return arraylist que guarda los billetes reservados
	 */
	public ArrayList<Billete> getBilletesReservados() {

		return null;
	}
	
	/**
	 * Metodo para la devolucion de billetes
	 * @param loc Localizador del billete a devolver
	 * @param numBilletes Cantidad de billetes a devolver
	 * @throws IllegalArgumentException cuando localizador es nulo
	 * @throws IllegalArgumentException cuando numBilletes es menor que 1
	 * @throws IllegalArgumentException cuando localizador es nulo
	 * @throws IllegalStateException cuando localizador no coincide con un billete previamente comprado
	 */
	public void devolverBilletes(String loc, int numBilletes) {
	
	}

	
	/**
	* Metodo que devuelve el precio total de los billetes de un usuario
	 * (Recordar que el precio de un billete de tren, tiene un 10% de descuento con respecto al precio del recorrido)
	 * @param locUsr localizador del usuario
	 * @throws IllegalStateException si el descuento del 10% en los billetes de tren no es aplicado
	 * @throws IllegalArgumentException si el localizador de usuario es nulo
	 * @return precio total de los billetes en forma de float
	 */
	public float obtenerPrecioTotal(String locUsr) {
		
		return 0;
	}
	
	/**
	 * Metodo que devuelve un arraylist con los recorridos en una fecha dada
	 * @param fecha Fecha en la que se buscan los recorridos
	 * @return arraylist con los recorridos en una determinada fecha
	 * @throws IllegalArgumentException si la fecha es nula
	 * 
	 */
	public ArrayList<Recorrido> getRecorridosPorFecha(LocalDate fecha) {
		
		return null;
	}

	/**
	 * Añade un billete al arraylist de billetes
	 * @param billete billete a añadir al arraylists
	 * @throws IllegalArgumentException si el billete es nulo
	 */
	public void añadirBillete(Billete billete) {	
		if(billete == null) {
			throw new IllegalArgumentException();
		}
		
		billetes.add(billete);
	}


	/**
	 * Reserva un billete 
	 * @param Localizador localizador del billete reservado 
	 * @param usuario usuario que realiza la reserva
	 * @param recorrido recorrido del que se reveran billetes 
	 * @param numBilletes cantidad de billetes reservados
	 * @throws IllegalStateException si se intentan reservar mas plazas que las disponibles
	 * @throws IllegalStateException si se intenta reservar cuando el numero de plazas disponibles es menor que la mitad del numero de plazas totales
	 * @throws IllegalArgumentException si el localizador es nulo
	 * @throws IllegalArgumentException si el usuario es nulo
	 * @throws IllegalArgumentException si el recorrido es nulo
	 * @throws IllegalArgumentException si el numero de billetes es menor que el limite inferior
	 */
	public void reservarBilletes(String Localizador,Usuario usuario,Recorrido recorrido,int numBilletes) {
		
	}


	/**
	 * Devuelve un arraylist con todos los billetes reservados que tienen el mismo localizador
	 * @param Localizador localizador de los billetes reservados
	 * @return arraylist que contiene los billetes reservados
	 */
	public ArrayList<Billete> getReservaBilletes(String Localizador) {
		return null;
	}

	
	/**
	 * Anula la reserva de billetes reservados
	 * @param Localizador localizador de los billetes reservados
	 * @param numBilletes cantidad de billetes reservados a anular
	 * @throws IllegalStateException si los billetes no han sido previamente reservados
	 * @throws IllegalArgumentException si el localizador es nulo
	 */
	public void anularReservaBilletes(String Localizador, int numBilletes) {
		
	}
}
