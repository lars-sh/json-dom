package de.larssh.json.dom.values;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.larssh.json.dom.JsonDomDocument;
import de.larssh.utils.io.Resources;

/**
 * Test {@link JacksonDomValue}
 */
public class JacksonDomValueTest {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @throws Exception on error
	 */
	@Test
	public void shouldReturnXml_whenConvertJsonToXml_givenJson() throws Exception {
		// given
		final String json = new String(
				Files.readAllBytes(Resources.getResourceRelativeTo(getClass(), Paths.get("data.json")).get()),
				StandardCharsets.UTF_8);

		final String expectedXml = new String(
				Files.readAllBytes(Resources.getResourceRelativeTo(getClass(), Paths.get("data.xml")).get()),
				StandardCharsets.UTF_8);

		// when
		final JsonNode jsonNode = new ObjectMapper().readTree(json);
		final Document document = new JsonDomDocument<>(new JacksonDomValue(jsonNode));
		final Writer writer = new StringWriter();
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(writer));
		final String actualXml = writer.toString();

		// then
		assertThat(actualXml).isEqualTo(expectedXml);
	}
}
