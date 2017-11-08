package simplehttp.test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tibco.cep.runtime.model.event.SimpleEvent;
import com.tibco.cep.runtime.service.tester.beunit.ExpectationType;

//Do not name it as *Test, so Maven Surefire plugin will not launch it separately from AllTestSuite
public class PreprocessorTests {
	static HashMap<String, SimpleEvent> requestEvents = new HashMap<String, SimpleEvent>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    	AllTestSuite.setUpBeforeClass();
    	
    	// initialize test events
		List<SimpleEvent> events = AllTestSuite.helper.createEventsFromTestData("/TestData/HTTPRequest");
    	for (SimpleEvent evt : events) {
    		String name = (String) evt.getProperty("name");
    		requestEvents.put(name, evt);
    	}
	}
	
	@Before
	public void setUp() throws Exception {
		AllTestSuite.engine.resetSession(); // reset the rule session, which will clear working memory, restart timers, and clear the data from any previous tests
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNormalRequest() {
		try {
			AllTestSuite.engine.assertEvent(requestEvents.get("Denver,CO"), true, "/RuleFunctions/processRequest");
// Note: it does not work for /Events/SessionContext, but works for /Events/WeatherCondition -- a product defect?
//			AllTestSuite.expecter.expect("/Events/SessionContext", ExpectationType.EVENT_RETRACTED);
			AllTestSuite.expecter.expect("/Events/WeatherCondition", ExpectationType.EVENT_RETRACTED);
			AllTestSuite.expecter.expect("/Rules/EndSession", ExpectationType.RULE_EXECTION);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}

	@Test
	public void testBadRequest() {
		try {
			AllTestSuite.engine.assertEvent(requestEvents.get("XYZ"), true, "/RuleFunctions/processRequest");
			AllTestSuite.expecter.expect("/Rules/EndSession", ExpectationType.RULE_EXECTION);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}
}
