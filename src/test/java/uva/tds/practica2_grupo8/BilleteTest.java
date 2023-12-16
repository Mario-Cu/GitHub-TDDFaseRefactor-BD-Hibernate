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
	private BilleteId localizadorNormal;
	
	@BeforeEach
	void setUp() {
		this.usrNormal = new Usuario("33036946E","UsuarioNormal");
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		this.recorridoNormal = new Recorrido("1","origen","destino","autobus",0,fecha,hora,50,50,1);
		this.localizadorNormal = new BilleteId("L",1);
	}
	
	@Test
	void testConstructorBilleteLocalizadorLimiteInferior() {
		BilleteId localizador = new BilleteId("L", 1);
		Billete billete = new Billete(localizador,recorridoNormal,usrNormal);
		assertEquals("L",billete.getId().getLocalizador());
		assertEquals("1",recorridoNormal.getId());
		assertEquals("UsuarioNormal",usrNormal.getNombre());
	}
	
	@Test
	void testConstructorBilleteLocalizadorLimiteSuperior() {
		BilleteId localizador = new BilleteId("Loc8cara", 1);
		Billete billete = new Billete(localizador,recorridoNormal,usrNormal);
		assertEquals("Loc8cara",billete.getId().getLocalizador());
		assertEquals("1",recorridoNormal.getId());
		assertEquals("UsuarioNormal",usrNormal.getNombre());
	}
	
	

	@Test
	void testConstructorBilleteNoValidoLocalizadorMenorLimiteInferior() {
		BilleteId localizador = new BilleteId("", 1);
		assertThrows(IllegalArgumentException.class,()->{
			new Billete(localizador,recorridoNormal,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoLocalizadorMayorQueLimiteSuperior() {
		BilleteId localizador = new BilleteId("Loc8carac", 1);
		assertThrows(IllegalArgumentException.class,()->{
			new Billete(localizador,recorridoNormal,usrNormal);
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
			new Billete(localizadorNormal,null,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete(localizadorNormal,recorridoNormal,null);
		});
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesComparacionConBilleteNulo() {
		Billete billete = new Billete(localizadorNormal,recorridoNormal,usrNormal);
		assertFalse(billete.equals(null));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesComparacionConNoBillete() {
		Billete billete = new Billete(localizadorNormal,recorridoNormal,usrNormal);
		assertFalse(billete.equals(fecha));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesLocalizadorDiferente() {
		BilleteId localizador1 = new BilleteId("L1", 1);
		BilleteId localizador2 = new BilleteId("L2", 1);
		Billete billete = new Billete(localizador1,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(localizador2,recorridoNormal,usrNormal);
		assertFalse(billete.equals(billete2));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesRecorridoDiferente() {
		Recorrido recorridoNormal2 = new Recorrido("2","origen","destino","autobus",0,fecha,hora,50,50,1);
		Billete billete = new Billete(localizadorNormal,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(localizadorNormal,recorridoNormal2,usrNormal);
		assertFalse(billete.equals(billete2));
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesUsuariosDiferente() {
		Usuario usrNormal2 = new Usuario("71328961G","UsuarioNormal");
		Billete billete = new Billete(localizadorNormal,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(localizadorNormal,recorridoNormal,usrNormal2);
		assertFalse(billete.equals(billete2));
	}

	@Tag("Cobertura")
	@Test
	void testConstructorBilleteGettersSinCobertura() {
		Billete billete = new Billete(localizadorNormal,recorridoNormal,usrNormal);
		assertEquals(localizadorNormal,billete.getId());
		assertEquals(billete.getRecorrido().getId(),recorridoNormal.getId());
		assertEquals(billete.getUsuario().getNombre(),usrNormal.getNombre());
	}
}
