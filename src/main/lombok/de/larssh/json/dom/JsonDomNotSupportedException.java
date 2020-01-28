package de.larssh.json.dom;

import org.w3c.dom.DOMException;

/**
 * Thrown to indicate that JSON DOM does not support a particular DOM feature.
 */
public class JsonDomNotSupportedException extends DOMException {
	private static final long serialVersionUID = 3463682476298252257L;

	/**
	 * Constructs a new {@link JsonDomNotSupportedException} with the default detail
	 * message.
	 */
	public JsonDomNotSupportedException() {
		super(DOMException.NOT_SUPPORTED_ERR, "Not supported.");
		initCause(null);
	}
}
