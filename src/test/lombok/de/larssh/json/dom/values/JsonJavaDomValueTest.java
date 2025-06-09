package de.larssh.json.dom.values;

import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import de.larssh.utils.annotations.PackagePrivate;

/**
 * Test {@link JsonJavaDomValue}
 */
@PackagePrivate
class JsonJavaDomValueTest {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	@PackagePrivate
	void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		JsonDomValueTests.test(Paths.get("data_HashMap.xml"), json -> new JsonJavaDomValue(new JSONObject(json)));
	}
}
