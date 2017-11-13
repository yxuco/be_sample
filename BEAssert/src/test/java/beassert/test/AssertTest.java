package beassert.test;

import static com.tibco.psg.beassert.Assert.*;

import org.junit.Test;

public class AssertTest {

	@Test
	public void testAssertEqualsWithNull() {
		assertEquals("null equal null", null, null);
	}

	@Test
	public void testAssertEquals() {
		Long one = 1L;
		Long one2 = 1L;
		assertEquals("long equal", one, one2);
	}

	@Test
	public void testAssertWithinRange() {
		double ten = 10.0;
		double ten2 = 10.0000001;
		assertWithinRange("double within range", ten, ten2, 0.0001);
	}
	
	@Test
	public void testAssertNull() {
		assertNull("null value", null);
	}
}
