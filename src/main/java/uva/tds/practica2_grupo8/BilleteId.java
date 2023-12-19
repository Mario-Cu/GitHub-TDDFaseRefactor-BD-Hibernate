package uva.tds.practica2_grupo8;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * @author Mario Dano Ivanov
 * Clase que representa el id de un billete
 */
@Embeddable
public class BilleteId implements Serializable {
	/**
	 * Localizador del billete
	 */
	private String localizador;
	/**
	 * Numero del billete (SERIAL)
	 */
	private int numeroBillete;
	private static final long serialVersionUID = 1L;

	
	/**
	 * Metodo constructor de billete vacio
	 */
	public BilleteId() {
		
	}
	
	/**
	 * Metodo constructor de billete
	 * @param localizador LOcalizador del billete
	 * @param i numero asociado al billete
	 */
	public BilleteId(String localizador, int i) {
        super();
        this.localizador = localizador;
        this.numeroBillete = i;
    }
	
	/**
	 * Metodo que devuelve el localizador
	 * @return localizador del billete
	 */
	public String getLocalizador() {
		return localizador;
	}
	
	/**
	 * Metodo que devuelve el numero de billete
	 * @return numeroBillete del billete
	 */
	public int getNumeroBillete() {
		return numeroBillete;
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass()) {
        	return false;
        }
        BilleteId other = (BilleteId) obj;
        String otherloc = other.getLocalizador();
        return Objects.equals(this.localizador, otherloc);
    }
}
