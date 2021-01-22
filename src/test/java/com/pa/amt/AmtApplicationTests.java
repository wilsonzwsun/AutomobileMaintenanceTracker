package com.pa.amt;

import com.pa.amt.util.TokenCheck;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes=AmtApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AmtApplicationTests {

	@Test
	void test() {
		//tk = new TokenCheck();
		boolean d1 = TokenCheck.deleteAuth("dfsadafsa5436dsgs");
		boolean t1 = TokenCheck.updateAuth("3435sdfsadaf122sa");

		boolean d2 = TokenCheck.updateAuth("02943ldsfjsdfsfsdll");
		boolean t2 = TokenCheck.deleteAuth("132$#%%^8sdjssg0459kglfal");

		System.out.println("d2= "+ d2 +" t2 = " + t2);
		assertFalse(d1);
		assertFalse(t1);

		assertTrue(d2);
		assertTrue(t2);
	}

}
