# JSON DOM
A DOM implementation for JSON. While DOM is widely used for XML structured data, it can be useful for JSON data, too. These classes wrap generic JSON elements to fit the DOM interfaces.

## Getting started
Here's a Maven dependency example:

	<dependency>
		<groupId>de.lars-sh</groupId>
		<artifactId>json-dom</artifactId>
		<version><!-- TODO --></version>
	</dependency>

To learn more about the JSON DOM structure check out JavaDoc of the package `de.larssh.json.dom`.

## JSON Parsers
JSON DOM does not come with its own JSON parser or JSON element objects. Instead the interface `JsonDomValue` is made up to integrate existing parsers.

### Using GSON
JSON DOM comes with a GSON implementation called `GsonDomValue`. Take a look at the following example to get a DOM Document out of a GSON JSON element.

	// The JsonElement is part of GSON
	JsonElement jsonElement = ...;
	
	// At first the GSON object need to be wrapped using its JSON DOM implementation: GsonDomValue
	GsonDomValue gsonDomValue = new GsonDomValue(jsonElement);
	
	// Finally you can either create a DOM Document out of it...
	JsonDomDocument<JsonElement> jsonDomDocument = new JsonDomDocument(gsonDomValue);
	
	// ...or even use the helper methods inside JsonDomXPathExpressions to evaluate XPathExpressions to JSON elements.
	XPathExpression xPathExpression = ...;
	JsonElement jsonElement = JsonDomXPathExpressions.getJsonElement(jsonDomDocument, xPathExpression);

Note: JSON DOM does not come with GSON dependencies itself. To use `GsonDomValue` please add GSON to your dependencies.

### Using Jackson
JSON DOM comes with a Jackson implementation called `JacksonDomValue`. Take a look at the following example coding to get a DOM Document out of a Jackson JSON node.

	// The JsonNode is part of Jackson
	JsonNode jsonNode = ...;
	
	// At first the Jackson object need to be wrapped using its JSON DOM implementation: JacksonDomValue
	JacksonDomValue jacksonDomValue = new JacksonDomValue(jsonNode);
	
	// Finally you can either create a DOM Document out of it...
	JsonDomDocument<JsonNode> jsonDomDocument = new JsonDomDocument(jacksonDomValue);
	
	// ...or even use the helper methods inside JsonDomXPathExpressions to evaluate XPathExpressions to JSON elements.
	XPathExpression xPathExpression = ...;
	JsonNode jsonNode = JsonDomXPathExpressions.getJsonElement(jsonDomDocument, xPathExpression);

Note: JSON DOM does not come with Jackson dependencies itself. To use `JacksonDomValue` please add Jackson to your dependencies.

### Using any other JSON parser
The interface `JsonDomValue` is used to wrap elements as JSON DOM compatible value. Implement it for your concerns and feel free to push your code back to this repository.

Working with your custom JSON DOM value implementation works similar to the above GSON and Jackson implementations.
