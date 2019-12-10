package de.larssh.json.dom;

import static java.util.Collections.unmodifiableList;

import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.larssh.utils.Nullables;
import de.larssh.utils.collection.ProxiedList;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * {@link List} based {@link NodeList} implementation
 *
 * @param <E> node element type
 */
public class JsonDomNodeList<E extends Node> extends ProxiedList<E> implements NodeList {
	/**
	 * Constructor to create a {@link JsonDomNodeList} based on {@code list}.
	 *
	 * @param list list of nodes
	 */
	public JsonDomNodeList(final List<E> list) {
		super(unmodifiableList(list));
	}

	/** {@inheritDoc} */
	@Override
	public int getLength() {
		return size();
	}

	/** {@inheritDoc} */
	@NonNull
	@Override
	public E item(final int index) {
		return Nullables.orElseThrow(get(index));
	}
}
