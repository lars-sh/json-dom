package de.larssh.json.dom;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.DOMException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of {@link JsonDomValue} for {@link com.fasterxml.jackson} and
 * its {@link JsonNode}.
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onParam_ = { @Nullable })
public class JacksonDomValue implements JsonDomValue<JsonNode> {
	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	JsonNode jsonElement;

	/** {@inheritDoc} */
	@NonNull
	@Override
	public Map<String, JacksonDomValue> getChildren() {
		final Map<String, JacksonDomValue> children = new LinkedHashMap<>();
		final JsonNode node = getJsonElement();
		if (node.isArray()) {
			int index = 0;
			for (final JsonNode child : node) {
				children.put(JsonDomElement.ARRAY_ITEM_NODE_NAME_PREFIX + Integer.toString(index),
						new JacksonDomValue(child));
				index += 1;
			}
		} else if (node.isObject()) {
			node.fields()
					.forEachRemaining(entry -> children.put(entry.getKey(), new JacksonDomValue(entry.getValue())));
		}
		return children;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextValue() {
		final JsonNode node = getJsonElement();
		return node.isTextual() ? node.asText() : node.toString();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomType getType() {
		final JsonNodeType nodeType = getJsonElement().getNodeType();
		if (nodeType == JsonNodeType.ARRAY) {
			return JsonDomType.ARRAY;
		}
		if (nodeType == JsonNodeType.BOOLEAN) {
			return JsonDomType.BOOLEAN;
		}
		if (nodeType == JsonNodeType.NULL) {
			return JsonDomType.NULL;
		}
		if (nodeType == JsonNodeType.NUMBER) {
			return JsonDomType.NUMBER;
		}
		if (nodeType == JsonNodeType.OBJECT) {
			return JsonDomType.OBJECT;
		}
		if (nodeType == JsonNodeType.STRING) {
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
