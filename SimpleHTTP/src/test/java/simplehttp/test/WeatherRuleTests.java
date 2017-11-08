package simplehttp.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.tibco.cep.runtime.model.event.SimpleEvent;

//Do not name it as *Test, so Maven Surefire plugin will not launch it separately from AllTestSuite
@RunWith(Parameterized.class)
public class WeatherRuleTests {
	private SimpleEvent testEvent;
	private String ruleName;
	
    @Parameters(name= "{index}: fired rule {1}")
    public static Collection<Object[]> data() throws Exception {
    	AllTestSuite.setUpBeforeClass();
		List<SimpleEvent> events = AllTestSuite.helper.createEventsFromTestData("/TestData/WeatherCondition");
		ArrayList<Object[]> params = new ArrayList<Object[]>();
    	for (SimpleEvent evt : events) {
    		String condition = (String) evt.getProperty("condition");
    		String rule = "/Rules/Other";  // default rule
    		if (condition.matches(".*(Clear|Sunny).*")) {
    			rule = "/Rules/Clear";
    		} else if (condition.matches(".*(Shower|Thunderstorm).*")) {
    			rule = "/Rules/Shower";
    		} else if (condition.matches("Cloudy|Rain|Snow")) {
    			rule = "/Rules/" + condition;
    		}
    		params.add(new Object[]{evt, rule});
    	}
        return params;
    }

    public WeatherRuleTests(SimpleEvent testEvent, String ruleName) {
    	this.testEvent = testEvent;
    	this.ruleName = ruleName;
    }
    
	@Before
	public void setUp() throws Exception {
		AllTestSuite.engine.resetSession(); // reset the rule session, which will clear working memory, restart timers, and clear the data from any previous tests
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWeatherRule() {
		try {
			AllTestSuite.engine.assertEvent(this.testEvent, true);
			AllTestSuite.expecter.expectRuleFired(this.ruleName);
		} catch (Exception e) {
			e.printStackTrace();
			fail(String.format("Exception caught on rule %s: %s", this.ruleName, e.getMessage()));
		}
	}
}
