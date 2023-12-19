package uva.tds.practica2_grupo8;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Clase que representa un sistema.
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 * 
 */

public class Sistema {
	ArrayList<Recorrido> recorridos;
	ArrayList<Billete> billetes;
	ArrayList<Billete> billetesReservados;
	private static final  String EX_LOC = "El localizador no puede ser nulo";
	private static final  String EX_REC = "El recorrido no puede ser nulo";
	private static final  String EX_ID = "El identificador no puede ser nulo";
	private static final  double TRAIN_DISCOUNT_CONSTANT = 0.9;
	/**
	 * Constructor de la clase sistema
	 */
	public Sistema() {
		this.recorridos = new ArrayList<>();
		this.billetes  = new ArrayList<>();
		this.billetesReservados = new ArrayList<>();
		
	}
	/**
	 * Devuelve el arraylist que guardara los recorridos
	 * @return arraylist que guardara los recorridos
	 */
	public List<Recorrido> getRecorridos(){
		return recorridos;
	}
	
	/**
	 * Devuelve el arraylist que guardara los billetes normales
	 * @return arraylist que guardara los billetes
	 */
	public List<Billete> getBilletes(){
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
		if(loc == null)
			throw new IllegalArgumentException(EX_LOC); 
		if(usr == null) 
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		if(rec == null)
			throw new IllegalArgumentException(EX_REC);
		if(numBilletes < 1)
			throw new IllegalArgumentException("Tiene que comprarse al menos un billete");
		if(numBilletes > rec.getInfoRecorrido().getPlazasDisponibles())
			throw new IllegalArgumentException("No se pueden comprar mas billetes de los disponibles");
		if(getRecorridoPorId(rec.getId()) == null)
			throw new IllegalStateException("El recorrido no se encuentra en el sistema");
		
		Billete billeteComprado;
		BilleteId billeteId;
		for(int i=0; i < numBilletes; i++) {
			billeteId = new BilleteId(loc,i);
			billeteComprado = new Billete(billeteId,rec,usr);
			billetes.add(billeteComprado);
		}
		int indexRecorridoEnArrayList = getIndexRecorridoPorId(rec.getId());
		recorridos.get(indexRecorridoEnArrayList).getInfoRecorrido().setPlazasDisponibles(rec.getInfoRecorrido().getPlazasDisponibles()-numBilletes);
	}
	
	
	/**
	 * Anade un recorrido al arraylist de recorridos
	 * @param recorrido Recorrido que quieres anadir al sistema
	 * @throws IllegalStateException cuando se anade un recorrido con un identificador igual a otro
	 * @throws IllegalArgumentException cuando se anade un recorrido nulo
	 */
	public void anadirRecorrido(Recorrido recorrido) {
		if(recorrido == null) {
			throw new IllegalArgumentException(EX_REC);
		}
		for(Recorrido item : recorridos) {
			if((item.getId()).equals(recorrido.getId())){
				throw new IllegalStateException("El recorrido que intentas anadir ya existe en el sistema");
			}
		}
		
		recorridos.add(recorrido);

	}
	
	
	/**
	 * Elimina un recorrido del arraylist de recorridos	
	 * @param id Identificador del recorridow
	 * @throws IllegalStateException cuando se elimina un recorrido con billetes asociados
	 * @throws IllegalArgumentException cuando se intenta eliminar un recorrido con identificador nulo
	 */
	public void eliminarRecorrido(String id) {
		if(id == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		for(Billete item : billetes) {
			if((item.getRecorrido().getId()).equals(id)){
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
			if((item.getId()).equals(id)) {
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
		Recorrido solucion;
		for(Recorrido item : recorridos) {
			if((item.getId()).equals(id)) {
				solucion = item;
				return solucion;
			}
		}
		return null;
	}
	/**
	 * Metodo privado que obtiene el billete a traves de su localizador
	 */
	private Billete getBilletePorLocalizador(String loc) {
		Billete solucion = null;
		for(Billete item : billetes) {
			if(item.getId().getLocalizador().equals(loc)) {
				solucion = item;
				
			}
		}
		return solucion;
	}
	/**
	 * Metodo privado que obtiene el billete reservado a traves de su localizador
	 */
	private Billete getBilleteReservadoPorLocalizador(String loc) {
		Billete solucion = null;
		for(Billete item : billetesReservados) {
			if(item.getId().getLocalizador().equals(loc)) {
				solucion = item;
				
			}
		}
		return solucion;
	}

	
	/**
	 * Actualiza la fecha de un recorrido
	 * @param id identificador del recorrido
	 * @param fecha fecha nueva del reocorrido
	 * @throws IllegalArgumentException cuando el identificador es nulo
	 * @throws IllegalStateException cuando fecha es nula
	 * @throws IllegalStateException si el recorrido indicado por el id no se encuentra en el sistema
	 */
	public void actualizarFechaRecorrido(String id,LocalDate fecha) {
		if(id == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		if(fecha == null) {
			throw new IllegalStateException("La fecha no puede ser nulo");
		}
		Recorrido recorrido = getRecorridoPorId(id);
		if (recorrido != null)
			recorrido.getInfoRecorrido().setFecha(fecha);
		else {
			throw new IllegalArgumentException("El recorrido indicado por el id no se encuentra en el sistema");
		}

	}
	
	
	/**
	 * Actualiza la hora de un recorrido 
	 * @param id identificador del recorrido
	 * @param hora hora nueva del recorrido
	 * @throws IllegalArgumentException cuando identificador es nulo
	 * @throws IllegalStateException cuando hora es nula
	 * @throws IllegalStateException si el recorrido indicado por el id no se encuentra en el sistema
	 */
	public void actualizarHoraRecorrido(String id,LocalTime hora) {
		if(id == null) {
			throw new IllegalArgumentException(EX_ID);
		}
		if(hora == null) {
			throw new IllegalStateException("La hora no puede ser nulo");
		}
		Recorrido recorrido = getRecorridoPorId(id);
		if (recorrido != null)
			recorrido.getInfoRecorrido().setHora(hora);
		else {
			throw new IllegalArgumentException("El recorrido indicado por el id no se encuentra en el sistema");
		}
	}

	/**
	 * Metodo que permite comprar billetesReservados
	 * @param loc Localizador de los billetes
	 * @throws IllegalArgumentException cuando localizador es nulo
	 */
	public void comprarBilletesReservados(String loc) {
		
		List<Billete> billetesComprados = new ArrayList<>();
		if(loc == null)
			throw new IllegalArgumentException(EX_LOC);
		for(Billete item : billetesReservados) {
			if((item.getId().getLocalizador()).equals(loc)){
				billetes.add(item);
				billetesComprados.add(item);
			}
		}
		billetesReservados.removeAll(billetesComprados);

	}
	
	/**
	 * Metodo que devuelve una lista de los billetes Reservados
	 * @return arraylist que guarda los billetes reservados
	 */
	public List<Billete> getBilletesReservados() {

		return billetesReservados;
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
		if(loc == null)
			throw new IllegalArgumentException(EX_LOC);
		if(numBilletes < 1)
			throw new IllegalArgumentException("El numero de billetes no puede ser menor que 1");
		Billete billete = getBilletePorLocalizador(loc);
		if(billete == null) 
			throw new IllegalStateException("El localizador no coincide con el de un billete comprado");
		Recorrido recorrido = billete.getRecorrido();
		int indexRecorridoEnArrayList = getIndexRecorridoPorId(recorrido.getId());
		recorridos.get(indexRecorridoEnArrayList).getInfoRecorrido().setPlazasDisponibles(recorrido.getInfoRecorrido().getPlazasDisponibles()+numBilletes);
		
		List<Integer> indicesBilletesDevueltos = new ArrayList<>();
		int j =0;
		for(int i =0;i<billetes.size();i++) {
			if(j < numBilletes && (billetes.get(i).getId().getLocalizador()).equals(loc)) {
				indicesBilletesDevueltos.add(i);
				j++;
			}
		}
		Collections.sort(indicesBilletesDevueltos, Collections.reverseOrder());
		for(int i : indicesBilletesDevueltos) {
			billetes.remove(i);
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
		for(Billete item: billetes) {
			if((item.getUsuario().getNif()).equals(locUsr)) {
				if((item.getRecorrido().getMedioTransporte()).equals("tren")) {
					precioTotal = (float) (precioTotal + (TRAIN_DISCOUNT_CONSTANT*item.getRecorrido().getPrecio()));
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
	public List<Recorrido> getRecorridosPorFecha(LocalDate fecha) {
		if(fecha == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}
		List<Recorrido> recorridosEnFecha = new ArrayList<>();
		for(Recorrido item: recorridos) {
			if(item.getInfoRecorrido().getFecha() == fecha) {
				recorridosEnFecha.add(item);
			}
		}
		return recorridosEnFecha;
	}

	/**
	 * Anade un billete al arraylist de billetes
	 * @param billete billete a anadir al arraylists
	 * @throws IllegalArgumentException si el billete es nulo
	 */
	public void anadirBillete(Billete billete) {	
		if(billete == null) {
			throw new IllegalArgumentException();
		}
		
		billetes.add(billete);
	}


	/**
	 * Reserva un billete 
	 * @param localizador localizador del billete reservado 
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
	public void reservarBilletes(String localizador,Usuario usuario,Recorrido recorrido,int numBilletes) {
		if(localizador == null)
			throw new IllegalArgumentException(EX_LOC);
		if(usuario == null)
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		if(recorrido == null)
			throw new IllegalArgumentException(EX_REC);
		if(numBilletes < 1)
			throw new IllegalArgumentException("El numero de billetes tiene que ser al menos 1");
		if(numBilletes > recorrido.getInfoRecorrido().getPlazasDisponibles())
			throw new IllegalStateException("El numero de billetes supera las plazas disponiles");
		if(recorrido.getInfoRecorrido().getPlazasDisponibles() < recorrido.getInfoRecorrido().getPlazasTotales()/2)
			throw new IllegalStateException("En numero de plazas disponibles es menor que la mitad de plazas totales");
		if(getRecorridoPorId(recorrido.getId()) == null)
			throw new IllegalStateException("El recorrido no se encuentra en el sistema");
		
		Billete billeteReservado;
		BilleteId billeteId;
		for(int i=0; i < numBilletes; i++) {
			billeteId = new BilleteId(localizador,i);
			billeteReservado = new Billete(billeteId,recorrido,usuario);
			billetesReservados.add(billeteReservado);
		}
		int indexRecorridoEnArrayList = getIndexRecorridoPorId(recorrido.getId());
		recorridos.get(indexRecorridoEnArrayList).getInfoRecorrido().setPlazasDisponibles(recorrido.getInfoRecorrido().getPlazasDisponibles()-numBilletes);
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
			throw new IllegalArgumentException(EX_LOC);
		if(numBilletes < 1)
			throw new IllegalArgumentException("El numero de billetes no puede ser menor que 1");
		Billete billete = getBilleteReservadoPorLocalizador(localizador);
		if(billete == null) 
			throw new IllegalStateException("El localizador no coincide con el de un billete reservado");
		Recorrido recorrido = billete.getRecorrido();
		int indexRecorridoEnArrayList = getIndexRecorridoPorId(recorrido.getId());
		recorridos.get(indexRecorridoEnArrayList).getInfoRecorrido().setPlazasDisponibles(recorrido.getInfoRecorrido().getPlazasDisponibles()+numBilletes);
		
		List<Integer> indicesBilletesAnulados = new ArrayList<>();
		int j =0;
		for(int i =0;i<billetesReservados.size();i++) {
			if(j < numBilletes && (billetesReservados.get(i).getId().getLocalizador()).equals(localizador)) {
				indicesBilletesAnulados.add(i);
				j++;
			}
		}
		Collections.sort(indicesBilletesAnulados, Collections.reverseOrder());
		for(int i : indicesBilletesAnulados) {
			billetesReservados.remove(i);
		}

	}
}
