package de.larssh.json.dom;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.w3c.dom.DOMException;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for JSON Processing (aka
 * <a href="https://jcp.org/en/jsr/detail?id=353">JSR-353</a>) and its
 * {@link JsonValue}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onParam_ = { @Nullable })
public class JsonProcessingDomValue implements JsonDomValue<JsonValue> {
	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	JsonValue jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressFBWarnings("STT_TOSTRING_MAP_KEYING")
	public Map<String, JsonProcessingDomValue> getChildren() {
		final Map<String, JsonProcessingDomValue> children = new LinkedHashMap<>();
		final JsonValue value = getJsonElement();
		final ValueType valueType = value.getValueType();
		if (valueType == ValueType.ARRAY) {
			int index = 0;
			for (final JsonValue child : value.asJsonArray()) {
				children.put(JsonDomElement.ARRAY_ITEM_NODE_NAME_PREFIX + Integer.toString(index),
						new JsonProcessingDomValue(child));
				index += 1;
			}
		} else if (valueType == ValueType.OBJECT) {
			value.asJsonObject().forEach((key, child) -> children.put(key, new JsonProcessingDomValue(child)));
		}
		return children;
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
