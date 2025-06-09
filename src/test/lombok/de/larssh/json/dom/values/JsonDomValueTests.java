package de.larssh.json.dom.values;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.larssh.json.dom.JsonDomDocument;
import de.larssh.utils.Nullables;
import de.larssh.utils.function.ThrowingFunction;
import de.larssh.utils.io.Resources;
import lombok.experimental.UtilityClass;

/**
 * Unified test for {@link JsonDomValue} implementations
 */
@UtilityClass
public class JsonDomValueTests {
	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @param jsonParser a function consuming a JSON string and returning a proper
	 *                   {@link JsonDomValue} representation
	 * @throws Exception on error
	 */
	public static void test(final ThrowingFunction<String, JsonDomValue<?>> jsonParser) throws Exception {
		test(Paths.get("data.xml"), jsonParser);
	}

	/**
	 * Tests reading JSON and converting it to XML.
	 *
	 * @param expectedXmlFile path to the file containing the expected XML output
	 * @param jsonParser      a function consuming a JSON string and returning a
	 *                        proper {@link JsonDomValue} representation
	 * @throws Exception on error
	 */
	public static void test(final Path expectedXmlFile, final ThrowingFunction<String, JsonDomValue<?>> jsonParser)
			throws Exception {
		// given
		final String json = new String(
				Files.readAllBytes(
						Resources.getResourceRelativeTo(JsonDomValueTests.class, Paths.get("data.json")).get()),
				StandardCharsets.UTF_8);

		final String expectedXml = new String(
				Files.readAllBytes(Resources.getResourceRelativeTo(JsonDomValueTests.class, expectedXmlFile).get()),
				StandardCharsets.UTF_8);

		// when
		final JsonDomDocument<?> document
				= new JsonDomDocument<>(Nullables.orElseThrow(jsonParser.applyThrowing(json)));
		final Writer writer = new StringWriter();
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(writer));
		final String actualXml = writer.toString();

		// then
		assertThat(actualXml).isEqualTo(expectedXml);
	}
}
