package server.data.frienddata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteFriendDataTest {
	DeleteFriendData data ;

	@Before
	public void setUp() throws Exception {
		data = new DeleteFriendData();
	}

	@Test
	public void testDelete() {
		assertEquals(true,data.delete("10000001", "10000002"));
	}

}
