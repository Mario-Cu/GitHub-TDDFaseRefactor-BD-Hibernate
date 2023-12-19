
package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hsqldb.DatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataBaseManagerTest {
	private Recorrido recorrido1;
	private Recorrido recorrido2;
	private Usuario usuario1;
	private Usuario usuario2;
	private LocalDate fecha;
	private LocalTime hora;
	private InfoRecorrido info1;
	private InfoRecorrido info2;
	private BilleteId billeteId;
	private Billete billete1;
	
 
	private DataBaseManager dataBaseManager;
	
	@BeforeEach
	void setUp() throws Exception {
		fecha = LocalDate.of(2002, 7, 18);
		hora = LocalTime.of(12, 30);
		info1 = new InfoRecorrido(fecha,hora,50,50,1);
		info2 = new InfoRecorrido(fecha,hora,50,50,1);
		recorrido1 = new Recorrido("1","origen","destino","autobus",5,info1);
		info1.setRecorrido(recorrido1);
		recorrido2 = new Recorrido("2","origen","destino","autobus",5,info2);
		info2.setRecorrido(recorrido2);
		usuario1 = new Usuario("33036946E","UsuarioNormal");
		usuario2 = new Usuario("71328961G","UsuarioNormal");
		billeteId = new BilleteId("LocNorm",1);
		billete1 = new Billete(billeteId,recorrido1,usuario1);
		
		dataBaseManager = new DataBaseManager();
       
	}

	@Test
	void addRecorridotest() {
		dataBaseManager.addRecorrido(recorrido1);
		List<Recorrido> listaRecorridosBase = dataBaseManager.getRecorridos();
		assertEquals(1, listaRecorridosBase.size());
		assertEquals(recorrido1,(listaRecorridosBase.get(0)));
	}
	
	@Test
	void eliminarRecorridoTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addRecorrido(recorrido2);
		dataBaseManager.eliminarRecorrido(recorrido2.getId());
		List<Recorrido> listaRecorridosBase = dataBaseManager.getRecorridos();
		assertEquals(1, listaRecorridosBase.size());
		assertEquals(recorrido1,listaRecorridosBase.get(0));	
	}
	@Test
	void actualizarRecorrido() {
		dataBaseManager.addRecorrido(recorrido1);
		InfoRecorrido info1Nuevo = new InfoRecorrido(fecha,hora,50,50,1);
		Recorrido recorrido1Nuevo = new Recorrido("1","origen","destinoNuevo","autobus",5,info1Nuevo);
		info1Nuevo.setRecorrido(recorrido1Nuevo);
		dataBaseManager.actualizarRecorrido(recorrido1Nuevo);
		Recorrido recorridoBase = dataBaseManager.getRecorrido(recorrido1Nuevo.getId());
		assertEquals(recorrido1Nuevo,(recorridoBase));	
	}
	@Test
	void getRecorridoTest() {
		dataBaseManager.addRecorrido(recorrido1);
		assertEquals(recorrido1,(dataBaseManager.getRecorrido(recorrido1.getId())));
	}
	@Test
	void getRecorridosTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addRecorrido(recorrido2);
		List<Recorrido> listaRecorridosBase = dataBaseManager.getRecorridos();
		assertEquals(2, listaRecorridosBase.size());
		assertEquals(recorrido1,(listaRecorridosBase.get(0)));
		assertEquals(recorrido2,(listaRecorridosBase.get(1)));
	}
	@Test
	void getRecorridosPorFechaTest() {
		dataBaseManager.addRecorrido(recorrido1);
		List<Recorrido> listaRecorridosBase = dataBaseManager.getRecorridos(recorrido1.getInfoRecorrido().getFecha());
		assertEquals(recorrido1,(listaRecorridosBase.get(0)));
	}
	@Test
	void addUsuarioTest() {
		dataBaseManager.addUsuario(usuario1);
		Usuario usuarioBase = dataBaseManager.getUsuario(usuario1.getNif());
		assertEquals(usuario1,(usuarioBase));
	}
	@Test
	void eliminarUsuario() {
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.eliminarUsuario(usuario1.getNif());
		assertEquals(null, dataBaseManager.getUsuario(usuario1.getNif()));
	}
	@Test
	void actualizarUsuarioTest() {
		dataBaseManager.addUsuario(usuario1);
		Usuario usuario1Nuevo = new Usuario("33036946E","UsuarioNuevo"); 
		dataBaseManager.actualizarUsuario(usuario1Nuevo);
		Usuario usuarioBase = dataBaseManager.getUsuario(usuario1Nuevo.getNif());
		assertEquals(usuario1Nuevo,(usuarioBase));
	}
	@Test
	void getUsuarioTest() {
		dataBaseManager.addUsuario(usuario1);
		assertEquals(usuario1,(dataBaseManager.getUsuario(usuario1.getNif())));
	}
	@Test
	void addBilleteTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		List<Billete> billetesList = dataBaseManager.getBilletes(billete1.getId().getLocalizador());
		assertEquals(1,billetesList.size());
		assertEquals(billete1,(billetesList.get(0)));
	}
	@Test
	void eliminarBilleteTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		dataBaseManager.eliminarBilletes(billete1.getId().getLocalizador());
		List<Billete> billetesList = dataBaseManager.getBilletes(billete1.getId().getLocalizador());
		assertEquals(Collections.EMPTY_LIST,billetesList);
	}
	@Test
	void actualizarBilleteTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		dataBaseManager.addRecorrido(recorrido2);
		Billete billete1Nuevo = new Billete(billeteId,recorrido2,usuario1);
		dataBaseManager.actualizarBilletes(billete1Nuevo);
		List<Billete> billetesList = dataBaseManager.getBilletes(billete1Nuevo.getId().getLocalizador());
		Billete billeteBase = billetesList.get(0);
		assertEquals(billete1Nuevo,(billeteBase));
	}
	@Test
	void getBilletesTest() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		List<Billete> billetesList = dataBaseManager.getBilletes(billete1.getId().getLocalizador());
		Billete billeteBase = billetesList.get(0);
		assertEquals(billete1,(billeteBase));
	}
	@Test
	void getBilletesDeRecorrido() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		dataBaseManager.addRecorrido(recorrido2);
		BilleteId billeteId2 = new BilleteId("LocNor2",1);
		Billete billete2 = new Billete(billeteId2, recorrido2, usuario1);
		dataBaseManager.addBillete(billete2);
		System.out.println(recorrido1.getId());
		List<Billete> billetesList = dataBaseManager.getBilletesDeRecorrido(recorrido2.getId());
		Billete billeteBase = (Billete) billetesList.get(0);
		assertEquals(billete2,(billeteBase));
	}
	@Test
	void getBilletesDeUsuario() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		List<Billete> billetesList = dataBaseManager.getBilletesDeUsuario(usuario1.getNif());
		Billete billeteBase = billetesList.get(0);
		assertEquals(billete1,(billeteBase));
	}
	@Test
	void addRecorridoNoValidoRecorridoNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.addRecorrido(null);
		});
	}
	@Test
	void addRecorridoNoValidoRecorridoEnPersistencia() {
		dataBaseManager.addRecorrido(recorrido1);
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.addRecorrido(recorrido1);
		});
	}
	@Test
	void eliminarRecorridoNoValidoRecorridoNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.eliminarRecorrido(null);
		});
	}
	@Test
	void eliminarRecorridoNoValidoRecorridoConBilletesAsociados() {
		dataBaseManager.addRecorrido(recorrido1);
		dataBaseManager.addUsuario(usuario1);
		dataBaseManager.addBillete(billete1);
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.eliminarRecorrido("1");
		});
	}
	@Test
	void actualizarRecorridoNoValidoRecorridoNulo() {
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.actualizarRecorrido(recorrido1);
		});
	}
	@Test
	void actualizarRecorridoNoValidoRecorridoNoEnPersistencia() {
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.actualizarRecorrido(recorrido1);
		});
	}
	@Test
	void addUsuarioNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.addUsuario(null);
		});
	}
	@Test
	void addUsuarioNoValidoUsuarioEnPersistencia() {
		dataBaseManager.addUsuario(usuario1);
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.addUsuario(usuario1);
		});
	}
	@Test
	void eliminarUsuarioNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.eliminarUsuario(null);
		});
	}
	@Test
	void actualizarUsuarioNoValidoUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.actualizarUsuario(null);
		});
	}
	@Test
	void actualizarUsuarioNoValidoUsuarioNoEnPersistencia() {
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.actualizarUsuario(usuario1);
		});
	}
	@Test
	void addBilleteNoValidoBilleteNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.addBillete(null);
		});
	}
	@Test
	void eliminarBilleteNoValidoBilleteNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.eliminarBilletes(null);
		});
	}
	@Test
	void actualizarBilleteNoValidoBilleteNulo() {
		assertThrows(IllegalArgumentException.class, () ->{
			dataBaseManager.actualizarBilletes(null);
		});
	}
	@Test
	void actualizarBilleteNoValidoBilleteNoEnPersistencia() {
		assertThrows(IllegalStateException.class, () ->{
			dataBaseManager.actualizarBilletes(billete1);
		});
	}
	@AfterEach
	void tearDown() {
		dataBaseManager.clearDatabase();
	}
	
	
}

