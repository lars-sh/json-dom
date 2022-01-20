package de.larssh.json.dom.values;

import java.util.Collections;

import org.w3c.dom.DOMException;

import de.larssh.json.dom.JsonDomType;
import de.larssh.json.dom.children.JsonDomArrayChildren;
import de.larssh.json.dom.children.JsonDomChildren;
import de.larssh.json.dom.children.JsonDomObjectChildren;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.json.JsonArray;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;
import jakarta.json.JsonValue.ValueType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for JSON Processing (aka
 * <a href="https://jcp.org/en/jsr/detail?id=374">JSR-374</a>) and its
 * {@link JsonValue}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class JakartaJsonProcessingDomValue implements JsonDomValue<JsonValue> {
	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	JsonValue jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomChildren<JakartaJsonProcessingDomValue> getChildren() {
		final JsonValue value = getJsonElement();
		final ValueType valueType = value.getValueType();
		if (valueType == ValueType.ARRAY) {
			final JsonArray array = value.asJsonArray();
			return new JsonDomArrayChildren<>(array.size(), array, JakartaJsonProcessingDomValue::new);
		}
		if (valueType == ValueType.OBJECT) {
			return new JsonDomObjectChildren<>(value.asJsonObject(), JakartaJsonProcessingDomValue::new);
		}
		return Collections::emptySet;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextValue() {
		final JsonValue value = getJsonElement();
		return value.getValueType() == ValueType.STRING ? ((JsonString) value).getString() : value.toString();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressFBWarnings(value = "WEM_WEAK_EXCEPTION_MESSAGING",
			justification = "there is no more information about valueType")
	public JsonDomType getType() {
		final ValueType valueType = getJsonElement().getValueType();
		if (valueType == ValueType.ARRAY) {
			return JsonDomType.ARRAY;
		}
		if (valueType == ValueType.FALSE || valueType == ValueType.TRUE) {
			return JsonDomType.BOOLEAN;
		}
		if (valueType == ValueType.NULL) {
			return JsonDomType.NULL;
		}
		if (valueType == ValueType.NUMBER) {
			return JsonDomType.NUMBER;
		}
		if (valueType == ValueType.OBJECT) {
			return JsonDomType.OBJECT;
		}
		if (valueType == ValueType.STRING) {
			return JsonDomType.STRING;
		}
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unknown JSON node type.");
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String toString() {
		return getJsonElement().toString();
	}
}
