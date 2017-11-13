package com.tibco.psg.beassert.matcher;

import java.util.ArrayList;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import com.tibco.cep.kernel.model.entity.Entity;
import com.tibco.cep.runtime.model.element.Concept;
import com.tibco.cep.runtime.model.element.Property;
import com.tibco.cep.runtime.model.element.PropertyArray;
import com.tibco.cep.runtime.model.element.PropertyAtom;
import com.tibco.cep.runtime.model.event.SimpleEvent;

public class EntityPropertyMatcher extends FeatureMatcher<Entity, Object> {
	private final String propertyName;
	
	public EntityPropertyMatcher(String propertyName, Matcher<Object> propertyMatcher) {
		super(propertyMatcher, "property " + propertyName, "property " + propertyName);
		this.propertyName = propertyName;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object featureValueOf(Entity actual) {
		try {
			if (actual instanceof Concept) {
				Concept obj = (Concept) actual;
				Property prop = obj.getProperty(propertyName);
				if (prop instanceof PropertyArray) {
					// list of array properties, including primitives, contained or referenced concepts
					ArrayList list = new ArrayList();
					for (PropertyAtom item : ((PropertyArray) prop).toArray()) {
						list.add(item.getValue());
					}
					return list;
				}
				else if (prop instanceof PropertyAtom) {
					return ((PropertyAtom) prop).getValue();
				}
				else {
					// should not be here
					return prop;
				}
			} else if (actual instanceof SimpleEvent) {
				SimpleEvent evt = (SimpleEvent) actual;
				if ("@payload".equals(propertyName)) {
					// event payload string
					return evt.getPayloadAsString();
				}
				else {
					// event properties are all primitive values
					return evt.getPropertyValue(propertyName);
				}
			}
			else {
				throw new RuntimeException("Invalid object type " + actual.getClass().getName());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
     * Creates a matcher for BE entity that matches when the value of a specified property
     * is matched by <code>propertyMatcher</code>.
     * For example:
     * <pre>assertThat(aConcept, withProperty("myStringProp", endsWithString("o")))</pre>
     * 
	 * @param propertyName
	 *     the name of the property in the examined entity
	 * @param propertyMatcher
	 *     the matcher to apply to the specified property of the examined entity
	 */
    public static Matcher<?> withProperty(String propertyName, Matcher<Object> propertyMatcher) {
        return new EntityPropertyMatcher(propertyName, propertyMatcher);
    }

	/**
     * Creates a matcher for BE event that matches when the payload
     * is matched by <code>payloadMatcher</code>.
     * For example:
     * <pre>assertThat(aConcept, withPayload(containsString("xml")))</pre>
     * 
	 * @param payloadMatcher
	 *     the matcher to apply to the payload of the examined event
	 */
    public static Matcher<?> withPayload(Matcher<Object> payloadMatcher) {
        return new EntityPropertyMatcher("@payload", payloadMatcher);
    }
}
