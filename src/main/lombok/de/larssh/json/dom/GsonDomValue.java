package de.larssh.json.dom;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.DOMException;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for {@link com.google.gson.Gson} and
 * its {@link JsonElement}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onParam_ = { @Nullable })
public class GsonDomValue implements JsonDomValue<JsonElement> {
	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	JsonElement jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	public Map<String, GsonDomValue> getChildren() {
		final Map<String, GsonDomValue> children = new LinkedHashMap<>();
		final JsonElement element = getJsonElement();
		if (element.isJsonArray()) {
			int index = 0;
			for (final JsonElement child : element.getAsJsonArray()) {
				children.put(JsonDomElement.ARRAY_ITEM_NODE_NAME_PREFIX + Integer.toString(index),
						new GsonDomValue(child));
				index += 1;
			}
		} else if (element.isJsonObject()) {
			for (final Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
				children.put(entry.getKey(), new GsonDomValue(entry.getValue()));
			}
		}
		return children;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextValue() {
		final JsonElement element = getJsonElement();
		return element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()
				? element.getAsString()
				: element.toString();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomType getType() {
		final JsonElement element = getJsonElement();
		if (element.isJsonArray()) {
			return JsonDomType.ARRAY;
		}
		if (element.isJsonNull()) {
			return JsonDomType.NULL;
		}
		if (element.isJsonObject()) {
			return JsonDomType.OBJECT;
		}

		final JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();
		if (jsonPrimitive.isBoolean()) {
			return JsonDomType.BOOLEAN;
		}
		if (jsonPrimitive.isNumber()) {
			return JsonDomType.NUMBER;
		}
		if (jsonPrimitive.isString()) {
			return JsonDomType.STRING;
		}

		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unknown JSON data type.");
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String toString() {
		return getJsonElement().toString();
	}
}
