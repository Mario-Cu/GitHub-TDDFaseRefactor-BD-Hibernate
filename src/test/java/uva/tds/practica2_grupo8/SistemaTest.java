package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SistemaTest {
	private Recorrido recorrido1;
	private Recorrido recorrido2;
	private Usuario usuario;
	private LocalDate fecha;
	private LocalTime hora;


		
	@BeforeEach
	void setUp() throws Exception {
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		recorrido1 = new Recorrido("1","origen","destino","autobus",5,fecha,hora,50,50,1);
		recorrido2 = new Recorrido("2","origen","destino","autobus",5,fecha,hora,50,50,1);
		usuario = new Usuario("33036946E","UsuarioNormal");
	}
	
	
	@Test
	void testAñadirRecorridoAlSistema(){
		Sistema sistema = new Sistema();
		ArrayList<Recorrido> recorridos = new ArrayList<Recorrido>();
		recorridos.add(recorrido1);
		sistema.añadirRecorrido(recorrido1);
		assertEquals(recorridos,sistema.getRecorridos());
	}
	@Test
	void testAñadirRecorridoAlSistemaNoValidoRecorridoNulo() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.añadirRecorrido(null);
		});
	}
	
	@Test
	void testComprarBilleteEnSistema(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		billetes.add(billetePrueba);
		sistema.comprarBilletes("LocNorm",usuario,recorrido1,1);
		assertTrue(billetePrueba.equals(sistema.getBilletes().get(0)));
		assertEquals(49,recorrido1.getPlazasDisponibles());
	}
	
	@Test
	void testComprarBilletesReservados(){
		Sistema sistema = new Sistema();
		ArrayList<Billete> billetesReservados = new ArrayList<Billete>();
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		billetesReservados.add(billetePrueba);
		sistema.reservarBilletes("LocNorm", usuario, recorrido1, 1);
		sistema.comprarBilletesReservados("LocNorm");
		assertEquals(billetesReservados,sistema.getBilletes());
	}
	
	@Test
	void testComprarBilletesReservadosNoValidoLocalizadorNulo(){
		Sistema sistema = new Sistema();
		ArrayList<Billete> billetesReservados = new ArrayList<Billete>();
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		billetesReservados.add(billetePrueba);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletesReservados(null);
		});
	}
	
	@Test
	void testDevolverBilleteEnSistema(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		sistema.comprarBilletes("LocNorm", usuario, recorrido1, 1);
		sistema.devolverBilletes("LocNorm",1);
		assertEquals(billetes,sistema.getBilletes());
		assertEquals(50,recorrido1.getPlazasDisponibles());
	}
	
	@Test
	void testDevolverBilleteEnSistemaNoValidoBilleteNoComprado(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
        sistema.comprarBilletes("locNorm", usuario, recorrido1, 1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.devolverBilletes("LocNor2",1);
		});
	} 
	
	@Test
	void testDevolverBilleteEnSistemaNoValidoLocalizadorNulo(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		sistema.comprarBilletes("locNorm", usuario, recorrido1, 1);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.devolverBilletes(null,1);
		});
	}
	
	@Test
	void testDevolverBilleteEnSistemaNoValidoNumBilletesMenorQueUno(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		sistema.comprarBilletes("locNorm", usuario, recorrido1, 1);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.devolverBilletes("locNorm",0);
		});
	}
	
	@Test
	void testComprarVariosBilletesEnSistema(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billetePrueba = new Billete("LocNorm", recorrido1, usuario);
		for(int i = 1; i<4; i++) {
			billetes.add(billetePrueba);
		}
		sistema.comprarBilletes("LocNorm",usuario,recorrido1,3);
		assertTrue(billetePrueba.equals(sistema.getBilletes().get(0)));
		assertTrue(billetePrueba.equals(sistema.getBilletes().get(1)));
		assertTrue(billetePrueba.equals(sistema.getBilletes().get(2)));
	}
	
	
	@Test
	void testComprarBilleteEnSistemaNoValidoPlazasInsuficientes(){
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletes("LocNorm",usuario,recorrido1,51);
		});
	}
	
	@Test
	void testComprarBilleteEnSistemaNoValidoLocalizadorNulo(){
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletes(null,usuario,recorrido1,5);
 
		});
	}
	
	@Test
	void testComprarBilleteEnSistemaNoValidoUsuarioNulo(){
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletes("LocNorm",null,recorrido1,5);

		});
	}
	
	@Test
	void testComprarBilleteEnSistemaNoValidoRecorridoNulo(){
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletes("LocNorm",usuario,null,5);

		});
	}
	
	@Test
	void testComprarBilleteEnSistemaNoValidoRecorridoNoExisteEnSistema(){
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		Recorrido recorridoNoEnSistema = new Recorrido("3","origen","destino","autobus",0,fecha,hora,50,50,1);
		assertThrows(IllegalStateException.class, () ->{
		sistema.comprarBilletes("LocNorm",usuario,recorridoNoEnSistema,5);

		});
	}
	
	@Test
	void testComprarBilleteEnSistemaNoValidoNumeroDeBilletesMenorQueUno(){
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.comprarBilletes("LocNorm",usuario,recorrido1,0);
		});
	}
	
	@Test	
	void testAñadirRecorridoAlSistemaNoValidoDosRecorrridosConMismoIdentificador() {
		Recorrido recorrido1Copia = new Recorrido("1","origen","destino","autobus",0,fecha,hora,50,50,1);
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.añadirRecorrido(recorrido1Copia);
		});
	}
	

	@Test
	void testEliminarRecorridoDelSistema() {
		Sistema sistema = new Sistema();
		ArrayList<Recorrido> recorridos = new ArrayList<Recorrido>();
		recorridos.add(recorrido1);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirRecorrido(recorrido2);
		sistema.eliminarRecorrido(recorrido2.getId());
		assertEquals(recorridos,sistema.getRecorridos());
		
	}
	@Test
	void testEliminarRecorridoDelSistemaNoValidoRecorridoConBilletesAsociados() {
		Sistema sistema = new Sistema();
		Usuario usuario = new Usuario("33036946E","UsuarioNormal");
		Billete billete = new Billete("LocNorm",recorrido1,usuario);
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirBillete(billete);
		assertThrows(IllegalStateException.class, () ->{
			sistema.eliminarRecorrido(recorrido1.getId());
		});
	}
	@Test
	void testEliminarRecorridoDelSistemaNoValidoLocalizadorRecorridoNulo() {
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.eliminarRecorrido(null);
		});
	}
	@Test
	void testActualizarRecorridoFecha() {
		Sistema sistema = new Sistema();
		LocalDate fechaNueva = LocalDate.of(2002, 7, 19);
		sistema.añadirRecorrido(recorrido1);
		sistema.actualizarFechaRecorrido(recorrido1.getId(),fechaNueva);
		assertEquals(fechaNueva,recorrido1.getFecha());
	}
	@Test
	void testActualizarRecorridoFechaNoValidaFechaNula() {
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.actualizarFechaRecorrido(recorrido1.getId(),null);
		});
	}
	@Test
	void testActualizarRecorridoFechaNoValidaRecorridoNulo() {
		Sistema sistema = new Sistema();
		LocalDate fechaNueva = LocalDate.of(2002, 7, 19);
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.actualizarFechaRecorrido(null,fechaNueva);
		});
	}
	@Test
	void testActualizarRecorridoHora() {
		Sistema sistema = new Sistema();
		LocalTime horaNueva = hora = LocalTime.of(13, 00);
		sistema.añadirRecorrido(recorrido1);
		sistema.actualizarHoraRecorrido(recorrido1.getId(),horaNueva);
		assertEquals(horaNueva,recorrido1.getHora());
	}
	void testActualizarRecorridoHoraNoValidaHoraNula() {
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.actualizarHoraRecorrido(recorrido1.getId(),null);
		});
	}
	@Test
	void testActualizarRecorridoHoraNoValidaRecorridoNulo() {
		Sistema sistema = new Sistema();
		LocalTime horaNueva = hora = LocalTime.of(13, 00);
		sistema.añadirRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.actualizarHoraRecorrido(null,horaNueva);
		});
	}
	@Test
	void testReservarBilletes() {
		Sistema sistema = new Sistema();
		sistema.reservarBilletes("LocNorm",usuario,recorrido1,1);
		ArrayList<Billete> reservaBillete = new ArrayList<Billete>();
		Billete billeteReservado = new Billete("LocNorm",recorrido1,usuario);
		reservaBillete.add(billeteReservado);  
		assertEquals(49,recorrido1.getPlazasDisponibles());
		assertEquals(billeteReservado,sistema.getReservaBilletes("LocNorm"));
	}
	
	@Test
	void testObtenerPrecioTotal() {
		Sistema sistema = new Sistema();
		sistema.añadirRecorrido(recorrido1);
		sistema.añadirRecorrido(recorrido2);
		sistema.comprarBilletes("LocNor1", usuario, recorrido1, 1);
		sistema.comprarBilletes("LocNor2", usuario, recorrido2, 1);
		float precioTotal = sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(10,precioTotal);
	}
	
	@Test	
	void testObtenerPrecioTotalNoValidoLocalizadorUsuarioNulo() {
			Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			float precioTotal = sistema.obtenerPrecioTotal(null);
		});
	}
	
	@Test	
	void testObtenerPrecioTotalNoValidoDescuentoTrenNoAplicado() {
		Sistema sistema = new Sistema();
		Recorrido recorridoTren = new Recorrido("3","origen","destino","tren",5,fecha,hora,250,250,1);
		sistema.añadirRecorrido(recorridoTren);
		Billete billete = new Billete("LocNor1",recorridoTren,usuario);
		sistema.comprarBilletes("LocNor1", usuario, recorridoTren, 1);
		float precioTotal =sistema.obtenerPrecioTotal(usuario.getNif());
		assertThrows(IllegalStateException.class, () ->{
			assertEquals(5,precioTotal);
			
		});
	}

	@Test	
	void testObtenerPrecioTotaDescuentoTrenAplicado() {
		Sistema sistema = new Sistema();
		Recorrido recorridoTren = new Recorrido("3","origen","destino","tren",5,fecha,hora,250,250,1);
		sistema.añadirRecorrido(recorridoTren);
		Billete billete = new Billete("LocNor1",recorridoTren,usuario);
		sistema.comprarBilletes("LocNor1", usuario, recorridoTren, 1);
		float precioTotal =sistema.obtenerPrecioTotal(usuario.getNif());
		assertEquals(precioTotal,4.5);
	}
	
	@Test	
	void testObtenerRecorridoDisponiblesPorFecha() {
		Sistema sistema = new Sistema();
		ArrayList<Recorrido> recorridosEnFecha = new ArrayList<Recorrido>();
		sistema.añadirRecorrido(recorrido1);
		recorridosEnFecha.add(recorrido1);
		assertEquals(recorridosEnFecha,sistema.getRecorridosPorFecha(fecha));
	}
	
	@Test	
	void testObtenerRecorridoDisponiblesPorFechaNoValidoFechaNula() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.getRecorridosPorFecha(null);
		});
	}

	
	@Test
	void testReservarVariosBilletes() {
		Sistema sistema = new Sistema();
		ArrayList<Billete> reservaBilleteMultiple = new ArrayList<Billete>();
		Billete reservaBillete = new Billete("LocNorm", recorrido1, usuario);
		for(int i = 1; i<4; i++) {
			reservaBilleteMultiple.add(reservaBillete);
		}
		sistema.reservarBilletes("LocNorm",usuario,recorrido1,3);
		assertEquals(reservaBilleteMultiple,sistema.getReservaBilletes("LocNorm"));
	}
	@Test
	void testReservaBilletesNoValidaNumeroPlazasDisponiblesMenorQueMitadNumeroTotalPlazasAutobus() {
		Sistema sistema = new Sistema();
		Recorrido recorrido = new Recorrido("3","origen","destino","autobus",5,fecha,hora,24,50,1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.reservarBilletes("LocNorm",usuario,recorrido,1);
		});
	}
	@Test
	void testReservaBilletesNoValidaNumeroPlazasDisponiblesMenorQueMitadNumeroTotalPlazasTren() {
		Sistema sistema = new Sistema();
		Recorrido recorrido = new Recorrido("3","origen","destino","tren",5,fecha,hora,124,250,1);
		assertThrows(IllegalStateException.class, () ->{
			sistema.reservarBilletes("LocNorm",usuario,recorrido,1);
		});
	}
	@Test
	void testReservaBilletesNoValidaPlazasInsuficientes() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalStateException.class, () ->{
			sistema.reservarBilletes("LocNorm",usuario,recorrido1,51);
		});
	}
	@Test
	void testReservaBilletesNoValidaRecorridoNoExistente() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes("LocNorm",usuario,null,1);
		});
	}
	@Test
	void testReservaBilletesNoValidaLocalizadorNulo() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes(null,usuario,recorrido1,1);
		});
	}
	@Test
	void testReservaBilletesNoValidaUsuarioNulo() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes("LocNorm",null,recorrido1,1);
		});
	}
	@Test
	void testReservaBilletesNoValidaNumBilletesMenorQueLimiteInferior() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.reservarBilletes("LocNorm",usuario,recorrido1,0);
		});
	}
	@Test
	void testAñadirBillete() {
		Sistema sistema = new Sistema();
		ArrayList<Billete> billetes = new ArrayList<Billete>();
		Billete billete = new Billete("LocNorm", recorrido1, usuario);
		billetes.add(billete);
		sistema.añadirBillete(billete);
		assertEquals(billetes,sistema.getBilletes());
	}
	@Test
	void testAñadirBilleteNoValidoBilleteNulo() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.añadirBillete(null);
		});
	}
	@Test
	void testAnularReserva() {
		Sistema sistema = new Sistema();
		ArrayList<Billete> reservaBilletes = new ArrayList<Billete>();
		Billete billeteReservado = new Billete("LocNorm", recorrido1, usuario);
		reservaBilletes.add(billeteReservado);
		sistema.reservarBilletes("LocNorm",usuario,recorrido1,2);
		sistema.anularReservaBilletes("LocNorm",1);
		assertEquals(billeteReservado,sistema.getReservaBilletes("LocNorm"));
		assertEquals(49,recorrido1.getPlazasDisponibles());
	}
	@Test
	void testAnularReservaNoValidaBilleteNoPreviamenteReservados() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalStateException.class, () ->{
			sistema.anularReservaBilletes("LocNorm",1);
		});
	}
	@Test
	void testAnularReservaNoValidaLocalizadorNulo() {
		Sistema sistema = new Sistema();
		assertThrows(IllegalArgumentException.class, () ->{
			sistema.anularReservaBilletes(null,1);
		});
	}
}
