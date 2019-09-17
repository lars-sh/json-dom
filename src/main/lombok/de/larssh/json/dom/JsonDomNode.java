package de.larssh.json.dom;

import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JSON DOM implementation of {@link Node}.
 *
 * @param <T> implementation specific JSON element type
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onParam_ = { @Nullable })
public abstract class JsonDomNode<T> implements Node {
	/**
	 * Parent node
	 *
	 * @return parent node
	 */
	@Nullable
	JsonDomNode<T> parentNode;

	/**
	 * Node node
	 *
	 * @return node node
	 */
	String nodeName;

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> appendChild(@Nullable @SuppressWarnings("unused") final Node newChild) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> cloneNode(@SuppressWarnings("unused") final boolean deep) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public short compareDocumentPosition(@Nullable @SuppressWarnings("unused") final Node other) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public abstract JsonDomNamedNodeMap<JsonDomAttribute<T>> getAttributes();

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String getBaseURI() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public abstract JsonDomNodeList<JsonDomNode<T>> getChildNodes();

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public Object getFeature(@Nullable @SuppressWarnings("unused") final String feature,
			@Nullable @SuppressWarnings("unused") final String version) {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomNode<T> getFirstChild() {
		final JsonDomNodeList<JsonDomNode<T>> childNodes = getChildNodes();
		return childNodes.isEmpty() ? null : childNodes.get(0);
	}

	/**
	 * Returns the implementation specific JSON element.
	 *
	 * @return implementation specific JSON element
	 */
	public abstract T getJsonElement();

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomNode<T> getLastChild() {
		final JsonDomNodeList<JsonDomNode<T>> childNodes = getChildNodes();
		return childNodes.isEmpty() ? null : childNodes.get(childNodes.size() - 1);
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String getLocalName() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String getNamespaceURI() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public abstract JsonDomNode<T> getNextSibling();

	/** {@inheritDoc} */
	@NonNull
	@Override
	public abstract JsonDomDocument<T> getOwnerDocument();

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String getPrefix() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public abstract JsonDomNode<T> getPreviousSibling();

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public Object getUserData(@Nullable @SuppressWarnings("unused") final String key) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasAttributes() {
		final JsonDomNamedNodeMap<JsonDomAttribute<T>> attributes = getAttributes();
		return attributes != null && !attributes.isEmpty();
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasChildNodes() {
		return !getChildNodes().isEmpty();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> insertBefore(@Nullable @SuppressWarnings("unused") final Node newChild,
			@Nullable @SuppressWarnings("unused") final Node refChild) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isDefaultNamespace(@Nullable @SuppressWarnings("unused") final String namespaceURI) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isEqualNode(@Nullable final Node node) {
		return equals(node);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSameNode(@Nullable final Node node) {
		return equals(node);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSupported(@Nullable @SuppressWarnings("unused") final String feature,
			@Nullable @SuppressWarnings("unused") final String version) {
		return false;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String lookupNamespaceURI(@Nullable @SuppressWarnings("unused") final String prefix) {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public String lookupPrefix(@Nullable @SuppressWarnings("unused") final String namespaceURI) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
	public void normalize() {
		// do nothing
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> removeChild(@Nullable @SuppressWarnings("unused") final Node oldChild) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> replaceChild(@Nullable @SuppressWarnings("unused") final Node newChild,
			@Nullable @SuppressWarnings("unused") final Node oldChild) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setNodeValue(@Nullable @SuppressWarnings("unused") final String nodeValue) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setPrefix(@Nullable @SuppressWarnings("unused") final String prefix) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setTextContent(@Nullable @SuppressWarnings("unused") final String textContent) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public Object setUserData(@Nullable @SuppressWarnings("unused") final String key,
			@Nullable @SuppressWarnings("unused") final Object data,
			@Nullable @SuppressWarnings("unused") final UserDataHandler handler) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String toString() {
		return getJsonElement().toString();
	}
}
