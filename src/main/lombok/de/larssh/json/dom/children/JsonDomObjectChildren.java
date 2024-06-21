package de.larssh.json.dom.children;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import de.larssh.json.dom.values.JsonDomValue;

/**
 * JSON DOM children of a JSON object.
 *
 * @param <T> {@link JsonDomValue} type
 */
public class JsonDomObjectChildren<T extends JsonDomValue<?>> extends LinkedHashMap<String, T>
		implements JsonDomChildren<T> {
	/**
	 * Constructor that allows adding children while applying
	 * {@code jsonDomValueMapper} to each child's value before.
	 *
	 * @param <V>                type of children before mapping to a
	 *                           {@link JsonDomValue} type
	 * @param children           children to apply {@code jsonDomValueMapper} and
	 *                           add
	 * @param jsonDomValueMapper mapper from any given type to a
	 *                           {@link JsonDomValue} type
	 */
	public <V> JsonDomObjectChildren(final Map<String, V> children, final Function<V, T> jsonDomValueMapper) {
		this(children.entrySet(), jsonDomValueMapper);
	}

	/**
	 * Constructor that allows adding children while applying
	 * {@code jsonDomValueMapper} to each child's value before.
	 *
	 * @param <V>                type of children before mapping to a
	 *                           {@link JsonDomValue} type
	 * @param children           children to apply {@code jsonDomValueMapper} and
	 *                           add
	 * @param jsonDomValueMapper mapper from any given type to a
	 *                           {@link JsonDomValue} type
	 */
	public <V> JsonDomObjectChildren(final Set<Entry<String, V>> children, final Function<V, T> jsonDomValueMapper) {
		this(children.iterator(), jsonDomValueMapper);
	}

	/**
	 * Constructor that allows adding children while applying
	 * {@code jsonDomValueMapper} to each child's value before.
	 *
	 * @param <V>                type of children before mapping to a
	 *                           {@link JsonDomValue} type
	 * @param children           children to apply {@code jsonDomValueMapper} and
	 *                           add
	 * @param jsonDomValueMapper mapper from any given type to a
	 *                           {@link JsonDomValue} type
	 */
	@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
	public <V> JsonDomObjectChildren(final Iterator<Entry<String, V>> children,
			final Function<V, T> jsonDomValueMapper) {
		while (children.hasNext()) {
			final Entry<String, V> entry = children.next();
			put(entry.getKey(), jsonDomValueMapper.apply(entry.getValue()));
		}
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("PMD.UselessOverridingMethod")
	public Set<Entry<String, T>> entrySet() {
		// http://fb-contrib.sourceforge.net/bugdescriptions.html#SCII_SPOILED_CHILD_INTERFACE_IMPLEMENTOR
		return super.entrySet();
	}
}
