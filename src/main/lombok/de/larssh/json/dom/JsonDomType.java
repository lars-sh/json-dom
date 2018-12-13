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
	ARRAY("array"),

	/**
	 * JSON boolean
	 */
	BOOLEAN("boolean"),

	/**
	 * JSON null
	 */
	NULL("null"),

	/**
	 * JSON number
	 */
	NUMBER("number"),

	/**
	 * JSON object
	 */
	OBJECT("object"),

	/**
	 * JSON string
	 */
	STRING("string");

	/**
	 * String representation of the JSON type
	 *
	 * @return string representation
	 */
	String value;
}
