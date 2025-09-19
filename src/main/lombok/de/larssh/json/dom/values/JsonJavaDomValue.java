package de.larssh.json.dom.values;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.DOMException;

import de.larssh.json.dom.JsonDomType;
import de.larssh.json.dom.children.JsonDomArrayChildren;
import de.larssh.json.dom.children.JsonDomChildren;
import de.larssh.json.dom.children.JsonDomObjectChildren;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for JSON-Java (org.json) and its
 * {@link JSONObject}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class JsonJavaDomValue implements JsonDomValue<Object> {
	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	Object jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressFBWarnings(value = "ITC_INHERITANCE_TYPE_CHECKING", justification = "intended by JSON-Java library")
	public JsonDomChildren<JsonJavaDomValue> getChildren() {
		final Object value = getJsonElement();
		if (value instanceof List) {
			final List<?> list = (List<?>) value;
			return new JsonDomArrayChildren<>(list.size(), list, JsonJavaDomValue::new);
		}
		if (value instanceof JSONArray) {
			final JSONArray array = (JSONArray) value;
			return new JsonDomArrayChildren<>(array.length(), array.toList(), JsonJavaDomValue::new);
		}
		if (value instanceof Map) {
			@SuppressWarnings("unchecked")
			final Map<String, ?> map = (Map<String, ?>) value;
			return new JsonDomObjectChildren<>(map, JsonJavaDomValue::new);
		}
		if (value instanceof JSONObject) {
			final JSONObject object = (JSONObject) value;
			return new JsonDomObjectChildren<>(object.toMap(), JsonJavaDomValue::new);
		}
		return Collections::emptySet;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextValue() {
		return toString();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressWarnings({ "null", "PMD.CyclomaticComplexity" })
	@SuppressFBWarnings(value = { "ITC_INHERITANCE_TYPE_CHECKING", "WEM_WEAK_EXCEPTION_MESSAGING" },
			justification = "intended by JSON-Java library; there is no additional data to provide here")
	public JsonDomType getType() {
		final Object value = getJsonElement();
		if (value instanceof List || value instanceof JSONArray) {
			return JsonDomType.ARRAY;
		}
		if (value instanceof Boolean) {
			return JsonDomType.BOOLEAN;
		}
		if (value == null || value == JSONObject.NULL) {
			return JsonDomType.NULL;
		}
		if (value instanceof Number) {
			return JsonDomType.NUMBER;
		}
		if (value instanceof Map || value instanceof JSONObject) {
			return JsonDomType.OBJECT;
		}
		if (value instanceof String) {
			return JsonDomType.STRING;
		}
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unknown JSON node type.");
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressWarnings("null")
	public String toString() {
		final Object value = getJsonElement();
		return value == null || value == JSONObject.NULL ? "null" : value.toString();
	}
}
