package de.larssh.json.dom.values;

import de.larssh.json.dom.JsonDomType;
import de.larssh.json.dom.children.JsonDomChildren;

/**
 * Generic wrapper to keep JSON implementation specifics off of JSON DOM.
 *
 * @param <T> implementation specific JSON element type
 */
public interface JsonDomValue<T> {
	/**
	 * Returns a map of child node names and their values.
	 *
	 * <p>
	 * For JSON objects and arrays a map of keys to wrapped values should be
	 * returned. For other JSON elements an empty map should be returned.
	 *
	 * @return map of child node names and their value.
	 */
	JsonDomChildren<? extends JsonDomValue<T>> getChildren();

	/**
	 * Returns the implementation specific JSON element.
	 *
	 * @return implementation specific JSON element
	 */
	T getJsonElement();

	/**
	 * Returns the text value.
	 *
	 * <p>
	 * For JSON strings this is the string value without quotes. For other JSON
	 * elements it is their JSON representation.
	 *
	 * @return text value
	 */
	String getTextValue();

	/**
	 * Returns the JSON elements value type.
	 *
	 * @return JSON data type
	 */
	JsonDomType getType();
}
