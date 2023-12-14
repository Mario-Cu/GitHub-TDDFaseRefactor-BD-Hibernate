package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BilleteTest {
	private Usuario usrNormal;
	private Recorrido recorridoNormal;
	private LocalDate fecha;
	private LocalTime hora;

	
	@BeforeEach
	void setUp() {
		this.usrNormal = new Usuario("33036946E","UsuarioNormal");
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		this.recorridoNormal = new Recorrido("1","origen","destino","autobus",0,fecha,hora,50,50,1);

	}
	
	@Test
	void testConstructorBilleteLocalizadorLimiteInferior() {
		Billete billete = new Billete("L",recorridoNormal,usrNormal);
		assertEquals("L",billete.getLocalizador());
		assertEquals("1",recorridoNormal.getId());
		assertEquals("UsuarioNormal",usrNormal.getNombre());
	}
	
	@Test
	void testConstructorBilleteLocalizadorLimiteSuperior() {
		Billete billete = new Billete("Loc8cara",recorridoNormal,usrNormal);
		assertEquals("Loc8cara",billete.getLocalizador());
		assertEquals("1",recorridoNormal.getId());
		assertEquals("UsuarioNormal",usrNormal.getNombre());
	}
	
	

	@Test
	void testConstructorBilleteNoValidoLocalizadorMenorLimiteInferior() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete("",recorridoNormal,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoLocalizadorMayorQueLimiteSuperior() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete("Loc8carac",recorridoNormal,usrNormal);
		});
	}
	 
	@Test
	void testConstructorBilleteNoValidoLocalizadorNulo() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete(null,recorridoNormal,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoRecorridoNulo() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete("LocNorm",null,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete("LocNorm",recorridoNormal,null);
		});
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesComparacionConBilleteNulo() {
		Billete billete = new Billete("L",recorridoNormal,usrNormal);
		assertFalse(billete.equals(null));
	}

	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesLocalizadorDiferente() {
		Billete billete = new Billete("L1",recorridoNormal,usrNormal);
		Billete billete2 = new Billete("L2",recorridoNormal,usrNormal);
		assertFalse(billete.equals(billete2));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesRecorridoDiferente() {
		Recorrido recorridoNormal2 = new Recorrido("2","origen","destino","autobus",0,fecha,hora,50,50,1);
		Billete billete = new Billete("L",recorridoNormal,usrNormal);
		Billete billete2 = new Billete("L",recorridoNormal2,usrNormal);
		assertFalse(billete.equals(billete2));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesUsuariosDiferente() {
		Usuario usrNormal2 = new Usuario("71328961G","UsuarioNormal");
		Billete billete = new Billete("L",recorridoNormal,usrNormal);
		Billete billete2 = new Billete("L",recorridoNormal,usrNormal2);
		assertFalse(billete.equals(billete2));
	}

	@Tag("Cobertura")
	@Test
	void testConstructorBilleteGettersSinCobertura() {
		Billete billete = new Billete("Loc8cara",recorridoNormal,usrNormal);
		assertEquals("Loc8cara",billete.getLocalizador());
		assertEquals(billete.getRecorrido().getId(),recorridoNormal.getId());
		assertEquals(billete.getUsuario().getNombre(),usrNormal.getNombre());
	}
}
