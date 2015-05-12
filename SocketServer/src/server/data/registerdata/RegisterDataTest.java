package server.data.registerdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegisterDataTest {
	RegisterData data ;
	@Before
	public void setUp() throws Exception {
		data = new RegisterData();
	}

	@Test
	public void testAdd() {
		
		assertEquals(true,data.add("1000011", "ܳ", "ܳ", "0", "1"));
	}

}
