package de.larssh.json.dom;

import static de.larssh.utils.Collectors.toLinkedHashMap;
import static de.larssh.utils.Finals.constant;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.soap.Node;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

import de.larssh.json.dom.values.JsonDomValue;
import de.larssh.utils.Finals;
import de.larssh.utils.Nullables;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * JSON DOM implementation of {@link Element}.
 *
 * @param <T> implementation specific JSON element type
 */
@Getter
@EqualsAndHashCode(callSuper = true, onParam_ = { @Nullable })
public class JsonDomElement<T> extends JsonDomNode<T> implements Element {
	/**
	 * Prefix for array items node names
	 */
	public static final String ARRAY_ITEM_NODE_NAME_PREFIX = constant("item");

	/**
	 * The special value "*" matches all tags.
	 */
	private static final String GET_ELEMENTS_BY_TAG_NAME_WILDCARD = "*";

	/**
	 * Wrapped JSON element
	 *
	 * @return wrapped JSON element
	 */
	JsonDomValue<T> jsonDomValue;

	/**
	 * List of child element nodes
	 */
	Supplier<JsonDomNodeList<JsonDomElement<T>>> childNodes
			= Finals.lazy(() -> new JsonDomNodeList<>(getJsonDomValue().getChildren()
					.entrySet()
					.stream()
					.map(entry -> new JsonDomElement<>(this, entry.getKey(), entry.getValue()))
					.collect(toList())));

	/**
	 * Map of attribute names to attribute node
	 *
	 * @return map of attribute names to attribute node
	 */
	Supplier<JsonDomNamedNodeMap<JsonDomAttribute<T>>> attributes
			= Finals.lazy(() -> new JsonDomNamedNodeMap<>(singletonList(new JsonDomAttribute<>(this,
					getJsonDomValue().getType().getValue(),
					getJsonDomValue()::getTextValue)).stream()
							.collect(toLinkedHashMap(JsonDomAttribute::getNodeName, Function.identity()))));

	/**
	 * Constructor of {@link JsonDomElement}.
	 *
	 * @param parentNode   parent node
	 * @param nodeName     node name
	 * @param jsonDomValue wrapped JSON element
	 */
	public JsonDomElement(final JsonDomNode<T> parentNode, final String nodeName, final JsonDomValue<T> jsonDomValue) {
		super(parentNode, nodeName);

		this.jsonDomValue = jsonDomValue;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getAttribute(@Nullable final String name) {
		final JsonDomAttribute<T> attribute = getAttributeNode(name);
		return attribute == null ? "" : attribute.getValue();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomAttribute<T> getAttributeNode(@Nullable final String name) {
		return Nullables.orElseThrow(getAttributes()).get(name);
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomAttribute<T> getAttributeNodeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public String getAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNamedNodeMap<JsonDomAttribute<T>> getAttributes() {
		return attributes.get();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomElement<T>> getChildNodes() {
		return childNodes.get();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomElement<T>> getElementsByTagName(@Nullable final String name) {
		if (name == null) {
			return new JsonDomNodeList<>(emptyList());
		}

		final List<JsonDomElement<T>> list = new ArrayList<>();
		for (final JsonDomElement<T> child : getChildNodes()) {
			if (name.equals(child.getTagName()) || GET_ELEMENTS_BY_TAG_NAME_WILDCARD.equals(name)) {
				list.add(child);
			}
			list.addAll(child.getElementsByTagName(name));
		}
		return new JsonDomNodeList<>(list);
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomElement<T>> getElementsByTagNameNS(
			@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public T getJsonElement() {
		return getJsonDomValue().getJsonElement();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomElement<T> getNextSibling() {
		final JsonDomNode<T> parentNode = Nullables.orElseThrow(getParentNode());
		final JsonDomNodeList<JsonDomElement<T>> children = parentNode.getChildNodes();
		final int index = children.indexOf(this);
		return index + 1 < children.size() ? children.get(index + 1) : null;
	}

	/** {@inheritDoc} */
	@Override
	public short getNodeType() {
		return Node.ELEMENT_NODE;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getNodeValue() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomDocument<T> getOwnerDocument() {
		return Nullables.orElseThrow(getParentNode()).getOwnerDocument();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomElement<T> getPreviousSibling() {
		final JsonDomNode<T> parentNode = Nullables.orElseThrow(getParentNode());
		final JsonDomNodeList<JsonDomElement<T>> children = parentNode.getChildNodes();
		final int index = children.indexOf(this);
		return index > 0 ? children.get(index - 1) : null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public TypeInfo getSchemaTypeInfo() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTagName() {
		return getNodeName();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextContent() {
		return "";
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasAttribute(@Nullable final String name) {
		return Nullables.orElseThrow(getAttributes()).containsKey(name);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void removeAttribute(@Nullable @SuppressWarnings("unused") final String name) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomAttribute<T> removeAttributeNode(@Nullable @SuppressWarnings("unused") final Attr oldAttr) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void removeAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setAttribute(@Nullable @SuppressWarnings("unused") final String name,
			@Nullable @SuppressWarnings("unused") final String value) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomAttribute<T> setAttributeNode(@Nullable @SuppressWarnings("unused") final Attr newAttr) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomAttribute<T> setAttributeNodeNS(@Nullable @SuppressWarnings("unused") final Attr newAttr) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String qualifiedName,
			@Nullable @SuppressWarnings("unused") final String value) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setIdAttribute(@Nullable @SuppressWarnings("unused") final String name,
			@SuppressWarnings("unused") final boolean isId) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setIdAttributeNode(@Nullable @SuppressWarnings("unused") final Attr idAttr,
			@SuppressWarnings("unused") final boolean isId) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setIdAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName,
			@SuppressWarnings("unused") final boolean isId) {
		throw new JsonDomNotSupportedException();
	}
}
