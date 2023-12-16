package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RecorridoTest {
	
	private LocalDate fecha;
	private LocalTime hora;
	private InfoRecorrido infoStand;

	@BeforeEach
	void setUp() {
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
	    infoStand = new InfoRecorrido(fecha,hora,1,1,1);

	}
	
	@Test
	void testConstructorRecorridoAutobusLimiteInferior() {
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		Recorrido recorrido = new Recorrido("1","origen","destino","autobus",0,info1);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("autobus",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(info1,recorrido.getInfoRecorrido());

	}
	@Test
	void testConstructorRecorridoAutobusLimiteSuperior() {
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,1,50,1);
		Recorrido recorrido = new Recorrido("1","origen","destino","autobus",0,info2);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("autobus",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(info2,recorrido.getInfoRecorrido());

	}
	@Test
	void testConstructorRecorridoTrenLimiteInferior() {
		InfoRecorrido info3 = new InfoRecorrido(fecha,hora,1,1,1);
		Recorrido recorrido = new Recorrido("1","origen","destino","tren",0,info3);
		assertEquals("1",recorrido.getId());
		assertEquals("origen",recorrido.getOrigen());
		assertEquals("destino",recorrido.getDestino());
		assertEquals("tren",recorrido.getMedioTransporte());
		assertEquals(0,recorrido.getPrecio());
		assertEquals(info3,recorrido.getInfoRecorrido());

	}

	@Test
	void testConstructorRecorridoNoValidoIdentificadorMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("","origen","destino","autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoIdentificadorNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido(null,"origen","destino","autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoOrigenMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","","destino","autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoOrigenNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1",null,"destino","autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoDestinoMenorQueLimiteInferior() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","origen","","autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoDestinoNulo() {
		
		assertThrows(IllegalArgumentException.class,() ->{
			new Recorrido("1","origen",null,"autobus",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteDiferente() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","coche",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteVacio() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","",0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMedioTransporteNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino",null,0,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoPrecioMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",-1,infoStand);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoFechaNula() {
		InfoRecorrido info = new InfoRecorrido(null,hora,1,1,1);
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoHoraNula() {
		InfoRecorrido info = new InfoRecorrido(fecha,null,1,1,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Test
	void testContructorRecorridoNoValidoAutobusPlazasDisponiblesMenorQueLimiteInferior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,-1,1,1);
		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Test
	void testContructorRecorridoNoValidoAutobusPlazasDisponiblesMayorQuePlazasTotales() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,2,1,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Tag("Cobertura")
	@Test
	void testContructorRecorridoNoValidoTrenPlazasDisponiblesMenorQueLimiteInferior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,-1,1,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,info);
		});
	}
	@Tag("Cobertura")
	@Test
	void testContructorRecorridoNoValidoTrenPlazasDisponiblesMayorQuePlazasTotales() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,2,1,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoAutobusPlazasTotalesMenorQueLimiteInferior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,0,0,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoAutobusPlazasTotalesMayorQueLimiteSuperior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,51,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","autobus",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoTrenPlazasTotalesMenorQueLimiteInferior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,0,0,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoTrenPlazasTotalesMayorQueLimiteSuperior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,251,1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,info);
		});
	}
	@Test
	void testConstructorRecorridoNoValidoMinutosMenorQueLimiteInferior() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,1,-1);

		assertThrows(IllegalArgumentException.class, () ->{
			new Recorrido("1","origen","destino","tren",0,info);
		});
	}
	@Test
	void testRecorridoIgualAOtroPorMismoIdentificador() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,infoStand);
		assertEquals(recorrido1,(recorrido2));
	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualASiMismo() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		assertEquals(recorrido1,(recorrido1));
	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoComparacionConNulo() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		assertNotEquals(recorrido1,(null));
	}

	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteIdentificador() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("2","origen","destino","tren",0,infoStand);
		assertNotEquals(recorrido1,(recorrido2));
	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteOrigen() {
		Recorrido recorrido1 = new Recorrido("1","origen1","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen2","destino","tren",0,infoStand);
		assertNotEquals(recorrido1,(recorrido2));
	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteDestino() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino1","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino2","tren",0,infoStand);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteMedioTransporte() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","autobus",0,infoStand);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferentePrecio() {
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",1,infoStand);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteFecha() {
		LocalDate fecha2 = LocalDate.of(2002, 11, 14);
		InfoRecorrido info2 = new InfoRecorrido(fecha2,hora,1,1,1);
		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,info2);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteHora() {
		LocalTime hora2 = LocalTime.of(16, 30);
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora2,1,1,1);

		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,info2);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferentePlazasTotales() {
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,1,2,1);

		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,info2);
		assertNotEquals(recorrido1,(recorrido2));

	}
	@Tag("Cobertura")
	@Test
	void testRecorridoIgualNoValidoDiferenteDuracion() {
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,1,1,2);

		Recorrido recorrido1 = new Recorrido("1","origen","destino","tren",0,infoStand);
		Recorrido recorrido2 = new Recorrido("1","origen","destino","tren",0,info2);
		assertNotEquals(recorrido1,(recorrido2));

	}

	
	
	
	
	
}
