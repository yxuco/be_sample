package com.tibco.psg.beassert.matcher;

import java.util.List;
import java.util.Map;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class ItemMappedKeyMatcher<K,V> extends FeatureMatcher<Map<K,V>, V> {
	private K key;
	
	public ItemMappedKeyMatcher(K key, Matcher<? super V> itemMatcher) {
		super(itemMatcher, "item for key " + key, "item for key " + key);
		this.key = key;
	}

	@Override
	protected V featureValueOf(Map<K, V> actual) {
		return actual.get(key);
	}

	/**
     * Creates a matcher for {@link May}s that only matches when the item of
     * examined {@link May} for specified key is matched by the specified
     * <code>itemMatcher</code>.
     * For example:
     * <pre>assertThat(aMap, itemForKey(key, startsWithString(&quot;ba&quot;)))</pre>
     * 
	 * @param index index of the matching items
     * @param itemMatcher
     *     the matcher to apply to the item of the examined {@link List} at the index
	 */
	public static <K,V> Matcher<Map<K,V>> itemForKey(K key, final Matcher<? super V> itemMatcher) {
		return new ItemMappedKeyMatcher<K,V>(key, itemMatcher);
	}
}
