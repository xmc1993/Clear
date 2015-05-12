package server.data.roomdata;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DeleteLogerDataTest {
	DeleteLogerData data ;
	@Before
	public void setUp() throws Exception {
		data = new DeleteLogerData();
	}

	@Test
	public void testFind() {
		String result1 = data.find("Óôº®¸è1",1);
		 String result2 = data.find("Óôº®¸è",1);
		 String result3 = data.find("1",1);
		 String result4 = data.find("3",1);
		 assertEquals("1001", result1);
		 assertEquals("1002", result2);
		 assertEquals("1003", result3);
		 assertEquals("1004", result4);
	}

	@Test
	public void testGetLoger() {
		ArrayList<String> result1 = data.getLoger("1001");
		ArrayList<String> result2 = data.getLoger("1002");
		for(String s : result1){		
			assertEquals("ÏÄî£î£", s);
			
		}
		for(String s : result2){		
			assertEquals("ĞìÃ¨³É1", s);
		}
	}

	@Test
	public void testUpdate() {
		assertEquals(true, data.update("1001", "hi", "Óôº®¸è1", "3", "·¿¿Í: ÏÄî£î£"));
	}

}
