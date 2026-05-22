package de.larssh.json.dom.values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.DOMException;

import de.larssh.json.dom.JsonDomType;
import de.larssh.json.dom.children.JsonDomArrayChildren;
import de.larssh.json.dom.children.JsonDomChildren;
import de.larssh.json.dom.children.JsonDomObjectChildren;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for Jettison and its
 * {@link JSONObject}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class JettisonDomValue implements JsonDomValue<Object> {
	private static boolean isNull(@Nullable final Object value) {
		return value == null || value == JSONObject.NULL || value == JSONObject.EXPLICIT_NULL;
	}

	private static List<Object> toList(final JSONArray array) {
		final int size = array.length();
		final List<Object> results = new ArrayList<>(size);
		for (int index = 0; index < size; index += 1) {
			try {
				results.add(array.get(index));
			} catch (final JSONException e) {
				final DOMException domException = new DOMException(DOMException.INDEX_SIZE_ERR,
						String.format("Failed retrieving value from JSON array at index %d.", index));
				domException.initCause(e);
				throw domException;
			}
		}
		return results;
	}

	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	Object jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	@SuppressFBWarnings(value = "ITC_INHERITANCE_TYPE_CHECKING", justification = "intended by Jettison library")
	public JsonDomChildren<JettisonDomValue> getChildren() {
		final Object value = getJsonElement();
		if (value instanceof List) {
			final List<?> list = (List<?>) value;
			return new JsonDomArrayChildren<>(list.size(), list, JettisonDomValue::new);
		}
		if (value instanceof JSONArray) {
			final JSONArray array = (JSONArray) value;
			return new JsonDomArrayChildren<>(array.length(), toList(array), JettisonDomValue::new);
		}
		if (value instanceof Map) {
			@SuppressWarnings("unchecked")
			final Map<String, ?> map = (Map<String, ?>) value;
			return new JsonDomObjectChildren<>(map, JettisonDomValue::new);
		}
		if (value instanceof JSONObject) {
			@SuppressWarnings("unchecked")
			final Map<String, ?> map = ((JSONObject) value).toMap();
			return new JsonDomObjectChildren<>(map, JettisonDomValue::new);
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
	@SuppressWarnings("PMD.CyclomaticComplexity")
	@SuppressFBWarnings(value = { "ITC_INHERITANCE_TYPE_CHECKING", "WEM_WEAK_EXCEPTION_MESSAGING" },
			justification = "intended by Jettison library; there is no additional data to provide here")
	public JsonDomType getType() {
		final Object value = getJsonElement();
		if (value instanceof List || value instanceof JSONArray) {
			return JsonDomType.ARRAY;
		}
		if (value instanceof Boolean) {
			return JsonDomType.BOOLEAN;
		}
		if (isNull(value)) {
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
		return isNull(value) ? "null" : value.toString();
	}
}
