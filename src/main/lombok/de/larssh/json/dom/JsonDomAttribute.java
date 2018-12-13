package de.larssh.json.dom;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * JSON DOM implementation of {@link Attr}.
 *
 * @param <T> implementation specific JSON element type
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true, onParam_ = { @Nullable })
public class JsonDomAttribute<T> extends JsonDomNode<T> implements Attr {
	/**
	 * Supplier providing the attribute value when requested. The value is not
	 * cached.
	 *
	 * @return value provider
	 */
	Supplier<String> valueProvider;

	/**
	 * Constructor of {@link JsonDomAttribute}.
	 *
	 * @param parentNode    parent node
	 * @param nodeName      node name
	 * @param valueProvider value provider
	 */
	public JsonDomAttribute(final JsonDomElement<T> parentNode,
			final String nodeName,
			final Supplier<String> valueProvider) {
		super(parentNode, nodeName);

		this.valueProvider = valueProvider;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomNamedNodeMap<JsonDomAttribute<T>> getAttributes() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomElement<T>> getChildNodes() {
		return new JsonDomNodeList<>(Collections.emptyList());
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public T getJsonElement() {
		final JsonDomNode<T> parentNode = Objects.requireNonNull(getParentNode());
		return parentNode.getJsonElement();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getName() {
		return getNodeName();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomAttribute<T> getNextSibling() {
		final JsonDomNode<T> parentNode = Objects.requireNonNull(getParentNode());
		final JsonDomNamedNodeMap<JsonDomAttribute<T>> attributes = Objects.requireNonNull(parentNode.getAttributes());
		final Iterator<JsonDomAttribute<T>> iterator = attributes.values().iterator();

		while (iterator.next() != this) {
			// do nothing but iterating
		}
		return iterator.hasNext() ? iterator.next() : null;
	}

	/** {@inheritDoc} */
	@Override
	public short getNodeType() {
		return Node.ATTRIBUTE_NODE;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getNodeValue() {
		return getValue();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomDocument<T> getOwnerDocument() {
		final JsonDomNode<T> parentNode = Objects.requireNonNull(getParentNode());
		return parentNode.getOwnerDocument();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomElement<T> getOwnerElement() {
		return (JsonDomElement<T>) Objects.requireNonNull(getParentNode());
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomAttribute<T> getPreviousSibling() {
		JsonDomAttribute<T> previousSibling = null;
		JsonDomAttribute<T> currentSibling = null;

		final JsonDomNode<T> parentNode = Objects.requireNonNull(getParentNode());
		final JsonDomNamedNodeMap<JsonDomAttribute<T>> attributes = Objects.requireNonNull(parentNode.getAttributes());
		final Iterator<JsonDomAttribute<T>> iterator = attributes.values().iterator();

		while (currentSibling != this) {
			previousSibling = currentSibling;
			currentSibling = iterator.next();
		}
		return previousSibling;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public TypeInfo getSchemaTypeInfo() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean getSpecified() {
		return true;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextContent() {
		return getValue();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getValue() {
		return getValueProvider().get();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isId() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(@Nullable @SuppressWarnings("unused") final String value) {
		throw new UnsupportedOperationException();
	}
}
