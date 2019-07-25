package de.larssh.json.dom.values;

import java.util.Collections;

import org.w3c.dom.DOMException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

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
	public JsonDomChildren<GsonDomValue> getChildren() {
		final JsonElement element = getJsonElement();
		if (element.isJsonArray()) {
			final JsonArray array = element.getAsJsonArray();
			return new JsonDomArrayChildren<>(array.size(), array, GsonDomValue::new);
		}
		if (element.isJsonObject()) {
			return new JsonDomObjectChildren<>(element.getAsJsonObject().entrySet(), GsonDomValue::new);
		}
		return Collections::emptySet;
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
	@SuppressWarnings("PMD.CyclomaticComplexity")
	@SuppressFBWarnings(value = "WEM_WEAK_EXCEPTION_MESSAGING",
			justification = "there is no more information about element and primitive")
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
		if (!element.isJsonPrimitive()) {
			throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unknown JSON data type.");
		}

		final JsonPrimitive primitive = element.getAsJsonPrimitive();
		if (primitive.isBoolean()) {
			return JsonDomType.BOOLEAN;
		}
		if (primitive.isNumber()) {
			return JsonDomType.NUMBER;
		}
		if (primitive.isString()) {
			return JsonDomType.STRING;
		}
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unknown primitive JSON data type.");
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String toString() {
		return getJsonElement().toString();
	}
}
