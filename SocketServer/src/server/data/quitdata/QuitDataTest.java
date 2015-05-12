package server.data.quitdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuitDataTest {
	QuitData data ;
	@Before
	public void setUp() throws Exception {
		data = new QuitData();
	}

	@Test
	public void testDelete() {
		assertEquals(true , data.delete("”Ù∫Æ∏Ë1"));
	}

}
