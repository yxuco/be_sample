package simplehttp.test;

import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Do not name it as *TestSuite, so Maven Surefire plugin will not launch it separately from AllTestSuite
@RunWith(Suite.class)
@SuiteClasses({ YahooWeatherTests.class, FibonacciTests.class })
public class RuleFunctionSuite {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    	AllTestSuite.setUpBeforeClass();
	}

}
