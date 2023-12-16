package uva.tds.practica2_grupo8;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class BilleteId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String localizador;
	private int numeroBillete;
	
	public BilleteId(String localizador, int i) {
        super();
        this.localizador = localizador;
        this.numeroBillete = i;
    }
	
	public String getLocalizador() {
		return localizador;
	}
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
