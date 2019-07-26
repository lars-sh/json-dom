/**
 * DOM implementation for JSON
 *
 * <p>
 * While DOM is widely used for XML structured data, it can be useful for JSON
 * data, too. These classes wrap generic JSON elements to fit the DOM
 * interfaces.
 *
 * <p>
 * JSON DOM does not come with its own JSON parser or JSON element objects.
 * Instead {@link de.larssh.json.dom.values.JsonDomValue} is made up to
 * integrate existing parsers. The JSON DOM is not modifiable!
 *
 * <p>
 * <b>Model:</b> Each JSON element is represented by a DOM element. Those DOM
 * elements contain a DOM attribute describing its data type and value.
 *
 * <p>
 * The DOM elements node name depend on the elements parent.
 * <ul>
 * <li>The document elements node name is {@code root}.
 * <li>For JSON objects the node name matches the corresponding object key.
 * <li>For JSON arrays the node name equals {@code "item" + index}.
 * </ul>
 *
 * <p>
 * <b>Example:</b> While JSON DOM is not equivalent to XML, but a DOM
 * implementation, developers might be familiar with the XML DOM. Therefore the
 * following snippet demonstrates a simple JSON document represented as XML as
 * it is implemented in JSON DOM.
 * <table>
 * <tr>
 * <th>JSON</th>
 * <th>XML example</th>
 * </tr>
 * <tr>
 * <td><pre>{
 * 	"name": "Monitor",
 * 	"size": 24,
 * 	"speaker": false,
 * 	"volume": null,
 * 	"colors": ["red", "green", "blue"]
 * }</pre></td>
 * <td><pre>&lt;root object="..."&gt;
 * 	&lt;name string="Monitor" /&gt;
 * 	&lt;size number="24" /&gt;
 * 	&lt;speaker boolean="false" /&gt;
 * 	&lt;volume null="null" /&gt;
 * 	&lt;colors array="..."&gt;
 * 		&lt;item0 string="red" /&gt;
 * 		&lt;item1 string="green" /&gt;
 * 		&lt;item2 string="blue" /&gt;
 * 	&lt;/colors&gt;
 * &lt;/root&gt;</pre></td>
 * </tr>
 * </table>
 *
 * <p>
 * Note: In this example the values of {@code array} and {@code object}
 * attributes have been replaced with {@code ...} to simplify the output. The
 * value of such attributes is their JSON representation.
 */
@de.larssh.utils.annotations.NonNullByDefault
package de.larssh.json.dom;
