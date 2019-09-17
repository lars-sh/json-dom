package de.larssh.json.dom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JSON data types
 */
@Getter
@RequiredArgsConstructor
public enum JsonDomType {
	/**
	 * JSON array
	 */
	ARRAY("array", true),

	/**
	 * JSON boolean
	 */
	BOOLEAN("boolean", false),

	/**
	 * JSON null
	 */
	NULL("null", false),

	/**
	 * JSON number
	 */
	NUMBER("number", false),

	/**
	 * JSON object
	 */
	OBJECT("object", true),

	/**
	 * JSON string
	 */
	STRING("string", false);

	/**
	 * String representation of the JSON type
	 *
	 * @return string representation
	 */
	String value;

	/**
	 * Tells if this type is complex. Types are complex if they might contain
	 * further elements.
	 *
	 * @return {@code true} if this is a complex type
	 */
	boolean complex;
}
