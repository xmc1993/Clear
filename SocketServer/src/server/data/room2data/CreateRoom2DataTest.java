package server.data.room2data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreateRoom2DataTest {
	
	CreateRoom2Data data;

	@Before
	public void setUp() throws Exception {
		data = new CreateRoom2Data();
	}

	@Test
	public void testAdd() {
		
		assertEquals(true , data.add(2001, "hi", "������1", 2,1));
		
	}

}
