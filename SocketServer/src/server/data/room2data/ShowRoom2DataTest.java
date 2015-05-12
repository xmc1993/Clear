package server.data.room2data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ShowRoom2DataTest {
	
	ShowRoom2Data data ;

	@Before
	public void setUp() throws Exception {
		
		data = new ShowRoom2Data();
	}

	@Test
	public void testShow() {
		ArrayList<String> result1 = data.show();
		
		assertEquals("2002 hi 郁寒歌 3 2 房客: 房客:",result1.get(1));
		
	}

}
