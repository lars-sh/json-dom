package de.larssh.json.dom;

import static java.util.Collections.emptyList;

import org.w3c.dom.Node;
import org.w3c.dom.Text;

import de.larssh.utils.Nullables;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * JSON DOM implementation of {@link Text}.
 *
 * @param <T> implementation specific JSON element type
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class JsonDomText<T> extends JsonDomNode<T> implements Text {
	/**
	 * The text data
	 *
	 * @return data
	 */
	String data;

	/**
	 * Constructor of {@link JsonDomText}.
	 *
	 * @param parentNode parent node
	 * @param data       data
	 */
	public JsonDomText(final JsonDomElement<T> parentNode, final String data) {
		super(parentNode, "#text");

		this.data = data;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	@SuppressWarnings("PMD.ReturnEmptyCollectionRatherThanNull")
	public JsonDomNamedNodeMap<JsonDomAttribute<T>> getAttributes() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public JsonDomNodeList<JsonDomNode<T>> getChildNodes() {
		return new JsonDomNodeList<>(emptyList());
	}

	/** {@inheritDoc} */
	@Override
	public T getJsonElement() {
		return Nullables.orElseThrow(getParentNode()).getJsonElement();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public JsonDomText<T> getNextSibling() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public short getNodeType() {
		return Node.TEXT_NODE;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getNodeValue() {
		return getData();
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
	public JsonDomText<T> getPreviousSibling() {
		return null;
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getTextContent() {
		return getData();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public String getData() {
		return data;
	}

	/** {@inheritDoc} */
	@Override
	public void setData(@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public int getLength() {
		return getData().length();
	}

	/** {@inheritDoc} */
	@Override
	public String substringData(final int offset, final int count) {
		return getData().substring(offset, offset + count);
	}

	/** {@inheritDoc} */
	@Override
	public void appendData(@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void insertData(@SuppressWarnings("unused") final int offset,
			@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void deleteData(@SuppressWarnings("unused") final int offset, @SuppressWarnings("unused") final int count) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public void replaceData(@SuppressWarnings("unused") final int offset,
			@SuppressWarnings("unused") final int count,
			@Nullable @SuppressWarnings("unused") final String data) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public Text splitText(@SuppressWarnings("unused") final int offset) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isElementContentWhitespace() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public String getWholeText() {
		return getData();
	}

	/** {@inheritDoc} */
	@Override
	public Text replaceWholeText(@Nullable @SuppressWarnings("unused") final String content) {
		throw new JsonDomNotSupportedException();
	}
}
