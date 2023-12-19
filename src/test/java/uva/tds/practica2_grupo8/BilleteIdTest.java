package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BilleteIdTest {
	
	private BilleteId id1;
	private BilleteId id2;
	@BeforeEach
	void setUp() throws Exception {
		id1 = new BilleteId("LocNor1",1);
		id2 = new BilleteId("LocNor2",1);
	}
	
	@Test
	void testConstructorBilleteId() {
		BilleteId id = new BilleteId("LocNorm",1);
		assertEquals("LocNorm",id.getLocalizador());
		assertEquals(1,id.getNumeroBillete());
	}
	@Test
	void ComparacionBilleteCoberturaIdConNulo() {
		assertNotEquals(id1,null);
	}
	@Test
	void ComparacionBilleteCoberturaIdConOtraClase() {
		Usuario usrNormal = new Usuario("33036946E","UsuarioNormal");
		assertNotEquals(id1,usrNormal);
	}

}
