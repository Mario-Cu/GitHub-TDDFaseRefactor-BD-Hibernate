package uva.tds.practica2_grupo8;

/**
 * Clase que representa un usuario.
 */
public class Usuario {
	
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
		
	}

	/**
	 * Metodo que devuelve el nif de Usuario
	 * @return nif del usuario
	 */
	public String getNif() {
		return null;
	}

	/**
	 * Metodo que devuelve el nombre de Usuario
	 * @return nombre del usuario
	 */
	public String getNombre() {
		return null;
	}
	

	/**
	 * Metodo que sobreescribe el metodo equals de la clase objeto para comparar usuarios
	 * @return booleano dependiendo de la igualdad de los objetos.
	 */
 	@Override
	public boolean equals(Object o) {
		return false;
	}

 
}
