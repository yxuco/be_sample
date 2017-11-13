package com.tibco.psg.beassert;

import static com.tibco.be.model.functions.FunctionDomain.ACTION;

import java.util.Arrays;

import org.hamcrest.Matcher;

@com.tibco.be.model.functions.BEPackage(
	catalog = "BEUnit", 
	category = "Matchers", 
	synopsis = "BEUnit functions for Hamcrest assertions.")
public class MatcherAssert {

	@com.tibco.be.model.functions.BEFunction(
		name = "assertThat", 
		description = "Apply a matcher on actual value, throw AssertionError if it fails to match.", 
		signature = "void assertThat(String reason, Object actual, Object matcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "reason", type = "String", desc = "reason of the test failure"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "actual", type = "T", desc = "object of type (T) to be tested by the matcher"),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "matcher", type = "Matcher&lt;? super T&gt;", desc = "matcher for the type of the actual value. create the matcher by calling functions from this package, e.g., allOf(), startsWith(), etc.") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "void", desc = ""), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void assertThat(String reason, Object actual, Object matcher) {
		org.hamcrest.MatcherAssert
				.assertThat(reason, actual, (Matcher) matcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "asList", 
		description = "Convert an array of objects into List", 
		signature = "Object asList(Object... items)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "items", type = "T...", desc = "array of objects of type T") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "List&lt;T&gt;", desc = "List of testing objects of type (T)."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "asList(&quot;abc&quot;, &quot;xyz&quot;)")
	public static Object asList(Object... items) {
		return Arrays.asList(items);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "allOf", 
		description = "Creates a matcher that matches if the testing object matches <b>ALL</b> of the specified matchers.", 
		signature = "Object allOf(Object... matchers)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "matchers", type = "Matcher&lt;? super T&gt;...", desc = "Array of matchers for testing objects of type (T).  Create these matchers using functions in this package, e.g., startsWith()") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;T&gt;", desc = "The match-all matcher for testing objects of type (T).  This matcher will test all the input matchers against the testing object."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myValue&quot;, allOf(startsWithString(&quot;my&quot;), containsString(&quot;Val&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object allOf(Object... matchers) {
		if (matchers.length == 1) {
			return matchers[0];
		} else {
			Matcher[] matcherArray = new Matcher[matchers.length];
			for (int i = 0; i < matchers.length; i++) {
				matcherArray[i] = (Matcher) matchers[i];
			}
			return org.hamcrest.CoreMatchers.allOf(matcherArray);
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "anyOf", 
		description = "Creates a matcher that matches if the testing object matches <b>ANY</b> of the specified matchers.", 
		signature = "Object anyOf(Object... matchers)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "matchers", type = "Matcher&lt;? super T&gt;...", desc = "Array of matchers for testing objects of type (T).  Create these matchers using functions in this package, e.g., startWith()") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;T&gt;", desc = "The match-any matcher for testing objects if type (T).  This matcher will test all the input matchers against the testing object until it finds a match."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myValue&quot;, anyOf(startsWithString(&quot;foo&quot;), containsString(&quot;Val&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object anyOf(Object... matchers) {
		if (matchers.length == 1) {
			return matchers[0];
		} else {
			Matcher[] matcherArray = new Matcher[matchers.length];
			for (int i = 0; i < matchers.length; i++) {
				matcherArray[i] = (Matcher) matchers[i];
			}
			return org.hamcrest.CoreMatchers.anyOf(matcherArray);
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "everyItem", 
		description = "Creates a matcher for a list of testing items that matches if all items are matched by the specified itemMatcher.", 
		signature = "Object everyItem(Object itemMatcher)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "itemMatcher", type = "Matcher&lt;T&gt;", desc = "Matcher for testing object of type (T).  Note that it may be multiple match conditions combined by allOf() or anyOf().") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;Iterable&lt;? extends T&gt;&gt;", desc = "The matcher to test a list of items."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, Arrays.asList(&quot;bar&quot;, &quot;baz&quot;), everyItem(startsWithString(&quot;ba&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object everyItem(Object itemMatcher) {
		return org.hamcrest.CoreMatchers.everyItem((Matcher) itemMatcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "hasItem", 
		description = "Creates a matcher for a list of testing items that matches if at least one item is matched by the specified itemMatcher.", 
		signature = "Object hasItem(Object itemMatcher)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "itemMatcher", type = "Matcher&lt;T&gt;", desc = "Matcher for testing object of type (T).  Note that it may be multiple match conditions combined by allOf() or anyOf().") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;Iterable&lt;? super T&gt;&gt;", desc = "The matcher to test a list of items."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), hasItem(startsWithString(&quot;ba&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object hasItem(Object itemMatcher) {
		return org.hamcrest.CoreMatchers.hasItem((Matcher) itemMatcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "equalTo", 
		description = "Creates a matcher that matches if the testing object is equal to the operand by calling Object#equals.  For array, all items at the same index must be equal.", 
		signature = "Object equalTo(Object operand)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "operand", type = "T", desc = "expected value to match the testing object of type (T)") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;T&gt;", desc = "The matcher to test the equality of the operand against the testing object."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;foo&quot;, equalTo(&quot;foo&quot;))")
	public static Object equalTo(Object operand) {
		return org.hamcrest.CoreMatchers.equalTo(operand);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "not", 
		description = "Creates a matcher that matches if the testing object is opposite to equalTo, i.e., it is not equal to the specified value.  If the input value is a Matcher, this will return a matcher that inverts the logic of the input matcher.", 
		signature = "Object not(Object value)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "value", type = "T, or Matcher&lt;T&gt;", desc = "unexpected value to check against a testing object of type (T), or another matcher on type T to be inverted.") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;T&gt;", desc = "The matcher to test the equality of the value against the testing object."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, cheese, not(smelly))")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object not(Object value) {
		if (value instanceof Matcher) {
			return org.hamcrest.CoreMatchers.not((Matcher) value);
		} else {
			return org.hamcrest.CoreMatchers.not(value);
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "sameInstance", 
		description = "Creates a matcher that matches only when the testing object is the same instance as the specified target object, i.e., using == operator.", 
		signature = "Object sameInstance(Object target)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "target", type = "T", desc = "the target instance to check against a testing object of type (T).") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;T&gt;", desc = "The matcher to test for identity of testing object."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "")
	public static Object sameInstance(Object target) {
		return org.hamcrest.CoreMatchers.sameInstance(target);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "notNullValue", 
		description = "Creates a matcher that is a shortcut for <code>not(nullValue())</code>.", 
		signature = "Object notNullValue()", 
		params = {}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher", desc = "The matcher to test for non-null value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, cheese, notNullValue())")
	public static Object notNullValue() {
		return org.hamcrest.CoreMatchers.notNullValue();
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "nullValue", 
		description = "Creates a matcher that matches if the testing object is <code>null</code>.", 
		signature = "Object nullValue()", 
		params = {}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher", desc = "The matcher to test for null value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, cheese, nullValue())")
	public static Object nullValue() {
		return org.hamcrest.CoreMatchers.nullValue();
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "containsString", 
		description = "Creates a matcher that matches if the testing string contains a specified substring anywhere.", 
		signature = "Object containsString(String substring)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "substring", type = "String", desc = "substring to match anywhere in the testing string") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;String&gt;", desc = "The matcher to test if the substring exists anywhere in an actual value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myStringOfNote&quot;, containsString(&quot;ring&quot;))")
	public static Object containsString(java.lang.String substring) {
		return org.hamcrest.CoreMatchers.containsString(substring);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "startsWithString", 
		description = "Creates a matcher that matches if the testing string starts with a specified prefix.", 
		signature = "Object startsWithString(String prefix)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "prefix", type = "String", desc = "prefix to match the beginning of the testing string") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;String&gt;", desc = "The matcher to test the prefix in an actual value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myStringOfNote&quot;, startsWithString(&quot;my&quot;))")
	public static Object startsWithString(java.lang.String prefix) {
		return org.hamcrest.CoreMatchers.startsWith(prefix);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "endsWithString", 
		description = "Creates a matcher that matches if the testing string ends with a specified suffix.", 
		signature = "Object endsWithString(String suffix)", 
		params = { 
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "suffix", type = "String", desc = "suffix to match the end of the testing string") 
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;String&gt;", desc = "The matcher to test the suffix in an actual value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myStringOfNote&quot;, endsWithString(&quot;Note&quot;))")
	public static Object endsWithString(java.lang.String suffix) {
		return org.hamcrest.CoreMatchers.endsWith(suffix);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "matchesRegex", 
		description = "Creates a matcher that matches if the testing string conforms to a specified regex pattern.", 
		signature = "Object matchesRegex(String regex)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "regex", type = "String", desc = "regex pattern to match the testing string")			
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;String&gt;", desc = "The matcher to match the regex pattern on the actual value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myStringOfNote&quot;, matchesRegex(&quot;^my.*&quot;))")
	public static Object matchesRegex(java.lang.String regex) {
		return com.tibco.psg.beassert.matcher.StringRegexMatcher.matchesRegex(regex, false);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "instanceOf", 
		description = "Creates a matcher that matches if the testing object is an instance of a specified type.", 
		signature = "Object instanceOf(String expectedType)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "expectedType", type = "String", desc = "expected type of the testing object, e.g., int, double, DateTime, or /Concepts/Container")			
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher", desc = "The matcher to match the type of the actual value."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, &quot;myStringOfNote&quot;, instanceOf(&quot;String&quot;))")
	public static Object instanceOf(java.lang.String expectedType) {
		return com.tibco.psg.beassert.matcher.InstanceOfMatcher.instanceOf(expectedType);
	}
	
	@com.tibco.be.model.functions.BEFunction(
		name = "hasSize", 
		description = "Creates a matcher for a list of testing items that matches if the expected number of items are matched by the specified itemMatcher.", 
		signature = "Object hasSize(Object itemMatcher, Object sizeMatcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "itemMatcher", type = "Matcher&lt;T&gt;", desc = "the matcher to apply to every item in the testing object list. null to count all."),			
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "sizeMatcher", type = "int or Matcher<Integer>", desc = "expected number of objects that conform to the itemMatcher.")			
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;Iterable&lt;? extends T&gt;&gt;", desc = "The matcher to check the number of matching objects in the testing list."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, asList(&quot;bar&quot;, &quot;baz&quot;), hasSize(startsWithString(&quot;ba&quot;), 2))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object hasSize(Object itemMatcher, Object sizeMatcher) {
		if (sizeMatcher instanceof Matcher) {
			return com.tibco.psg.beassert.matcher.CollectionSizeMatcher.hasSize((Matcher) itemMatcher, (Matcher) sizeMatcher);
		} 
		else {
			return com.tibco.psg.beassert.matcher.CollectionSizeMatcher.hasSize((Matcher) itemMatcher, org.hamcrest.CoreMatchers.equalTo(sizeMatcher));
		}
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "itemAt", 
		description = "Creates a matcher for a list of testing items that matches if the item at an index is matched by the specified itemMatcher.", 
		signature = "Object itemAt(int index, Object itemMatcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "index", type = "int", desc = "the index of item that conform to the itemMatcher."),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "itemMatcher", type = "Matcher&lt;T&gt;", desc = "the matcher to apply to the item in the testing object list.")
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;List&lt;? extends T&gt;&gt;", desc = "The matcher to check the testing list."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, asList(&quot;bar&quot;, &quot;baz&quot;), itemAt(1, startsWithString(&quot;ba&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object itemAt(int index, Object itemMatcher) {
		return com.tibco.psg.beassert.matcher.ItemAtIndexMatcher.itemAt(index, (Matcher) itemMatcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "itemForKey", 
		description = "Creates a matcher for a map of testing items that matches if exactly the item for a key is matched by the specified itemMatcher.", 
		signature = "Object itemForKey(Object key, Object itemMatcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "key", type = "Object", desc = "the key of the item that conform to the itemMatcher."),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "itemMatcher", type = "Matcher&lt;T&gt;", desc = "the matcher to apply to the item in the testing object list.")
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher&lt;Map&lt;K,V&gt;&gt;", desc = "The matcher to check the testing map."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, aMap, itemForKey(key, startsWithString(&quot;ba&quot;)))")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object itemForKey(Object key, Object itemMatcher) {
		return com.tibco.psg.beassert.matcher.ItemMappedKeyMatcher.itemForKey(key, (Matcher) itemMatcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "withProperty", 
		description = "Creates a matcher for a BE entity that matches if a named property is matched by the specified propertyMatcher.", 
		signature = "Object withProperty(String propertyName, Object propertyMatcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "propertyName", type = "String", desc = "name of the property to be matched by propertyMatcher."),
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "propertyMatcher", type = "Matcher", desc = "the matcher to apply to the property of the testing entity.")			
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher", desc = "The matcher for the testing entity containing the specified property."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, aConcept, withProperty(&quot;aStringProp&quot;, startsWithString(&quot;ba&quot;)))")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object withProperty(String propertyName, Object propertyMatcher) {
		return com.tibco.psg.beassert.matcher.EntityPropertyMatcher.withProperty(propertyName, (Matcher) propertyMatcher);
	}

	@com.tibco.be.model.functions.BEFunction(
		name = "withPayload", 
		description = "Creates a matcher for a BE event that matches if its payload is matched by the specified payloadMatcher.", 
		signature = "Object withPayload(Object payloadMatcher)", 
		params = {
			@com.tibco.be.model.functions.FunctionParamDescriptor(name = "payloadMatcher", type = "Matcher", desc = "the matcher to apply to the payload of the testing event.")			
		}, 
		freturn = @com.tibco.be.model.functions.FunctionParamDescriptor(name = "", type = "Matcher", desc = "The matcher for the testing event."), 
		version = "1.0", see = "", mapper = @com.tibco.be.model.functions.BEMapper(), 
		cautions = "none", fndomain = { ACTION }, example = "assertThat(null, anEvent, withPayload(containsString(&quot;xml&quot;)))")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object withPayload(Object payloadMatcher) {
		return com.tibco.psg.beassert.matcher.EntityPropertyMatcher.withPayload((Matcher) payloadMatcher);
	}
}
