package server.data.frienddata;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ShowSingleFriendDataTest {
	ShowSingleFriendData data ;
	@Before
	public void setUp() throws Exception {
		data = new ShowSingleFriendData();
	}

	@Test
	public void testFind() {
		
		assertEquals("10000001 ”Ù∫Æ∏Ë1 1 1 1",data.find("10000002") );
	}

}
