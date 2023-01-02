package de.larssh.json.dom;

import static de.larssh.utils.Collectors.toLinkedHashMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import de.larssh.utils.collection.ProxiedMap;
import de.larssh.utils.text.Strings;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * {@link java.util.Map} based {@link NamedNodeMap} implementation
 *
 * @param <E> node element type
 */
public class JsonDomNamedNodeMap<E extends Node> extends ProxiedMap<String, E> implements NamedNodeMap {
	/**
	 * Constructor to create a {@link JsonDomNamedNodeMap} based on {@code nodes}.
	 *
	 * @param nodes collections of nodes
	 */
	public JsonDomNamedNodeMap(final Collection<E> nodes) {
		super(nodes.stream().collect(toLinkedHashMap(Node::getNodeName, Function.identity())));
	}

	/** {@inheritDoc} */
	@Override
	public int getLength() {
		return size();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public E getNamedItem(@Nullable final String name) {
		return get(name);
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public E getNamedItemNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isModifiable() {
		return false;
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public E item(final int index) {
		if (index < 0) {
			throw new DOMException(DOMException.INDEX_SIZE_ERR, Strings.format("Invalid index %d.", index));
		}
		if (index >= size()) {
			return null;
		}
		final Iterator<E> iterator = values().iterator();
		for (int i = 0; i < index; i += 1) {
			iterator.next();
		}
		return iterator.next();
	}

	/** {@inheritDoc} */
	@Nullable
	@Override
	public E removeNamedItem(@Nullable final String name) {
		return remove(name);
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public E removeNamedItemNS(@Nullable @SuppressWarnings("unused") final String namespaceURI,
			@Nullable @SuppressWarnings("unused") final String localName) {
		throw new JsonDomNotSupportedException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public E setNamedItem(@Nullable @SuppressWarnings("unused") final Node node) {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public E setNamedItemNS(@Nullable @SuppressWarnings("unused") final Node node) {
		throw new JsonDomNotSupportedException();
	}
}
