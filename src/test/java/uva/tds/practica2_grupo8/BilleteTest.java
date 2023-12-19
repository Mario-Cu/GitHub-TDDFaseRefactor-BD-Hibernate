package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
	private InfoRecorrido info;
	private BilleteId id;
	
	@BeforeEach
	void setUp() {
		this.usrNormal = new Usuario("33036946E","UsuarioNormal");
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		info = new InfoRecorrido(fecha,hora,50,50,1);
		this.recorridoNormal = new Recorrido("1","origen","destino","autobus",0,info);
		id = new BilleteId("LocNorm",1);

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
			new Billete(id,null,usrNormal);
		});
	}
	
	@Test
	void testConstructorBilleteNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class,()->{
			new Billete(id,recorridoNormal,null);
		});
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesComparacionConBilleteNulo() {
		Billete billete = new Billete(id,recorridoNormal,usrNormal);
		assertNotEquals(billete,null);
	}

	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesLocalizadorDiferente() {
		BilleteId id1 = new BilleteId("L1",1);
		BilleteId id2 = new BilleteId("L2",1);
		Billete billete = new Billete(id1,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(id2,recorridoNormal,usrNormal);
		assertNotEquals(billete,billete2);
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesRecorridoDiferente() {
		Recorrido recorridoNormal2 = new Recorrido("2","origen","destino","autobus",0,info);
		Billete billete = new Billete(id,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(id,recorridoNormal2,usrNormal);
		assertNotEquals(billete,billete2);
	}
	@Tag("Cobertura")
	@Test
	void testComparacionBilleteNoIgualesUsuariosDiferente() {
		Usuario usrNormal2 = new Usuario("71328961G","UsuarioNormal");
		Billete billete = new Billete(id,recorridoNormal,usrNormal);
		Billete billete2 = new Billete(id,recorridoNormal,usrNormal2);
		assertNotEquals(billete,billete2);
	}

	@Tag("Cobertura")
	@Test
	void testConstructorBilleteGettersSinCobertura() {
		Billete billete = new Billete(id,recorridoNormal,usrNormal);
		billete.setEstado(EstadoBillete.COMPRADO);
		assertEquals(id,billete.getId());
		assertEquals(billete.getRecorrido().getId(),recorridoNormal.getId());
		assertEquals(billete.getUsuario().getNombre(),usrNormal.getNombre());
		assertEquals(EstadoBillete.COMPRADO,billete.getEstado());
		
	}
}
