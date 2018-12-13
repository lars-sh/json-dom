package de.larssh.json.dom;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import de.larssh.utils.dom.XPathExpressions;
import lombok.experimental.UtilityClass;

/**
 * This class contains JSON DOM related helper methods for
 * {@link XPathExpression}.
 */
@UtilityClass
public final class JsonDomXPathExpressions {
	/**
	 * Evaluates the compiled XPath expression in the specified context and
	 * optionally returns a JSON element.
	 *
	 * <p>
	 * See package {@link de.larssh.json.dom} for a description of the JSON DOM
	 * hierarchy.
	 *
	 * @param              <T> implementation specific JSON element type
	 * @param jsonDomValue the starting context
	 * @param expression   the XPath expression
	 * @return evaluation result as optional {@code T}
	 * @throws XPathExpressionException If the expression cannot be evaluated.
	 */
	public static <T> Optional<T> getJsonElement(final JsonDomValue<T> jsonDomValue, final XPathExpression expression)
			throws XPathExpressionException {
		return XPathExpressions.<JsonDomNode<T>>getNode(new JsonDomDocument<>(jsonDomValue), expression)
				.map(JsonDomNode::getJsonElement);
	}

	/**
	 * Evaluates the compiled XPath expression in the specified context and returns
	 * a list of JSON elements.
	 *
	 * <p>
	 * See package {@link de.larssh.json.dom} for a description of the JSON DOM
	 * hierarchy.
	 *
	 * @param              <T> implementation specific JSON element type
	 * @param jsonDomValue the starting context
	 * @param expression   the XPath expression
	 * @return evaluation result as list of {@code T}
	 * @throws XPathExpressionException If the expression cannot be evaluated.
	 */
	public static <T> List<T> getJsonElementList(final JsonDomValue<T> jsonDomValue, final XPathExpression expression)
			throws XPathExpressionException {
		return XPathExpressions.<JsonDomNode<T>>getNodes(new JsonDomDocument<>(jsonDomValue), expression)
				.stream()
				.map(JsonDomNode::getJsonElement)
				.collect(toList());
	}
}
