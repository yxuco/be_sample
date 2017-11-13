package betestlib.test;

import org.junit.Test;

import static com.tibco.psg.betestclient.BETestRunner.*;

public class SimpleHamcrestTest {
	public static final String folder = "/Test/SimpleHamcrestTest/";
	
	@Test
	public void testFalseAllOf() {
		assertRuleFunction(
			folder + "testFalseAllOf", true);
	}

	@Test
	public void testTrueAllOf() {
		assertRuleFunction(
			folder + "testTrueAllOf", true);
	}

	@Test
	public void testFalseAnyOf() {
		assertRuleFunction(
			folder + "testFalseAllOf", true);
	}

	@Test
	public void testTrueAnyOf() {
		assertRuleFunction(
			folder + "testTrueAllOf", true);
	}

	@Test
	public void testFalseEveryItem() {
		assertRuleFunction(
			folder + "testFalseEveryItem", true);
	}

	@Test
	public void testTrueEveryItem() {
		assertRuleFunction(
			folder + "testTrueEveryItem", true);
	}

	@Test
	public void testFalseHasItem() {
		assertRuleFunction(
			folder + "testFalseHasItem", true);
	}

	@Test
	public void testTrueHasItem() {
		assertRuleFunction(
			folder + "testTrueHasItem", true);
	}

	@Test
	public void testFalseNotMatcher() {
		assertRuleFunction(
			folder + "testFalseNotMatcher", true);
	}

	@Test
	public void testTrueNotMatcher() {
		assertRuleFunction(
			folder + "testTrueNotMatcher", true);
	}

	@Test
	public void testFalseNotValue() {
		assertRuleFunction(
			folder + "testFalseNotValue", true);
	}

	@Test
	public void testTrueNotValue() {
		assertRuleFunction(
			folder + "testTrueNotValue", true);
	}

	@Test
	public void testFalseNullValue() {
		assertRuleFunction(
			folder + "testFalseNullValue", true);
	}

	@Test
	public void testTrueNullValue() {
		assertRuleFunction(
			folder + "testTrueNullValue", true);
	}

	@Test
	public void testFalseNotNullValue() {
		assertRuleFunction(
			folder + "testFalseNotNullValue", true);
	}

	@Test
	public void testTrueNotNullValue() {
		assertRuleFunction(
			folder + "testTrueNotNullValue", true);
	}

	@Test
	public void testFalseRegexMatch() {
		assertRuleFunction(
			folder + "testFalseRegexMatch", true);
	}

	@Test
	public void testTrueRegexMatch() {
		assertRuleFunction(
			folder + "testTrueRegexMatch", true);

	}
	@Test
	public void testFalseCollectionLength() {
		assertRuleFunction(
			folder + "testFalseCollectionLength", true);
	}

	@Test
	public void testTrueCollectionLength() {
		assertRuleFunction(
			folder + "testTrueCollectionLength", true);
	}

	@Test
	public void testTrueListItemAt() {
		assertRuleFunction(
			folder + "testTrueListItemAt", true);
	}

	@Test
	public void testTrueMapItemFor() {
		assertRuleFunction(
			folder + "testTrueMapItemForKey", true);
	}
}
