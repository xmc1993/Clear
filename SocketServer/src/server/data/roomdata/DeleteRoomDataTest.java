package server.data.roomdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteRoomDataTest {
	DeleteRoomData data ;
	@Before
	public void setUp() throws Exception {
		data = new DeleteRoomData();
	}

	@Test
	public void testDelete() {
		assertEquals(true,data.delete("”Ù∫Æ∏Ë1"));
	}

}
