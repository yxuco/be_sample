package betestlib.test;

import org.junit.Test;

import static com.tibco.psg.betestclient.BETestRunner.*;

public class BE_AssertionTest {
	public static final String folder = "/Test/BE_AssertionTest/";
	
	@Test
	public void testFalseInstanceOfConcept() {
		assertRuleFunction(
			folder + "testFalseInstanceOfConcept", true);
	}

	@Test
	public void testTrueInstanceOfConcept() {
		assertRuleFunction(
			folder + "testTrueInstanceOfConcept", true);
	}

	@Test
	public void testFalseInstanceOfEvent() {
		assertRuleFunction(
			folder + "testFalseInstanceOfEvent", true);
	}

	@Test
	public void testTrueInstanceOfEvent() {
		assertRuleFunction(
			folder + "testTrueInstanceOfEvent", true);
	}

	@Test
	public void testFalseInstanceOfDateTime() {
		assertRuleFunction(
			folder + "testFalseInstanceOfDateTime", true);
	}

	@Test
	public void testTrueInstanceOfPrimitive() {
		assertRuleFunction(
			folder + "testTrueInstanceOfPrimitive", true);
	}

	@Test
	public void testTrueConceptPrimitiveProperty() {
		assertRuleFunction(
			folder + "testTrueConceptPrimitiveProperty", true);
	}

	@Test
	public void testTrueConceptPrimitivePropertyArray() {
		assertRuleFunction(
			folder + "testTrueConceptPrimitivePropertyArray", true);
	}

	@Test
	public void testTrueConceptReferenceProperty() {
		assertRuleFunction(
			folder + "testTrueConceptReferenceProperty", true);
	}

	@Test
	public void testTrueConceptReferencePropertyArray() {
		assertRuleFunction(
			folder + "testTrueConceptReferencePropertyArray", true);
	}

	@Test
	public void testTrueContainedConceptProperty() {
		assertRuleFunction(
			folder + "testTrueContainedConceptProperty", true);
	}

	@Test
	public void testTrueEventPayload() {
		assertRuleFunction(
			folder + "testTrueEventPayload", true);
	}

	@Test
	public void testTrueEventProperty() {
		assertRuleFunction(
			folder + "testTrueEventProperty", true);
	}
}
