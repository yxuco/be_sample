package beassert.test;

import static com.tibco.psg.beassert.MatcherAssert.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MatchAssertTest {

	@Test
	public void testAllOf() {
		assertThat("AllOf", "myValue", allOf(startsWithString("my"), containsString("Val")));
	}

	@Test
	public void testAnyOf() {
		assertThat("AnyOf", "myValue", anyOf(startsWithString("foo"), containsString("Val")));
	}

	@Test
	public void testEveryItem() {
		assertThat("EveryItem", asList("bar", "baz"), everyItem(startsWithString("ba")));
	}

	@Test
	public void testHasItem() {
		assertThat("HasItem", asList("foo", "bar"), hasItem(startsWithString("ba")));
	}

	@Test
	public void testEqualTo() {
		assertThat("EqualTo", "foo", equalTo("foo"));
	}

	@Test
	public void testNot() {
		assertThat("Not", "foo", not(equalTo("bar")));
	}

	@Test
	public void testSameInstance() {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj1p = obj1;
		assertThat("SameInstance", obj1p, sameInstance(obj1));
		assertThat("Not SameInstance", obj2, not(sameInstance(obj1)));
	}
	
	@Test
	public void testNullValue() {
		assertThat("NullValue", null, nullValue());
	}

	@Test
	public void testStartsWithString() {
		assertThat("StartsWithString", "myValue", startsWithString("my"));
	}

	@Test
	public void testEndsWithString() {
		assertThat("EndsWithString", "myValue", endsWithString("lue"));
	}

	@Test
	public void testContainsString() {
		assertThat("ContainsString", "myValue", containsString("Val"));
	}

	@Test
	public void testMatchesRegex() {
		assertThat("MatchesRegex", "myValue", matchesRegex("^my.*lue$"));
	}

	@Test
	public void testInstanceOf() {
		assertThat("InstanceOf", Calendar.getInstance(), instanceOf("DateTime"));
	}

	@Test
	public void testHasSize() {
		assertThat("HasSize", asList("foo", "bar"), hasSize(notNullValue(), 2));
	}

	@Test
	public void testItemAt() {
		assertThat("ItemAt", asList("foo", "bar"), itemAt(0, startsWithString("fo")));
	}

	@Test
	public void testItemForMap() {
		@SuppressWarnings("serial")
		Map<String, Integer> map = new HashMap<String, Integer>() {{
			put("one", 1);
			put("two", 2);
		}};

		assertThat("ItemForKey", map, itemForKey("one", equalTo(1)));
	}

}
