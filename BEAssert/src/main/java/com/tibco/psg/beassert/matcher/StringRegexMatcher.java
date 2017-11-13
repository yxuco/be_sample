package com.tibco.psg.beassert.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class StringRegexMatcher extends TypeSafeMatcher<String> {
	protected final String regex;
	private final boolean toLowercase;

	protected StringRegexMatcher(String regex, boolean toLowercase) {
		this.regex = regex;
		this.toLowercase = toLowercase;
	}
	
	public void describeTo(Description description) {
		description.appendText(
			String.format("a string%s matches regex %s",
				(toLowercase ? " coverted to lower-case" : ""),
				regex));
	}

	@Override
	protected boolean matchesSafely(String item) {
		String value = toLowercase ? item.toLowerCase() : item;
		return value.matches(regex);
	}
	
    public static Matcher<String> matchesRegex(String regex, boolean toLowercase) {
        return new StringRegexMatcher(regex, toLowercase);
    }
}
