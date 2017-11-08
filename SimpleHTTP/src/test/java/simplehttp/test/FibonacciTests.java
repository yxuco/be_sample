package simplehttp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//Do not name it as *Test, so Maven Surefire plugin will not launch it separately from AllTestSuite
@RunWith(Parameterized.class)
public class FibonacciTests {
	private int input;
	private long fib;
	
    @Parameters(name= "{index}: fib({0}) = {1}")
    public static Collection<Object[]> data() throws Exception {
    	return Arrays.asList(new Object[][]{ {0, 1}, {1, 2}, {2, 3}, {3, 5}, {4, 8} });
    }

    public FibonacciTests(int input, long fib) {
    	this.input = input;
    	this.fib = fib;
    }
    
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
	public void testFibonacci() {
		try {
			long result = (Long) AllTestSuite.engine.invokeRuleFunction("/RuleFunctions/calcFibonacci", this.input);
			assertEquals(result, this.fib);
		} catch (Exception e) {
			e.printStackTrace();
			fail(String.format("Exception caught on calcFibonacci: %s", e.getMessage()));
		}
	}

}
