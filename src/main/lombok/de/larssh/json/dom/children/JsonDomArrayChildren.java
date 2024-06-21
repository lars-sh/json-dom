package de.larssh.json.dom.children;

import static de.larssh.utils.Collectors.toLinkedHashMap;
import static java.util.function.Function.identity;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import de.larssh.json.dom.values.JsonDomValue;
import lombok.NoArgsConstructor;

/**
 * JSON DOM children of a JSON array.
 *
 * @param <T> {@link JsonDomValue} type
 */
@NoArgsConstructor
public class JsonDomArrayChildren<T extends JsonDomValue<?>> extends ArrayList<T> implements JsonDomChildren<T> {
	/**
	 * Constructor that allows adding an initial amount of children while applying
	 * {@code jsonDomValueMapper} before.
	 *
	 * @param <V>                type of children before mapping to a
	 *                           {@link JsonDomValue} type
	 * @param initialCapacity    hint on the number of children to add
	 * @param children           children to apply {@code jsonDomValueMapper} and
	 *                           add
	 * @param jsonDomValueMapper mapper from any given type to a
	 *                           {@link JsonDomValue} type
	 */
	@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
	public <V> JsonDomArrayChildren(final int initialCapacity,
			final Iterable<V> children,
			final Function<V, T> jsonDomValueMapper) {
		super(initialCapacity);

		for (final V child : children) {
			add(jsonDomValueMapper.apply(child));
		}
	}

	/** {@inheritDoc} */
	@Override
	public Set<Entry<String, T>> entrySet() {
		final AtomicInteger index = new AtomicInteger(0);
		return stream().collect(toLinkedHashMap(element -> Integer.toString(index.getAndIncrement()), identity()))
				.entrySet();
	}
}
