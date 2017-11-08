package simplehttp.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//Do not name it as *Test, so Maven Surefire plugin will not launch it separately from AllTestSuite
public class YahooWeatherTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    	AllTestSuite.setUpBeforeClass();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNormalYahooResponse() {
		try {
			Object respNode = AllTestSuite.engine.invokeRuleFunction("/RuleFunctions/queryYahooWeather", "Denver,CO");
			assertNotNull(respNode);
			String respJson = respNode.toString();
			assertFalse(respJson.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}

	@Test
	public void testCityOnlyYahooRequest() {
		try {
			Object respNode = AllTestSuite.engine.invokeRuleFunction("/RuleFunctions/queryYahooWeather", "Denver");
			assertNotNull(respNode);
			String respJson = respNode.toString();
			assertFalse(respJson.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}
	
	@Test
	public void testBadYahooRequest() {
		try {
			Object respNode = AllTestSuite.engine.invokeRuleFunction("/RuleFunctions/queryYahooWeather", "XYZ");
			assertNotNull(respNode);
			String respJson = respNode.toString();
			assertTrue(respJson.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}

}
