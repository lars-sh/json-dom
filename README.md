# JSON DOM
A DOM implementation for JSON. While DOM is widely used for XML structured data, it can be useful for JSON data, too. These classes wrap generic JSON elements to fit the DOM interfaces.

## Getting started
Here's a Maven dependency example:

	<dependency>
		<groupId>de.lars-sh</groupId>
		<artifactId>json-dom</artifactId>
		<version><!-- TODO --></version>
	</dependency>

## DOM implementation for JSON
While DOM is widely used for XML structured data, it can be useful for JSON data, too. This project wraps JSON elements to fit the DOM interfaces.

JSON DOM does not come with its own JSON parser or JSON element objects. Instead it allows to plug in existing parsers. The JSON DOM is not modifiable.

### DOM Structure
Each JSON element is represented by a DOM element.

The DOM elements node name equals the JSON elements key where possible. See below for notes. The root element is always `root`.

In case of a JSON array or object the DOM element contains children, else the DOM elements value equals the JSON elements value.

Each DOM element contains the following attributes:
* the JSON elements `name`
* the JSON elements `type` (one of `array`, `boolean`, `null`, `number`, `object`, `string`)

### Node Names
While transforming a DOM to a XML representation JSON object keys are transformed to XML tag names. And while JSON object keys can contain any value XML tag names cannot.

Therefore keys that are invalid XML tag names are replaced inside JSON DOM. The attribute `name` still contains the original JSON object key.

### Example
JSON DOM does not mean converting to XML, but that might be one case. And as most developers are familiar with XML DOM already the following snippet demonstrates a simple JSON document and how it looks alike in XML notation when using JSON DOM.

#### JSON
Credits for the example goes to [the Wikipedia authors](https://en.wikipedia.org/wiki/JSON).

	{
		"firstName": "John",
		"lastName": "Smith",
		"isAlive": true,
		"age": 27,
		"address": {
			"streetAddress": "21 2nd Street",
			"city": "New York",
			"state": "NY",
			"postalCode": "10021-3100"
		},
		"phoneNumbers": [
			{
				"type": "home",
				"number": "212 555-1234"
			}, {
				"type": "office",
				"number": "646 555-4567"
			}, {
				"type": "mobile",
				"number": "123 456-7890"
			}
		],
		"children": [],
		"spouse": null
	}

#### XML (attributes hidden)

	<root>
		<firstName>John</firstName>
		<lastName>Smith</lastName>
		<isAlive>true</isAlive>
		<age>27</age>
		<address>
			<streetAddress>21 2nd Street</streetAddress>
			<city>New York</city>
			<state>NY</state>
			<postalCode>10021-3100</postalCode>
		</address>
		<phoneNumbers>
			<n0>
				<type>home</type>
				<number>212 555-1234</number>
			</n0>
			<n1>
				<type>office</type>
				<number>646 555-4567</number>
			</n1>
			<n2>
				<type>mobile</type>
				<number>123 456-7890</number>
			</n2>
		</phoneNumbers>
		<children />
		<spouse>null</spouse>
	</root>

#### XML (attributes shown)

	<root name="root" type="object">
		<firstName name="firstName" type="string">John</firstName>
		<lastName name="lastName" type="string">Smith</lastName>
		<isAlive name="isAlive" type="boolean">true</isAlive>
		<age name="age" type="number">27</age>
		<address name="address" type="object">
			<streetAddress name="streetAddress" type="string">21 2nd Street</streetAddress>
			<city name="city" type="string">New York</city>
			<state name="state" type="string">NY</state>
			<postalCode name="postalCode" type="string">10021-3100</postalCode>
		</address>
		<phoneNumbers name="phoneNumbers" type="array">
			<n0 name="0" type="object">
				<type name="type" type="string">home</type>
				<number name="number" type="string">212 555-1234</number>
			</n0>
			<n1 name="1" type="object">
				<type name="type" type="string">office</type>
				<number name="number" type="string">646 555-4567</number>
			</n1>
			<n2 name="2" type="object">
				<type name="type" type="string">mobile</type>
				<number name="number" type="string">123 456-7890</number>
			</n2>
		</phoneNumbers>
		<children name="children" type="array" />
		<spouse name="spouse" type="null">null</spouse>
	</root>

## JSON Parsers
JSON DOM does not come with its own JSON parser or JSON element objects. Instead the interface `JsonDomValue` is made up to integrate existing parsers.

### Using GSON
JSON DOM comes with a GSON implementation called `GsonDomValue`. Take a look at the following example to get a DOM Document out of a GSON JSON element.

	// The JsonElement is part of GSON
	JsonElement jsonElement = ...;
	
	// At first the GSON object needs to be wrapped using its JSON DOM implementation: GsonDomValue
	GsonDomValue gsonDomValue = new GsonDomValue(jsonElement);
	
	// Finally you can either create a DOM Document out of it...
	JsonDomDocument<JsonElement> jsonDomDocument = new JsonDomDocument(gsonDomValue);
	
	// ...or even use the helper methods inside JsonDomXPathExpressions to evaluate XPath expressions to JSON elements.
	XPathExpression xPathExpression = ...;
	JsonElement jsonElement = JsonDomXPathExpressions.getJsonElement(jsonDomDocument, xPathExpression);

Note: JSON DOM does not come with GSON dependencies itself. To use `GsonDomValue` please add GSON to your dependencies.

### Using Jackson
JSON DOM comes with a Jackson implementation called `JacksonDomValue`. Take a look at the following example coding to get a DOM Document out of a Jackson JSON node.

	// The JsonNode is part of Jackson
	JsonNode jsonNode = ...;
	
	// At first the Jackson object needs to be wrapped using its JSON DOM implementation: JacksonDomValue
	JacksonDomValue jacksonDomValue = new JacksonDomValue(jsonNode);
	
	// Finally you can either create a DOM Document out of it...
	JsonDomDocument<JsonNode> jsonDomDocument = new JsonDomDocument(jacksonDomValue);
	
	// ...or even use the helper methods inside JsonDomXPathExpressions to evaluate XPath expressions to JSON elements.
	XPathExpression xPathExpression = ...;
	JsonNode jsonNode = JsonDomXPathExpressions.getJsonElement(jsonDomDocument, xPathExpression);

Note: JSON DOM does not come with Jackson dependencies itself. To use `JacksonDomValue` please add Jackson to your dependencies.

### Using JSON Processing ([aka JSR-374](https://jcp.org/en/jsr/detail?id=374))
JSON DOM comes with a JSON Processing implementation called `JsonProcessingDomValue`. Take a look at the following example coding to get a DOM Document out of a JSON Processing value.

	// The JsonValue is part of JSON Processing
	JsonValue jsonValue = ...;
	
	// At first the JSON Processing object needs to be wrapped using its JSON DOM implementation: JsonProcessingDomValue
	JsonProcessingDomValue jsonProcessingDomValue = new JsonProcessingDomValue(jsonValue);
	
	// Finally you can either create a DOM Document out of it...
	JsonDomDocument<JsonValue> jsonDomDocument = new JsonDomDocument(jsonProcessingDomValue);
	
	// ...or even use the helper methods inside JsonDomXPathExpressions to evaluate XPath expressions to JSON elements.
	XPathExpression xPathExpression = ...;
	JsonValue jsonValue = JsonDomXPathExpressions.getJsonElement(jsonDomDocument, xPathExpression);

Note: JSON DOM does not come with a JSR-374 implementation. To use `JsonProcessingDomValue` please add an implementation to your dependencies.

### Using any other JSON parser
The interface `JsonDomValue` is used to wrap elements as JSON DOM compatible value. Implement it for your concerns and feel free to push your code back to this repository.

Working with your custom JSON DOM value implementation works similar to the above implementations.

## Quick and Dirty
The following code snippets might help to either get started with JSON DOM.

### Parse JSON with Jackson

	JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

### Parse JSON with GSON

	JsonElement jsonElement = new JsonParser().parse(jsonString);

### Parse JSON with JSON-P

	final JsonParser parser = Json.createParser(new StringReader(jsonString));
	parser.next();
	final JsonValue jsonValue = parser.getValue();

### Transform DOM to XML

	static String transformDomToXml(final Node node) throws TransformerException {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 4);
		transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	
		final Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	
		final StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(node), new StreamResult(writer));
		return writer.toString();
	}
