package server.data.frienddata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchFriendDataTest {
	SearchFriendData data ;
	@Before
	public void setUp() throws Exception {
		data = new SearchFriendData();
	}

	@Test
	public void testGet() {
		assertEquals("”Ù∫Æ∏Ë1",data.get("10000001"));
	}

}
