package com.tibco.psg.beassert.matcher;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class InstanceOfMatcher extends DiagnosingMatcher<Object> {

	private final String expectedTypeName;
	private final String expectedClassName;
	
	public InstanceOfMatcher(String expectedTypeName) {
		this.expectedTypeName = expectedTypeName;
		this.expectedClassName = toClassName(expectedTypeName);
	}
	
	private static String toClassName(String expectedTypeName) {
		if (null == expectedTypeName) {
			return null;
		}
		else if (expectedTypeName.contains("/")){
			String name = expectedTypeName.replaceAll("/", ".");
			if (name.startsWith(".")) {
				return "be.gen" + name;
			}
			else {
				return "be.gen." + name;
			}
		}
		else if (expectedTypeName.toLowerCase().startsWith("bool")) {
			return "java.lang.Boolean";
		}
		else if (expectedTypeName.toLowerCase().startsWith("int")) {
			return "java.lang.Integer";
		}
		else if ("long".equalsIgnoreCase(expectedTypeName)) {
			return "java.lang.Long";
		}
		else if (expectedTypeName.toLowerCase().matches("float|double")) {
			return "java.lang.Double";
		}
		else if ("string".equalsIgnoreCase(expectedTypeName)) {
			return "java.lang.String";
		}
		else if (expectedTypeName.toLowerCase().startsWith("date")) {
			return "java.util.GregorianCalendar";
		}
		return "be.gen." + expectedTypeName;
	}
	
	public void describeTo(Description description) {
		description.appendText("an instance of ").appendText(expectedTypeName);
	}

	@Override
	protected boolean matches(Object item, Description mismatch) {
		if (null == item) {
			return false;
		}
		else if (item.getClass().getName().equals(expectedClassName)) {
			return true;
		}
		else {
			mismatch.appendText(" is a ").appendText(item.getClass().getName());
			return false;
		}
	}

	public static Matcher<?> instanceOf(String expectedTypeName) {
		return new InstanceOfMatcher(expectedTypeName);
	}
}
