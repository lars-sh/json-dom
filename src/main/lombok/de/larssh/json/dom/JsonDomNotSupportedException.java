package de.larssh.json.dom;

import org.w3c.dom.DOMException;

/**
 * Thrown to indicate that JSON DOM does not support a particular DOM feature.
 */
public class JsonDomNotSupportedException extends DOMException {
	/**
	 * Constructs a new {@link JsonDomNotSupportedException} with the default detail
	 * message.
	 */
	@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
	public JsonDomNotSupportedException() {
		super(NOT_SUPPORTED_ERR, "Not supported.");
		initCause(null);
	}
}
