package de.larssh.json.dom.values;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import de.larssh.utils.annotations.PackagePrivate;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

/**
 * Test {@link JakartaJsonProcessingDomValue}
 */
@PackagePrivate
class JakartaJsonProcessingDomValueTests {
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
			return new JakartaJsonProcessingDomValue(parser.getValue());
		});
	}
}
