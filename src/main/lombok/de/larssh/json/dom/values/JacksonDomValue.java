package de.larssh.json.dom.values;

import java.util.Collections;

import org.w3c.dom.DOMException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

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
 * Implementation of {@link JsonDomValue} for
 * <a href="https://github.com/FasterXML/jackson">Jackson</a> and its
 * {@link JsonNode}.
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
	public JsonDomChildren<JacksonDomValue> getChildren() {
		final JsonNode node = getJsonElement();
		if (node.isArray()) {
			return new JsonDomArrayChildren<>(node.size(), node, JacksonDomValue::new);
		}
		if (node.isObject()) {
			return new JsonDomObjectChildren<>(node.fields(), JacksonDomValue::new);
		}
		return Collections::emptySet;
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
	@SuppressFBWarnings(value = "WEM_WEAK_EXCEPTION_MESSAGING",
			justification = "there is no more information about nodeType")
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
