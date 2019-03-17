package de.larssh.json.dom.children;

import java.util.Map.Entry;
import java.util.Set;

import de.larssh.json.dom.values.JsonDomValue;

/**
 * JSON DOM children consisting of a key, which they are accessible through and
 * a {@link JsonDomValue} object containing the value.
 *
 * @param <T> {@link JsonDomValue} type
 */
public interface JsonDomChildren<T extends JsonDomValue<?>> {
	/**
	 * Returns a {@link Set} view of the children.
	 *
	 * @return a set view of the children
	 */
	Set<Entry<String, T>> entrySet();
}
