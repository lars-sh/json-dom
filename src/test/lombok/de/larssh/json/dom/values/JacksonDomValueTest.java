package de.larssh.json.dom.values;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.larssh.utils.annotations.PackagePrivate;

/**
 * Test {@link JacksonDomValue}
 */
@PackagePrivate
class JacksonDomValueTest {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	@PackagePrivate
	void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		JsonDomValueTests.test(json -> new JacksonDomValue(new ObjectMapper().readTree(json)));
	}
}
