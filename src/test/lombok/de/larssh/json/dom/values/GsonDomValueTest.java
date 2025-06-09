package de.larssh.json.dom.values;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import de.larssh.utils.annotations.PackagePrivate;

/**
 * Test {@link GsonDomValue}
 */
@PackagePrivate
class GsonDomValueTest {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	@PackagePrivate
	void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		JsonDomValueTests.test(json -> new GsonDomValue(JsonParser.parseString(json)));
	}
}
