package server.data.frienddata;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ShowFriendDataTest {
	ShowFriendData data ;
	@Before
	public void setUp() throws Exception {
		data = new ShowFriendData();
	}

	@Test
	public void testFindsString() {
		ArrayList<String> list = data.finds("10000002") ;
		assertEquals("10000001 ”Ù∫Æ∏Ë1 1 1 1",list.get(0));
	}

}
