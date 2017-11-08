package simplehttp.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//Do not name it as *Test, so Maven Surefire plugin will not launch it separately from AllTestSuite
@RunWith(Parameterized.class)
public class RequestEventTests {
	private String ruleFunction;
	
    @Parameters(name= "{index}: invoke {0}")
    public static Collection<Object[]> data() throws Exception {
    	AllTestSuite.setUpBeforeClass();
    	return Arrays.asList(new Object[][]{ 
    		{"/FunctionalTests/testNormalRequest"}, 
    		{"/FunctionalTests/testBadRequest"} }
    	);
    }
    
    public RequestEventTests(String ruleFunction) {
    	this.ruleFunction = ruleFunction;
    }

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void invokeFunctionalTest() {
		try {
			// functional test implemented by BE rule-function that returns null (if passed) or error message (if failed)
			String msg = (String) AllTestSuite.engine.invokeRuleFunction(this.ruleFunction);
			if (null == msg) {
				assertNull(msg);
			} else {
				fail(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception caught: " + e.getMessage());
		}
	}
}
