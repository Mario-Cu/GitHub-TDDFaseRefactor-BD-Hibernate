package uva.tds.practica2_grupo8;

/**
 * Clase que representa un Usuario.
 * @author marcobr (Mario Cobreros del Caz)
 * @author mardano (Mario Danov Ivanov)
 * 
 */

public class Usuario {
	String nif;
	String nombre;
	/**
	 * Creacion de un usuario
	 * @author marcobr (Mario Cobreros del Caz)
	 * @param nif Identificador de una persona. Debe tener 9 caracteres
	 * @param nombre Palabra con la que se denomina a una persona. Debe tener entre 1 y 15 caracteres
	 * @throws IllegalArgumentException si la longitud de nombre es menor que 1
	 * @throws IllegalArgumentException si la longitud de nombre es mayor que 15
	 * @throws IllegalArgumentException si nombre es null
	 * @throws IllegalArgumentException si la longitud de nif es mayor o menor que 9 
	 * @throws IllegalArgumentException si nif utiliza las letras I,Ñ,O,U.
	 * @throws IllegalArgumentException si el resto de la division del numero nif entre 23 no coincide con su letra asociada 
	 * @throws IllegalArgumentException si nif utiliza las letras I,Ñ,O,U.
	 * @throws IllegalArgumentException si nif es nulo.
	 */
	public Usuario(String nif, String nombre) {
		char[] letrasProhibidas = {'I','Ñ','O','U'};
		
		if(nombre == null) {
			throw new IllegalArgumentException("El nombre no puede ser nulo");
		}
		if(nif == null) {
			throw new IllegalArgumentException("El nif no puede ser nulo");
		}
		for(char item : letrasProhibidas) {
			if(nif.indexOf(item) != -1){
				throw new IllegalArgumentException("El nif no puede contener las letras I,Ñ,O,U");
			}
		}
		if(nombre.length()<1) {
			throw new IllegalArgumentException("El nombre tiene que tener al menos 1 caracter");
		}
		if(nombre.length()>15) {
			throw new IllegalArgumentException("El nombre no puede tener mas de 15 caracteres");
		}
		
		if(nif.length()<9) {
			throw new IllegalArgumentException("El nif tiene que tener 9 caracteres");
		}
		if(nif.length()>9) {
			throw new IllegalArgumentException("El nif tiene que tener 9 caracteres");
		}
		Boolean b = letraDNI(nif);
		if(Boolean.FALSE.equals(b)) {
			throw new IllegalArgumentException("El resto de la division del numero nif entre 23 no coincide con su letra asociada");
		}


		this.nif = nif;
		this.nombre = nombre;
	}
	
	
	/**
	 * Metodo privado que comprueba si la letra asociada al dni es valida
	 * @return nif del usuario
	 */
	private Boolean letraDNI(String dni) {
		Boolean valor = false;
		int miDNI = Integer.parseInt(dni.substring(0,8));
		String letra = dni.substring(8);
		int resto = 0;
		String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
		resto = miDNI % 23;
		String letraAsociada = asignacionLetra[resto];
		if(letra.equals(letraAsociada)) {
			valor = true;
		}
		return valor;
	}

	/**
	 * Metodo que devuelve el nif de Usuario
	 * @return nif del usuario
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Metodo que devuelve el nombre de Usuario
	 * @return nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}
	

	/**
	 * Metodo que sobreescribe el metodo equals de la clase objeto para comparar usuarios
	 * @return booleano dependiendo de la igualdad de los objetos.
	 */
 	@Override
	public boolean equals(Object o) {
 		Boolean valor = false;
		Usuario u = (Usuario)o;
		if(this.nif == u.getNif()) {
			valor = true;
		}
		return valor;

	}

 
}
