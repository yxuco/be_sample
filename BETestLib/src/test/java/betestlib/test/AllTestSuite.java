package betestlib.test;

import org.junit.AfterClass;
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
	static Process engine = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// pass repoRoot to unit test when using studio, e.g., use VM args: -DrepoRoot=/git/be
		String repoRoot = System.getProperty("repoRoot");
		String baseDir = null == repoRoot ? "" : repoRoot + "/BETestLib/";
		System.out.println("baseDir=" + baseDir);
		if (null == engine) {
			engine = startBEEngine(baseDir + "target/be-engine-args", 180);
		}
		initTestConnection("localhost", 8989);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			if (engine != null) {
				stopProcess(engine, 5000);
			}
		} catch (Exception localException) {
		}
	}

}
