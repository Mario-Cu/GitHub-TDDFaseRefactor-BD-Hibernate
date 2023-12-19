package uva.tds.practica2_grupo8;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase que define los métodos necesarios para gestionar usuarios, recorridos y billetes a traves de una interfaz 
 * en una base de datos
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 */
public class SistemaPersistenciaSinAislamiento {

	private IDatabaseManager databaseManager;
	/**
	 * Creación del SistemaPersistencia
	 * @param databaseManager Interfaz de la BD
	 */
	public SistemaPersistenciaSinAislamiento(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	
	/**
	 * Devuelve el arraylist que guardara los recorridos
	 * @return arraylist que guardara los recorridos
	 */
	public List<Recorrido> getRecorridos(){
		return this.databaseManager.getRecorridos();
	}

	/**
	 * Devuelve el arraylist que guardara los billetes
	 * @param loc Localizador de los billetes
	 * @return arraylist que guardara los billetes
	 */
	public List<Billete> getBilletes(String loc){
		return this.databaseManager.getBilletes(loc);
	}
	
	/**
	 * Anade un recorrido al arraylist de recorridos
	 * @param recorrido Recorrido que quieres anadir al sistema
	 * @throws IllegalStateException cuando se anade un recorrido con un identificador igual a otro
	 * @throws IllegalArgumentException cuando se anade un recorrido nulo
	 */
	public void anadirRecorrido(Recorrido recorrido) {
		try {
			this.databaseManager.addRecorrido(recorrido);
		} catch (IllegalArgumentException e1) {
			throw e1;
		} catch(IllegalStateException e2){
			throw e2;
		}
	}
	

	/**
	 * Elimina un recorrido del arraylist de recorridos	
	 * @param id Identificador del recorrido
	 * @throws IllegalStateException cuando se elimina un recorrido con billetes asociados
	 * @throws IllegalArgumentException cuando se intenta eliminar un recorrido con identificador nulo
	 */
	public void eliminarRecorrido(String id) {
		for(Billete item : this.databaseManager.getBilletesDeRecorrido(id)) {
			if((item.getRecorrido().getId()).equals(id)){
				throw new IllegalStateException("El recorrido que intentas eliminar tiene un billete asociado");
			}
		}
		
		this.databaseManager.eliminarRecorrido(id);
	

	}

	/**
	 * Actualiza el recorrido cuyo identificador coincida con el proporcionado. 
	 * @param recorrido El recorrido con los datos a actualizar
	 * @throws IllegalArgumentException si recorrido es nulo
	 * @throws IllegalStateException si no existe un recorrido con el identificador indicado en recorrido
	 */
	public void actualizarRecorrido(Recorrido recorrido) {	
		this.databaseManager.actualizarRecorrido(recorrido);
	}
	
	
	/**
	 * Metodo para la compra de billetes en un sistema
	 * @param billete Billete a anadir
	 * @param numBilletes cantidad de billetes reservados a anular
	 * @throws IllegalArgumentException cuando localizador es nulo
	 * @throws IllegalArgumentException cuando usuario es nulo
	 * @throws IllegalArgumentException cuando recorrido es nulo
	 * @throws IllegalArgumentException cuando numBilletes es menor que 1
	 * @throws IllegalArgumentException cuando el numero de plazas del recorrido no tiene suficientes plazas libres
	 * @throws IllegalStateException cuando el recorrido no existe en el sistema
	 */
	public void anadirBilletes(Billete billete, int numBilletes) {
		
		if(numBilletes < 1)
			throw new IllegalArgumentException("Tiene que comprarse al menos un billete");
		if(numBilletes > billete.getRecorrido().getInfoRecorrido().getPlazasDisponibles())
			throw new IllegalArgumentException("No se pueden comprar mas billetes de los disponibles");
		if(this.databaseManager.getRecorrido(billete.getRecorrido().getId()) == null)
			throw new IllegalStateException("El recorrido no se encuentra en el sistema");
		
		
		String localizador = billete.getId().getLocalizador();
		BilleteId billeteId;
		Billete billetePersistencia;
		for(int i=0; i < numBilletes; i++) {
			billeteId = new BilleteId(localizador,i);
			billetePersistencia = new Billete(billeteId, billete.getRecorrido(),billete.getUsuario());
			this.databaseManager.addBillete(billetePersistencia);
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
		
		this.databaseManager.eliminarBilletes(loc);

	}
	
	
	
	/**
	 * Reserva un billete 
	 * @param  billete Billete a reservar
	 * @param numBilletes cantidad de billetes reservados a anular
	 * @throws IllegalStateException si se intentan reservar mas plazas que las disponibles
	 * @throws IllegalStateException si se intenta reservar cuando el numero de plazas disponibles es menor que la mitad del numero de plazas totales
	 * @throws IllegalArgumentException si el localizador es nulo
	 * @throws IllegalArgumentException si el usuario es nulo
	 * @throws IllegalArgumentException si el recorrido es nulo
	 * @throws IllegalArgumentException si el numero de billetes es menor que el limite inferior
	 */
	public void reservarBilletes(Billete billete,int numBilletes) {
		
		if(numBilletes < 1)
			throw new IllegalArgumentException("Tiene que comprarse al menos un billete");
		if(numBilletes > billete.getRecorrido().getInfoRecorrido().getPlazasDisponibles())
			throw new IllegalArgumentException("No se pueden comprar mas billetes de los disponibles");
		if(this.databaseManager.getRecorrido(billete.getRecorrido().getId()) != null) {
		
		String localizador = billete.getId().getLocalizador();
		BilleteId billeteId;
		Billete billetePersistencia;
		for(int i=0; i < numBilletes; i++) {
			billeteId = new BilleteId(localizador,i);
			billetePersistencia = new Billete(billeteId, billete.getRecorrido(),billete.getUsuario());
			billetePersistencia.setEstado(EstadoBillete.RESERVADO);
			this.databaseManager.addBillete(billetePersistencia);
			}
		}else {
			throw new IllegalStateException("El recorrido no se encuentra en el sistema");
		}
	}	
	
	/**
	 * Anula la reserva de billetes reservados
	 * @param localizador localizador de los billetes reservados
	 * @param numBilletes cantidad de billetes reservados a anular
	 * @throws IllegalStateException si los billetes no han sido previamente reservados
	 * @throws IllegalArgumentException si el localizador es nulo
	 */
	public void anularReservaBilletes(String localizador, int numBilletes) {
		if(localizador == null)
			throw new IllegalArgumentException("El localizador no puede ser nulo");
		if(numBilletes < 1)
			throw new IllegalArgumentException("El numero de billetes no puede ser menor que 1");

		this.databaseManager.eliminarBilletes(localizador);
			
		

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

			if((item.getRecorrido().getMedioTransporte()).equals("tren")) {
				precioTotal = (float) (precioTotal + (0.9*item.getRecorrido().getPrecio()));
			}else {
				precioTotal = precioTotal + item.getRecorrido().getPrecio();
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
	public List<Recorrido> getRecorridosPorFecha(LocalDate fecha) {
		if(fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		return this.databaseManager.getRecorridos(fecha);
	}
	
	/**
	 * Metodo para añadir un usuario al sistema
	 * @param usuario Usuario que queremos añadir al sistema
	 */
	public void anadirUsario(Usuario usuario) {
		this.databaseManager.addUsuario(usuario);
	}
	
	/**
	 * Metodo que limpia la base de datos.
	 */
	public void clearDataBase() {
	 ((DataBaseManager) databaseManager).clearDatabase();
	}
}
