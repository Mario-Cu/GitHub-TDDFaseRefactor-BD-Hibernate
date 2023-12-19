package uva.tds.practica2_grupo8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BilleteIdTest {
	
	private BilleteId id1;
	@BeforeEach
	void setUp() throws Exception {
		id1 = new BilleteId("LocNor1",1);
	}
	
	@Test
	void testConstructorBilleteId() {
		BilleteId id = new BilleteId("LocNorm",1);
		assertEquals("LocNorm",id.getLocalizador());
		assertEquals(1,id.getNumeroBillete());
	}
	@Test
	void ComparacionBilleteCoberturaIdConNulo() {
		assertNotEquals(null,id1);
	}

}
