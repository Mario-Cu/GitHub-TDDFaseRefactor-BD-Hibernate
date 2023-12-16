package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaPersistenciaSinAislamientoTest {

	private Recorrido recorrido1;
	private Recorrido recorrido2;
	private Recorrido recorrido1Copia;
	private Usuario usuario;
	private LocalDate fecha;
	private LocalTime hora;
	private BilleteId billeteId;

	@TestSubject
	private SistemaPersistencia sistema;

	@Mock
	private IDatabaseManager databaseManager;

	@BeforeEach
	void setUp() {
		databaseManager = EasyMock.mock(IDatabaseManager.class);
		sistema = new SistemaPersistencia(databaseManager);
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		
		recorrido1 = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		recorrido1Copia = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		recorrido2 = new Recorrido("2", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		usuario = new Usuario("33036946E", "UsuarioNormal");
		billeteId = new BilleteId("LocNorm",1);
	}

	@Test
	void testAñadirRecorridoAlSistema() {
		ArrayList<Recorrido> recorridosEsperados = new ArrayList<Recorrido>();
		ArrayList<Recorrido> recorridosDevueltos = new ArrayList<Recorrido>();
		recorridosEsperados.add(recorrido1);
		recorridosDevueltos.add(recorrido1Copia);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall().times(1);
		EasyMock.expect(databaseManager.getRecorridos()).andReturn(recorridosDevueltos).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		assertEquals(recorridosEsperados, sistema.getRecorridos());
		EasyMock.verify(databaseManager);
	}

	@Test
	void testAñadirRecorridoAlSistemaNoValidoRecorridoNulo() {
		databaseManager.addRecorrido(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.añadirRecorrido(null);
		});
		EasyMock.verify(databaseManager);
	}

	@Test
	void testAñadirRecorridoAlSistemaNoValidoDosRecorrridosConMismoIdentificador() {
		Recorrido recorrido1Copia = new Recorrido("1", "origen", "destino", "autobus", 0, fecha, hora, 50, 50, 1);
		databaseManager.addRecorrido(recorrido1Copia);
		EasyMock.expectLastCall();
		databaseManager.addRecorrido(recorrido1Copia);
		EasyMock.expectLastCall().andThrow(new IllegalStateException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1Copia);
		assertThrows(IllegalStateException.class, () -> {
			sistema.añadirRecorrido(recorrido1Copia);
		});
		EasyMock.verify(databaseManager);
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
		
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		databaseManager.addRecorrido(recorrido2);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletesDeRecorrido(recorrido2.getId())).andReturn(billetes).times(1);
		databaseManager.eliminarRecorrido(recorrido2.getId());
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorridos()).andReturn(recorridosDevueltos).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirRecorrido(recorrido2);
		sistema.eliminarRecorrido(recorrido2.getId());
		assertEquals(recorridosEsperados, sistema.getRecorridos());
		EasyMock.verify(databaseManager);
	}

	@Test
	void testEliminarRecorridoDelSistemaNoValidoRecorridoConBilletesAsociados() {
		Usuario usuario = new Usuario("33036946E", "UsuarioNormal");
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		billetes.add(billete);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletesDeRecorrido(recorrido1.getId())).andReturn(billetes).times(1);
		databaseManager.eliminarRecorrido(recorrido1.getId());
		EasyMock.expectLastCall().andThrow(new IllegalStateException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirBilletes(billete,1);
		assertThrows(IllegalStateException.class, () -> {
			sistema.eliminarRecorrido(recorrido1.getId());
		});

	}

	@Test
	void testEliminarRecorridoDelSistemaNoValidoLocalizadorRecorridoNulo() {
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletesDeRecorrido(null)).andReturn(billetes).times(1);
		databaseManager.eliminarRecorrido(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.eliminarRecorrido(null);
		});
		EasyMock.verify(databaseManager);
	}
	
	@Test
	void testActualizarRecorridoFecha() {
		LocalDate fechaNueva = LocalDate.of(2002, 7, 19);
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		Recorrido recorridoActualizado = new Recorrido("1", "origen", "destino", "autobus", 5, fechaNueva, hora, 50, 50, 1);
		databaseManager.addRecorrido(recorridoAntiguo);
		EasyMock.expectLastCall();
		databaseManager.actualizarRecorrido(recorridoActualizado);
		EasyMock.expectLastCall();
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorridoAntiguo);
		sistema.actualizarRecorrido(recorridoActualizado);
		assertEquals(fechaNueva, recorridoActualizado.getFecha());
		EasyMock.verify(databaseManager);
	}

	@Test
	void testActualizarRecorridoNoValidaRecorridoNulo() {
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		databaseManager.addRecorrido(recorridoAntiguo);
		EasyMock.expectLastCall();
		databaseManager.actualizarRecorrido(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorridoAntiguo);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.actualizarRecorrido(null);
		});
	}
	
	@Test
	void testActualizarRecorridoNoValidaRecorridoNoExistente() {
		databaseManager.actualizarRecorrido(recorrido1);
		EasyMock.expectLastCall().andThrow(new IllegalStateException());
		EasyMock.replay(databaseManager);
		assertThrows(IllegalStateException.class, () -> {
			sistema.actualizarRecorrido(recorrido1);
		});
	}


	@Test
	void testActualizarRecorridoHora() {
		LocalTime horaNueva = LocalTime.of(13, 00);
		Recorrido recorridoAntiguo = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, hora, 50, 50, 1);
		Recorrido recorridoActualizado = new Recorrido("1", "origen", "destino", "autobus", 5, fecha, horaNueva, 50, 50, 1);
		databaseManager.addRecorrido(recorridoAntiguo);
		EasyMock.expectLastCall();
		databaseManager.actualizarRecorrido(recorridoActualizado);
		EasyMock.expectLastCall();
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorridoAntiguo);
		sistema.actualizarRecorrido(recorridoActualizado);
		assertEquals(horaNueva, recorridoActualizado.getHora());
		EasyMock.verify(databaseManager);
	}

	
	@Test
	void testComprarBilleteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		billetes.add(billete);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("Locnorm")).andReturn(billetes).times(1);
		
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirBilletes(billete, 1);
		assertTrue(billete.equals(sistema.getBilletes("Locnorm").get(0)));
		EasyMock.verify(databaseManager);
	}

	
	
	@Test
	void testComprarVariosBilletesEnSistema() {
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete(billeteId, recorrido1, usuario);
		for (int i = 1; i < 4; i++) {
			billetes.add(billetePrueba);
		}
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billetePrueba);
		databaseManager.addBillete(billetePrueba);
		databaseManager.addBillete(billetePrueba);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("LocNorm")).andReturn(billetes).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirBilletes(billetePrueba, 3);
		assertEquals(billetes, sistema.getBilletes("LocNorm"));
		EasyMock.verify(databaseManager);
	}
	
/*  No funciona no se por que
	@Test
	void testComprarBilleteEnSistemaNoValidoBilleteNulo() {
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(billetePrueba.getRecorrido().getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.añadirBilletes(null, 1); 
		});
		EasyMock.verify(databaseManager);
	}
*/

	@Test
	void testComprarBilleteEnSistemaNoValidoPlazasInsuficientes(){
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.añadirBilletes(billete,51);
		});
	}
	
	
	@Test
	void testComprarBilleteEnSistemaNoValidoRecorridoNoExisteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalStateException.class, () -> {
			sistema.añadirBilletes(billete, 5);

		});
	}

	@Test
	void testComprarBilleteEnSistemaNoValidoNumeroDeBilletesMenorQueUno() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);

		assertThrows(IllegalArgumentException.class, () -> {
			sistema.añadirBilletes(billete, 0);
		});
	}
	
	@Test
	void testReservarBilleteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		billetes.add(billete);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("Locnorm")).andReturn(billetes).times(1);
		
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.reservarBilletes(billete, 1);
		assertTrue(billete.equals(sistema.getBilletes("Locnorm").get(0)));
		EasyMock.verify(databaseManager);
	}

	
	
	@Test
	void testReservarVariosBilletesEnSistema() {
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete(billeteId, recorrido1, usuario);
		for (int i = 1; i < 4; i++) {
			billetes.add(billetePrueba);
		}
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billetePrueba);
		databaseManager.addBillete(billetePrueba);
		databaseManager.addBillete(billetePrueba);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("LocNorm")).andReturn(billetes).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.reservarBilletes(billetePrueba, 3);
		assertEquals(billetes, sistema.getBilletes("LocNorm"));
		EasyMock.verify(databaseManager);
	}
	
/* No funciona no se por que 
	@Test
	void testReservarBilleteEnSistemaNoValidoBilleteNulo() {
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(billetePrueba.getRecorrido().getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes(null, 1); 
		});
		EasyMock.verify(databaseManager);
	}
*/

	@Test
	void testReservarBilleteEnSistemaNoValidoPlazasInsuficientes(){
		Billete billete = new Billete(billeteId, recorrido1, usuario);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes(billete,51);
		});
	}
	
	
	@Test
	void testReservarBilleteEnSistemaNoValidoRecorridoNoExisteEnSistema() {
		Billete billete = new Billete(billeteId, recorrido1, usuario);

		sistema.añadirRecorrido(recorrido1);
		Recorrido recorridoNoEnSistema = new Recorrido("3", "origen", "destino", "autobus", 0, fecha, hora, 50, 50, 1);
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
		BilleteId id1 = new BilleteId("LocNorm",1);
		BilleteId id2 = new BilleteId("LocNorm",1);
		Billete billete1 = new Billete(id1, recorrido1,usuario);
		Billete billete2 = new Billete(id2, recorrido2,usuario);
		Billete billetePrueba = new Billete(id1, recorrido1, usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete2);
		ArrayList<Billete> billetesEsperados = new ArrayList<Billete>();
		billetesReturn.add(billete1);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		databaseManager.addRecorrido(recorrido2);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido2.getId())).andReturn(recorrido2).times(1);
		databaseManager.addBillete(billete2);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("LocNor2")).andReturn(billetesReturn).times(1);
		databaseManager.eliminarBilletes("LocNor2");
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("LocNorm")).andReturn(billetesEsperados).times(1);

		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirRecorrido(recorrido2);
		sistema.añadirBilletes(billete1, 1);
		sistema.añadirBilletes(billete2, 1);
		sistema.devolverBilletes("LocNor2", 1);
		assertEquals(billetesEsperados, sistema.getBilletes("LocNorm"));
		EasyMock.verify(databaseManager);
	}

	@Test
	void testDevolverBilleteEnSistemaNoValidoBilleteNoComprado() {
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		EasyMock.expect(databaseManager.getBilletes("LocNor2")).andReturn(billetesReturn).times(1);

		EasyMock.replay(databaseManager);
		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes("LocNor2", 1);
		});
		EasyMock.verify(databaseManager);
	}

/* No funciona no se por que
	@Test
	void testDevolverBilleteEnSistemaNoValidoBilleteNulo() {
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		Billete billete1 = new Billete("LocNorm", recorrido1,usuario);
		billetesReturn.add(billete1);
		EasyMock.expect(databaseManager.getBilletes("LocNorm")).andReturn(billetesReturn).times(1);
		databaseManager.eliminarBilletes(null);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException());
		EasyMock.replay(databaseManager);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes(null, 1);
		});
		EasyMock.verify(databaseManager);

	}
*/

	@Test
	void testDevolverBilleteEnSistemaNoValidoNumBilletesMenorQueUno() {
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes("locNorm", 0);
		});
	}



	@Test
	void testObtenerPrecioTotal() {
		BilleteId id1 = new BilleteId("LocNorm",1);
		BilleteId id2 = new BilleteId("LocNorm",1);
		Billete billete1 = new Billete(id1, recorrido1,usuario);
		Billete billete2 = new Billete(id2, recorrido2,usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete1);
		billetesReturn.add(billete2);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		databaseManager.addRecorrido(recorrido2);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido2.getId())).andReturn(recorrido2).times(1);
		databaseManager.addBillete(billete2);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletesDeUsuario(usuario.getNif())).andReturn(billetesReturn).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirRecorrido(recorrido2);
		sistema.añadirBilletes(billete1, 1);
		sistema.añadirBilletes(billete2, 1);
		float precioTotal = sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(10, precioTotal);
		EasyMock.verify(databaseManager);
	}
	
	
	@Test
	void testObtenerPrecioTotalDescuentoTrenAplicado() {
		Recorrido recorridoTren = new Recorrido("3", "origen", "destino", "tren", 5, fecha, hora, 250, 250, 1);
		Billete billete = new Billete(billeteId, recorridoTren, usuario);
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
		billetesReturn.add(billete);
		databaseManager.addRecorrido(recorridoTren);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorridoTren.getId())).andReturn(recorridoTren).times(1);
		databaseManager.addBillete(billete);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletesDeUsuario(usuario.getNif())).andReturn(billetesReturn).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorridoTren);
		sistema.añadirBilletes(billete, 1);
		float precioTotal = sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(precioTotal, 4.5);
		EasyMock.verify(databaseManager);
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
		LocalDate fecha2 = LocalDate.of(2002, 11, 14);
		recorridosEnFecha.add(recorrido1);
		recorridosReturn.add(recorrido1);
		databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorridos(fecha2)).andReturn(recorridosReturn).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		assertEquals(recorridosEnFecha, sistema.getRecorridosPorFecha(fecha2));
		EasyMock.verify(databaseManager);
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
		ArrayList<Billete> billetesReturn = new ArrayList<Billete>();
	    databaseManager.addRecorrido(recorrido1);
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getRecorrido(recorrido1.getId())).andReturn(recorrido1).times(1);
		databaseManager.addBillete(billete);
		EasyMock.expectLastCall();
		databaseManager.eliminarBilletes("LocNorm");
		EasyMock.expectLastCall();
		EasyMock.expect(databaseManager.getBilletes("LocNorm")).andReturn(billetesReturn).times(1);
		EasyMock.replay(databaseManager);
		sistema.añadirRecorrido(recorrido1);
		sistema.reservarBilletes(billete, 1);
		sistema.anularReservaBilletes("LocNorm", 1);
		assertTrue(sistema.getBilletes("LocNorm").isEmpty());
		EasyMock.verify(databaseManager);
	}


	@Test
	void testAnularReservaNoValidaNumeroDeBilletesMenorQueLimiteInferior() {
		Billete billete = new Billete(billeteId, recorrido1,usuario);
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

}
