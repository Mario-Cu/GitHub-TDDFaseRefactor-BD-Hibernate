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

}
