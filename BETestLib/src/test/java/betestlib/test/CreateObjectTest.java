package betestlib.test;

import org.junit.Test;

import static com.tibco.psg.betestclient.BETestRunner.*;

public class CreateObjectTest {

	@Test
	public void testCreatePrimitiveItem() {
		assertRuleFunction(
			"/Test/CreateObjectTest/testCreatePrimitiveItem", true);
	}

	@Test
	public void testCreateContainer() {
		assertRuleFunction(
			"/Test/CreateObjectTest/testCreateContainer", true);
	}
}
