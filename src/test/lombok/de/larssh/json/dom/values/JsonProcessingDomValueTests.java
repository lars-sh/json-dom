package de.larssh.json.dom.values;

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;

import org.junit.jupiter.api.Test;

import de.larssh.utils.annotations.PackagePrivate;

/**
 * Test {@link JsonProcessingDomValue}
 */
@PackagePrivate
class JsonProcessingDomValueTests {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	@PackagePrivate
	@SuppressWarnings({ "checkstyle:SuppressWarnings", "resource" })
	void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		JsonDomValueTests.test(json -> {
			final JsonParser parser = Json.createParser(new StringReader(json));
			parser.next();
			return new JsonProcessingDomValue(parser.getValue());
		});
	}
}
