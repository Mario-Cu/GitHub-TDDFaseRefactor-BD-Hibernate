package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaPersistenciaSinAislamientoTest {

	private Recorrido recorrido1;
	private Recorrido recorrido2;
	private Recorrido recorrido1Copia;
	private Usuario usuario;
	private LocalDate fecha;
	private LocalTime hora;
	private InfoRecorrido info1;
	private InfoRecorrido info1Copia;
	private InfoRecorrido info2;
	private BilleteId billeteId;
	private SistemaPersistenciaSinAislamiento sistema;


	@BeforeEach
	void setUp() {
		IDatabaseManager databaseManager = new DataBaseManager();
		sistema = new SistemaPersistenciaSinAislamiento(databaseManager);
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		info1 = new InfoRecorrido(fecha,hora,50,50,1);
		info1Copia = new InfoRecorrido(fecha,hora,50,50,1);
		info2 = new InfoRecorrido(fecha,hora,50,50,1);
		recorrido1 = new Recorrido("1", "origen", "destino", "autobus", 5,info1);
		info1.setRecorrido(recorrido1);
		recorrido1Copia = new Recorrido("1", "origen", "destino", "autobus", 5,info1Copia);
		info1Copia.setRecorrido(recorrido1Copia);
		recorrido2 = new Recorrido("2", "origen", "destino", "autobus", 5, info2);
		info2.setRecorrido(recorrido2);
		usuario = new Usuario("33036946E", "UsuarioNormal");
		billeteId = new BilleteId("LocNorm",1);
	}

	@Test
	void testAnadirRecorridoAlSistema() {
		ArrayList<Recorrido> recorridosEsperados = new ArrayList<Recorrido>();
		ArrayList<Recorrido> recorridosDevueltos = new ArrayList<Recorrido>();
		recorridosEsperados.add(recorrido1);
		recorridosDevueltos.add(recorrido1Copia);
		sistema.anadirRecorrido(recorrido1);
		assertEquals(recorridosEsperados, sistema.getRecorridos());

	}

	@Test
	void testAnadirRecorridoAlSistemaNoValidoRecorridoNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anadirRecorrido(null);
		});
	}

	@Test
	void testAnadirRecorridoAlSistemaNoValidoDosRecorrridosConMismoIdentificador() {
		InfoRecorrido info1Copia = new InfoRecorrido(fecha,hora,50,50,1);
		Recorrido recorrido1Copia = new Recorrido("1", "origen", "destino", "autobus", 0, info1Copia);
		info1Copia.setRecorrido(recorrido1Copia);
		sistema.anadirRecorrido(recorrido1Copia);
		assertThrows(IllegalStateException.class, () -> {
			sistema.anadirRecorrido(recorrido1Copia);
		});
	}
	
	@Test
	void testEliminarRecorridoDelSistema() {
		ArrayList<Recorrido> recorridosEsperados = new ArrayList<Recorrido>();
		ArrayList<Recorrido> recorridosDevueltos = new ArrayList<Recorrido>();
		recorridosEsperados.add(recorrido1);
		recorridosDevueltos.add(recorrido1Copia);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		billetes.add(billete);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirRecorrido(recorrido2);
		sistema.eliminarRecorrido(recorrido2.getId());
		assertEquals(recorridosEsperados, sistema.getRecorridos());

	}

	@Test
	void testEliminarRecorridoDelSistemaNoValidoRecorridoConBilletesAsociados() {
		Usuario usuario = new Usuario("33036946E", "UsuarioNormal");
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete,1);
		assertThrows(IllegalStateException.class, () -> {
			sistema.eliminarRecorrido("1");
		});

	}

	@Test
	void testEliminarRecorridoDelSistemaNoValidoLocalizadorRecorridoNulo() {
		sistema.anadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.eliminarRecorrido(null);
		});
	}
	
	@Test
	void testActualizarRecorridoFecha() {
		LocalDate fechaNueva = LocalDate.of(2002, 7, 19);
		InfoRecorrido infoAntiguo = new InfoRecorrido(fecha,hora,50,50,1);
		InfoRecorrido infoNuevo = new InfoRecorrido(fechaNueva,hora,50,50,1);
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, infoAntiguo);
		Billete billete = new Billete(billeteId, recorridoAntiguo,usuario);
		infoAntiguo.setRecorrido(recorridoAntiguo);
		Recorrido recorridoActualizado = new Recorrido("1", "origen", "destino", "autobus", 5, infoNuevo);
		infoNuevo.setRecorrido(recorridoActualizado);
		sistema.anadirRecorrido(recorridoAntiguo);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete, 1);
		sistema.actualizarRecorrido(recorridoActualizado);
		List<Recorrido> recorridosList = sistema.getRecorridos();
		Recorrido recorridoBase = recorridosList.get(0);
		assertEquals(fechaNueva, recorridoBase.getInfoRecorrido().getFecha());

	}

	@Test
	void testActualizarRecorridoNoValidaRecorridoNulo() {
		InfoRecorrido infoAntiguo= new InfoRecorrido(fecha,hora,50,50,1);
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, infoAntiguo);
		infoAntiguo.setRecorrido(recorridoAntiguo);
		sistema.anadirRecorrido(recorridoAntiguo);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.actualizarRecorrido(null);
		});
	}
	
	@Test
	void testActualizarRecorridoNoValidaRecorridoNoExistente() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.actualizarRecorrido(recorrido1);
		});
	}


	@Test
	void testActualizarRecorridoHora() {
		LocalTime horaNueva = LocalTime.of(13, 00);
		InfoRecorrido info2 = new InfoRecorrido(fecha,horaNueva,50,50,1);
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, info1);
		info1.setRecorrido(recorridoAntiguo);
		Recorrido recorridoActualizado = new Recorrido("1", "origen", "destino", "autobus", 5, info2);
		info2.setRecorrido(recorridoActualizado);
		sistema.anadirRecorrido(recorridoAntiguo);
		sistema.actualizarRecorrido(recorridoActualizado);
		assertEquals(horaNueva, recorridoActualizado.getInfoRecorrido().getHora());

	}

	
	@Test
	void testComprarBilleteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		billetes.add(billete);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete, 1);
		assertEquals(billete,sistema.getBilletes(billete.getId().getLocalizador()).get(0));

	}

	
	
	@Test
	void testComprarVariosBilletesEnSistema() {
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete(billeteId, recorrido1, usuario);
		for (int i = 1; i < 4; i++) {
			billetes.add(billetePrueba);
		}
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billetePrueba, 3);
		assertEquals(billetes, sistema.getBilletes("LocNorm"));
	}
	

	@Test
	void testComprarBilleteEnSistemaNoValidoPlazasInsuficientes(){
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.anadirBilletes(billete,51);
		});
	}
	
	
	@Test
	void testComprarBilleteEnSistemaNoValidoRecorridoNoExisteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalStateException.class, () -> {
			sistema.anadirBilletes(billete, 5);

		});
	}

	@Test
	void testComprarBilleteEnSistemaNoValidoNumeroDeBilletesMenorQueUno() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anadirBilletes(billete, 0);
		});
	}
	
	@Test
	void testReservarBilleteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		billetes.add(billete);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.reservarBilletes(billete, 1);
		assertEquals(billete,sistema.getBilletes(billete.getId().getLocalizador()).get(0));
	}

	
	
	@Test
	void testReservarVariosBilletesEnSistema() {
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete(billeteId, recorrido1, usuario);
		for (int i = 1; i < 4; i++) {
			billetes.add(billetePrueba);
		}
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.reservarBilletes(billetePrueba, 3);
		assertEquals(billetes, sistema.getBilletes(billetePrueba.getId().getLocalizador()));
	}

	@Test
	void testReservarBilleteEnSistemaNoValidoPlazasInsuficientes(){
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes(billete,51);
		});
	}
	
	
	@Test
	void testReservarBilleteEnSistemaNoValidoRecorridoNoExisteEnSistema() {
		sistema.anadirRecorrido(recorrido1);
		Recorrido recorridoNoEnSistema = new Recorrido("3", "origen", "destino", "autobus", 0, info1);
		Billete billete = new Billete(billeteId, recorridoNoEnSistema, usuario);
		assertThrows(IllegalStateException.class, () -> {
			sistema.reservarBilletes(billete, 5);

		});
	}

	@Test
	void testReservarBilleteEnSistemaNoValidoNumeroDeBilletesMenorQueUno() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);

		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes(billete, 0);
		});
	}

	@Test
	void testDevolverBilleteEnSistema() {
		BilleteId id1 = new BilleteId("LocNor1",1);
		BilleteId id2 = new BilleteId("LocNor2",1);
		Billete billete1 = new Billete(id1, recorrido1,usuario);
		Billete billete2 = new Billete(id2, recorrido2,usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete2);
		ArrayList<Billete> billetesEsperados = new ArrayList<Billete>();
		billetesReturn.add(billete1);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirRecorrido(recorrido2);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete1, 1);
		sistema.anadirBilletes(billete2, 1);
		sistema.devolverBilletes("LocNor2", 1);
		assertEquals(billetesEsperados, sistema.getBilletes("LocNorm"));

	}

	@Test
	void testDevolverBilleteEnSistemaNoValidoBilleteNoComprado() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes("LocNor2", 1);
		});
	}


	@Test
	void testDevolverBilleteEnSistemaNoValidoNumBilletesMenorQueUno() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes("locNorm", 0);
		});
	}



	@Test
	void testObtenerPrecioTotal() {
		BilleteId id1 = new BilleteId("LocNor1",1);
		BilleteId id2 = new BilleteId("LocNor2",1);
		Billete billete1 = new Billete(id1, recorrido1,usuario);
		Billete billete2 = new Billete(id2, recorrido2,usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete1);
		billetesReturn.add(billete2);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirRecorrido(recorrido2);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete1, 1);
		sistema.anadirBilletes(billete2, 1);
		float precioTotal = sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(10, precioTotal);
	}
	
	
	@Test
	void testObtenerPrecioTotalDescuentoTrenAplicado() {
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,250,250,1);
		Recorrido recorridoTren = new Recorrido("3", "origen", "destino", "tren", 5, info2);
		info2.setRecorrido(recorridoTren);
		BilleteId billeteId1 = new BilleteId("LocNor1",1);
		Billete billete = new Billete(billeteId1, recorridoTren, usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete);
		sistema.anadirRecorrido(recorridoTren);
		sistema.anadirUsario(usuario);
		sistema.anadirBilletes(billete, 1);
		float precioTotal = sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(4.5, precioTotal);
	}

	
	@Test
	void testObtenerPrecioTotalNoValidoLocalizadorUsuarioNulo() {

		assertThrows(IllegalArgumentException.class, () -> {
			float precioTotal = sistema.obtenerPrecioTotal(null);
		});
	}
	
	@Test
	void testObtenerRecorridoDisponiblesPorFecha() {
		ArrayList<Recorrido> recorridosEnFecha = new ArrayList<Recorrido>();
		ArrayList<Recorrido> recorridosReturn = new ArrayList<Recorrido>();
		recorridosEnFecha.add(recorrido1);
		recorridosReturn.add(recorrido1);
		sistema.anadirRecorrido(recorrido1);
		assertEquals(recorridosEnFecha, sistema.getRecorridosPorFecha(recorrido1.getInfoRecorrido().getFecha()));
	}

	@Test
	void testObtenerRecorridoDisponiblesPorFechaNoValidoFechaNula() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getRecorridosPorFecha(null);
		});
	}
	
	@Test
	void testAnularReserva() {
		Billete billete = new Billete(billeteId, recorrido1,usuario);
		sistema.anadirRecorrido(recorrido1);
		sistema.anadirUsario(usuario);
		sistema.reservarBilletes(billete, 1);
		sistema.anularReservaBilletes("LocNorm", 1);
		assertTrue(sistema.getBilletes("LocNorm").isEmpty());
	}


	@Test
	void testAnularReservaNoValidaNumeroDeBilletesMenorQueLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anularReservaBilletes("LocNorm", 0);
		});

	}

	@Test
	void testAnularReservaNoValidaLocalizadorNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anularReservaBilletes(null, 1);
		});
	}
	@AfterEach
	void tearDown() {
		sistema.clearDataBase();
	}

}

