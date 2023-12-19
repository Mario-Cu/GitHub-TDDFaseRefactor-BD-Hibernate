package uva.tds.practica2_grupo8;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Mario Dano Ivanov
 * Clase que representa el id de un billete
 */
@Embeddable
public class BilleteId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String localizador;
	private int numeroBillete;
	
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
	
	
	@Override
	public int hashCode() {
	    HashCodeBuilder result = new HashCodeBuilder(17, 37).append(localizador).append(numeroBillete);
		return result.toHashCode();
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BilleteId other = (BilleteId) obj;
        return Objects.equals(getLocalizador(), other.getLocalizador());
    }
}
