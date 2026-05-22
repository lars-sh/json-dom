package de.larssh.json.dom.values;

import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Test;

import de.larssh.utils.annotations.PackagePrivate;

/**
 * Test {@link JettisonDomValue}
 */
@PackagePrivate
class JettisonDomValueTest {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	@PackagePrivate
	void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		JsonDomValueTests.test(json -> new JettisonDomValue(new JSONObject(json)));
	}
}
