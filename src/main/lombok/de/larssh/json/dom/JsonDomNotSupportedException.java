package de.larssh.json.dom;

import org.w3c.dom.DOMException;

import lombok.ToString;

/**
 * Thrown to indicate that JSON DOM does not support a particular DOM feature.
 */
@ToString
public class JsonDomNotSupportedException extends DOMException {
	// @EqualsAndHashCode(callSuper = true, onParam_ = { @Nullable })
	private static final long serialVersionUID = 6374630768171198592L;

	/**
	 * Constructs a new {@link JsonDomNotSupportedException} with the default detail
	 * message.
	 */
	public JsonDomNotSupportedException() {
		super(DOMException.NOT_SUPPORTED_ERR, "Not supported.");
		initCause(null);
	}
}
