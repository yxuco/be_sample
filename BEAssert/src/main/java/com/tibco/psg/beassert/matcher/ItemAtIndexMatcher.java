package com.tibco.psg.beassert.matcher;

import java.util.List;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class ItemAtIndexMatcher<T> extends FeatureMatcher<List<? extends T>, T> {
	private final int index;
	
	public ItemAtIndexMatcher(int index, Matcher<? super T> itemMatcher) {
		super(itemMatcher, "item at index " + index, "item at index " + index);
		this.index = index; 
	}

	@Override
	protected T featureValueOf(List<? extends T> actual) {
		return actual.get(index);
	}
	
	/**
     * Creates a matcher for {@link Iterable}s that only matches when the item of
     * examined {@link List} at specified index is matched by the specified
     * <code>itemMatcher</code>.
     * For example:
     * <pre>assertThat(Arrays.asList(&quot;bar&quot;, &quot;cat&quot;), itemAt(1, startsWithString(&quot;ba&quot;)))</pre>
     * 
	 * @param index index of the matching items
     * @param itemMatcher
     *     the matcher to apply to the item of the examined {@link List} at the index
	 */
	public static <T> Matcher<List<? extends T>> itemAt(int index, final Matcher<? super T> itemMatcher) {
		return new ItemAtIndexMatcher<T>(index, itemMatcher);
	}
}
