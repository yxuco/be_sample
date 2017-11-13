package com.tibco.psg.beassert;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;

@com.tibco.be.model.functions.BEPackage(
	catalog = "BEUnit", 
	category = "Assert", 
	synopsis = "BEUnit functions for JUnit assertion.")
public class Assert {

	@com.tibco.be.model.functions.BEFunction(
		name = "fail", 
		description = "Throws AssersionError with a specified message.", 
		signature = "void fail(String reason)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void fail(String reason) {
		if (reason == null) {
			throw new AssertionError();
		}
		throw new AssertionError(reason);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertTrue", 
		description = "Throws AssersionError if condition is not true.", 
		signature = "void assertTrue(String reason, boolean condition)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "condition", type = "boolean", desc = "condition to be checked") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertTrue(String reason, boolean condition) {
		if (!condition) {
			fail(reason);
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertFalse", 
		description = "Throws AssersionError if condition is not false.", 
		signature = "void assertFalse(String reason, boolean condition)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "condition", type = "boolean", desc = "condition to be checked") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertFalse(String reason, boolean condition) {
		assertTrue(reason, !condition);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertEquals", 
		description = "Throws AssersionError if 2 objects are not equal.", 
		signature = "void assertEquals(String reason, Object expected, Object actual)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "expected", type = "Object", desc = "expected value"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "Object", desc = "actual value") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertEquals(String reason, Object expected, Object actual) {
		if (null == expected) {
			assertTrue(reason, null == actual);
		} else {
			assertTrue(reason, expected.equals(actual));
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertNotEquals", 
		description = "Throws AssersionError if 2 objects are equal.", 
		signature = "void assertNotEquals(String reason, Object unexpected, Object actual)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "unexpected", type = "Object", desc = "expected value"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "Object", desc = "actual value") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertNotEquals(String reason, Object unexpected, Object actual) {
		if (null == unexpected) {
			assertTrue(reason, null != actual);
		} else {
			assertTrue(reason, !unexpected.equals(actual));
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertWithinRange", 
		description = "Throws AssersionError if difference between 2 doubles is greater than a delta.", 
		signature = "void assertNotEquals(String reason, double expected, double actual, double delta)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "expected", type = "double", desc = "expected value"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "double", desc = "actual value"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "delta", type = "double", desc = "maximum difference between the 2 numbers"), 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertWithinRange(String reason, double expected, double actual, double delta) {
		assertTrue(reason, Math.abs(expected - actual) <= delta);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertNull", 
		description = "Throws AssersionError if an object is not null.", 
		signature = "void assertNull(String reason, Object actual)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "Object", desc = "actual value to check") 
		}, freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertNull(String reason, Object actual) {
		assertTrue(reason, null == actual);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "assertNotNull", 
		description = "Throws AssersionError if an object is null.", 
		signature = "void assertNotNull(String reason, Object actual)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "Object", desc = "actual value to check") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static void assertNotNull(String reason, Object actual) {
		assertTrue(reason, null != actual);
	}
}
