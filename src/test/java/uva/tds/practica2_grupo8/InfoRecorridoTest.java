package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfoRecorridoTest {
	
	private Recorrido recorrido;
	private LocalDate fecha;
	private LocalTime hora;
	
	@BeforeEach
	void setUp() throws Exception {
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		
	}
	@Test
	void testConstructorInfoRecorrido() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,1,1);
		recorrido = new Recorrido("1","origen","destino","autobus",0,info);
		info.setRecorrido(recorrido);
		assertEquals(fecha,info.getFecha());
		assertEquals(hora,info.getHora());
		assertEquals(1,info.getPlazasDisponibles());
		assertEquals(1,info.getPlazasTotales());
		assertEquals(1,info.getMinutos());
		assertEquals(recorrido,info.getRecorrido());
	}
	@Test
	void testSetPlazasTotalesInfoRecorrido(){
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,1,1);
		info.setPlazasTotales(2);
		assertEquals(2,info.getPlazasTotales());
	}
	@Test
	void testSetMinutosInfoRecorrido(){
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,1,1);
		info.setMinutos(2);
		assertEquals(2,info.getMinutos());
	}

	@Test
	void testComparacionInfoRecorridoNoIgualANull() {
		InfoRecorrido info = new InfoRecorrido(fecha,hora,1,1,1);
		assertNotEquals(null,info);
	}

	@Test
	void testComparacionInfoRecorridoNoIgualFechaDiferente() {
		LocalDate fecha2 = LocalDate.of(2002, 8, 19);
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		InfoRecorrido info2 = new InfoRecorrido(fecha2,hora,1,1,1);
		assertNotEquals(info1,info2);
		
	}
	@Test
	void testComparacionInfoRecorridoNoIgualHoraDiferente() {
		LocalTime hora2 = LocalTime.of(11, 00);
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora2,1,1,1);
		assertNotEquals(info1,info2);
		
	}
	@Test
	void testComparacionInfoRecorridoNoIgualPlazasDisponiblesDiferente() {
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,2,1,1);
		assertNotEquals(info1,info2);
		
	}
	@Test
	void testComparacionInfoRecorridoNoIgualPlazasTotalesDiferente() {
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,1,2,1);
		assertNotEquals(info1,info2);
		
	}
	@Test
	void testComparacionInfoRecorridoNoIgualMinutosDiferente() {
		InfoRecorrido info1 = new InfoRecorrido(fecha,hora,1,1,1);
		InfoRecorrido info2 = new InfoRecorrido(fecha,hora,1,1,2);
		assertNotEquals(info1,info2);
		
	}

}
