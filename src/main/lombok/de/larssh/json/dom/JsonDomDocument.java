package de.larssh.json.dom;

import static de.larssh.utils.Finals.constant;
import static java.util.Collections.singletonList;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

import de.larssh.json.dom.values.JsonDomValue;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * JSON DOM implementation of {@link Document}.
 *
 * @param <T> implementation specific JSON element type
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class JsonDomDocument<T> extends JsonDomNode<T> implements Document {
	/**
	 * Node name of the document element
	 */
	public static final String DOCUMENT_ELEMENT_NODE_NAME = constant("root");

	/**
	 * Document element with node name {@code root}
	 *
	 * @return document element
	 */
	JsonDomElement<T> documentElement;

	/**
	 * Constructor of {@link JsonDomDocument}.
	 *
	 * @param jsonDomValue wrapped JSON element
	 */
	public JsonDomDocument(final JsonDomValue<T> jsonDomValue) {
		super(null, "#document");

		documentElement = new JsonDomElement<>(this, DOCUMENT_ELEMENT_NODE_NAME, jsonDomValue);
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public Node adoptNode(@Nullable @SuppressWarnings("unused") final Node source) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomAttribute<T> createAttribute(@Nullable @SuppressWarnings("unused") final String name) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomAttribute<T> createAttributeNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String qualifiedName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public CDATASection createCDATASection(@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public Comment createComment(@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public DocumentFragment createDocumentFragment() {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomElement<T> createElement(@Nullable @SuppressWarnings("unused") final String tagName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomElement<T> createElementNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String qualifiedName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public EntityReference createEntityReference(@Nullable @SuppressWarnings("unused") final String name) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public ProcessingInstruction createProcessingInstruction(@Nullable @SuppressWarnings("unused") final String target,
			@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public Text createTextNode(@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
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
	@SuppressWarnings("PMD.ReturnEmptyCollectionRatherThanNull")
	public JsonDomNodeList<JsonDomNode<T>> getChildNodes() {
		return new JsonDomNodeList<>(singletonList(getDocumentElement()));
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public DocumentType getDoctype() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getDocumentURI() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public DOMConfiguration getDomConfig() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomElement<T> getElementById(@Nullable @SuppressWarnings("unused") final String elementId) {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomElement<T>> getElementsByTagName(@Nullable final String tagName) {
		return getDocumentElement().getElementsByTagName(tagName);
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
	public DOMImplementation getImplementation() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getInputEncoding() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public T getJsonElement() {
		return getDocumentElement().getJsonElement();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomNode<T> getNextSibling() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public short getNodeType() {
		return Node.DOCUMENT_NODE;
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
		return this;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomNode<T> getPreviousSibling() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean getStrictErrorChecking() {
		return true;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getTextContent() {
		return null;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getXmlEncoding() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean getXmlStandalone() {
		return false;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public String getXmlVersion() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> importNode(@Nullable @SuppressWarnings("unused") final Node importedNode,
			@SuppressWarnings("unused") final boolean deep) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void normalizeDocument() {
		// do nothing
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNode<T> renameNode(@Nullable @SuppressWarnings("unused") final Node node,
			@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String qualifiedName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setDocumentURI(@Nullable @SuppressWarnings("unused") final String documentURI) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setStrictErrorChecking(@SuppressWarnings("unused") final boolean strictErrorChecking) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setXmlStandalone(@SuppressWarnings("unused") final boolean xmlStandalone) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void setXmlVersion(@Nullable @SuppressWarnings("unused") final String xmlVersion) {
		throw new JsonDomNotSupportedException();
	}
}
