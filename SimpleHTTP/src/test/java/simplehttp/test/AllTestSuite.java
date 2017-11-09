package simplehttp.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tibco.cep.runtime.service.tester.beunit.BETestEngine;
import com.tibco.cep.runtime.service.tester.beunit.Expecter;
import com.tibco.cep.runtime.service.tester.beunit.TestDataHelper;

// Name this class as *TestSuite, so Maven Surefire plugin will use it to start all tests
// Note: RuleFunction Tests (invokeRuleFunction) must be executed first -- possibly a product defect.
@RunWith(Suite.class)
@SuiteClasses({ RuleFunctionSuite.class, FunctionalSuite.class, RuleSuite.class })
public class AllTestSuite {
	static BETestEngine engine;
	static TestDataHelper helper;
	static Expecter expecter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (null == engine) {
			engine = new BETestEngine("/Users/yxu/Developer/git/be/SimpleHTTP/target/test-classes/config.properties");
			engine.start();
			helper = new TestDataHelper(engine);
			expecter = new Expecter(engine);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
//			engine.shutdown();
		} catch (Exception localException) {
		}
	}
}
