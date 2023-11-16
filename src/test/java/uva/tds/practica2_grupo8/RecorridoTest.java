package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecorridoTest {
	private LocalDate fecha;
	private LocalTime hora;
	@BeforeEach
	void setUp() {
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
	}
	
	@Test
	void testConstructorRecorridoAutobusLimiteInferior() {
		Recorrido recorrido = new Recorrido("1","origen","destino","autobus",0,fecha,hora,1,1,1);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("autobus",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(fecha,recorrido.getFecha());
		assertEquals(hora,recorrido.getHora());
		assertEquals(1,recorrido.getPlazasDisponibles());
		assertEquals(1,recorrido.getPlazasTotales());
		assertEquals(1,recorrido.getDuracion());
	}
	@Test
	void testConstructorRecorridoAutobusLimiteSuperior() {
		Recorrido recorrido = new Recorrido("1","origen","destino","autobus",0,fecha,hora,1,50,1);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("autobus",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(fecha,recorrido.getFecha());
		assertEquals(hora,recorrido.getHora());
		assertEquals(1,recorrido.getPlazasDisponibles());
		assertEquals(50,recorrido.getPlazasTotales());
		assertEquals(1,recorrido.getDuracion());
	}
	@Test
	void testConstructorRecorridoTrenLimiteInferior() {
		Recorrido recorrido = new Recorrido("1","origen","destino","tren",0,fecha,hora,1,1,1);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("tren",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(fecha,recorrido.getFecha());
		assertEquals(hora,recorrido.getHora());
		assertEquals(1,recorrido.getPlazasDisponibles());
		assertEquals(1,recorrido.getPlazasTotales());
		assertEquals(1,recorrido.getDuracion());
	}
	@Test
	void testConstructorRecorridoTrenLimiteSuperior() {
		Recorrido recorrido = new Recorrido("1","origen","destino","tren",0,fecha,hora,1,250,1);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("tren",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(fecha,recorrido.getFecha());
		assertEquals(hora,recorrido.getHora());
		assertEquals(1,recorrido.getPlazasDisponibles());
		assertEquals(250,recorrido.getPlazasTotales());
		assertEquals(1,recorrido.getDuracion());
	}
	@Test
	void testConstructorRecorridoNoValidoIdentificadorMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("","origen","destino","autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoIdentificadorNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido(null,"origen","destino","autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoOrigenMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","","destino","autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoOrigenNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1",null,"destino","autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoDestinoMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","origen","","autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoDestinoNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","origen",null,"autobus",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteDiferente() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","coche",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteVacio() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","",0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino",null,0,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoPrecioMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",-1,fecha,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoFechaNula() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,null,hora,1,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoHoraNula() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,fecha,null,1,1,1);
		});
	}
	@Test
	void testContructorRecorridoNoValidoPlazasDisponiblesMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,fecha,hora,-1,1,1);
		});
	}
	void testContructorRecorridoNoValidoPlazasDisponiblesMayorQuePlazasTotales() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,fecha,hora,2,1,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoAutobusPlazasTotalesMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,fecha,hora,1,0,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoAutobusPlazasTotalesMayorQueLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,fecha,hora,1,51,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoTrenPlazasTotalesMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,fecha,hora,1,0,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoTrenPlazasTotalesMayorQueLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,fecha,hora,1,251,1);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMinutosMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,fecha,hora,1,1,-1);
		});
	}
	@Test
	void testRecorridoIgualAOtroPorMismoIdentificador() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,fecha,hora,1,1,1);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,fecha,hora,1,1,1);
		assertEquals(recorrido1,recorrido2);
	}
	
	
}
