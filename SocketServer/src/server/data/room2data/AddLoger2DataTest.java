package server.data.room2data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AddLoger2DataTest {
	AddLoger2Data data ;
	@Before
	public void setUp() throws Exception {
		data = new AddLoger2Data();
	}

	@Test
	public void testUpdate() {
		assertEquals(true, data.update("2002", "hi", "������", "3", "2","����: ���� ��è��1"
				,"����: ���� ��è��1"));
	}

}
