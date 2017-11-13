package betestlib.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import static com.tibco.psg.betestclient.BETestRunner.*;

@RunWith(value = org.junit.runners.Suite.class)
@SuiteClasses(value = { 
	CreateObjectTest.class, 
	UpdateObjectTest.class,
	RuleTest.class, 
	SimpleHamcrestTest.class, 
	BE_AssertionTest.class })
public class AllTestSuite {

	@BeforeClass
	public static void initialize() throws Exception {
		initTestConnection("localhost", 8989);
	}
}
