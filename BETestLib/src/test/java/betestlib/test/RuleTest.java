package betestlib.test;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.tibco.psg.betestclient.BETestRunner.*;

public class RuleTest {

	@BeforeClass
	public static void setupUpdatePrimitiveItem() {
		assertRuleFunction(
			"/Test/RuleTest/setupUpdatePrimitiveItem", true);
	}

	@Test
	public void testUpdatePrimitiveItemInPreproc() {
		assertRuleFunction(
			"/Test/RuleTest/testUpdatePrimitiveItemInPreproc", true);
	}

	@Test
	public void testUpdatePrimitiveItemInRule() {
		assertRuleFunction(
			"/Test/RuleTest/testUpdatePrimitiveItemInRule", false);
	}
}
