package server.data.roomdata;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ShowRoomDataTest {
	ShowRoomData data ;
	@Before
	public void setUp() throws Exception {
		data = new ShowRoomData();
	}

	@Test
	public void testShow() {
		ArrayList<String> result1 = data.show();
		for(String s : result1){
			assertEquals("1001 hi Óôº®¸è1 3 ·¿¿Í:",s);
		}
	}

}
