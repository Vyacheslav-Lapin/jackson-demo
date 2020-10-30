package ru.vlapin.demo.jacksondemo.jackson.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.vavr.CheckedFunction1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Function;
import lombok.experimental.NonFinal;
import lombok.val;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class jacksonSerializationTest {

  Function<Object, String> toXML = CheckedFunction1.of(new XmlMapper()::writeValueAsString).unchecked();

  @NonFinal
  Function<Object, String> toJSON;

  @Autowired
  private void setUp(ObjectMapper objectMapper) {
    toJSON = CheckedFunction1.of(objectMapper::writeValueAsString).unchecked();
  }

  @Test
  @DisplayName("JsonAnyGetter annotation works correctly")
  void jsonAnyGetterAnnotationWorksCorrectlyTest() {
    assertThat(toJSON.apply(
        JsonAnyGetterExampleBean.builder()
            .name("My bean")
            .property("attr1", "val1")
            .property("attr2", "val2")
            .build()))
        .isEqualToIgnoringWhitespace("""
            {
              "name":"My bean",
              "attr1":"val1",
              "attr2":"val2"
            }""");
  }

  @Test
  @DisplayName("JsonGetter annotation works correctly")
  void jsonGetterAnnotationWorksCorrectlyTest() {
    assertThat(toJSON.apply(
        JsonGetterExampleBean.builder()
            .id(1)
            .theName("My bean")
            .build()))
        .isEqualToIgnoringWhitespace("""
            {
              "id": 1,
              "name": "My bean"
            }""");
  }

  @Test
  @DisplayName("JsonPropertyOrder annotation works correctly")
  void jsonPropertyOrderAnnotationWorksCorrectlyTest() {
    assertThat(toJSON.apply(
        JsonPropertyOrderExampleBean.builder()
            .id(1)
            .name("My bean").build()))
        .isEqualToIgnoringWhitespace("""
            {
              "name": "My bean",
              "id": 1
            }""");
  }

  @Test
  @DisplayName("JsonRawValue annotation works correctly")
  void jsonRawValueAnnotationWorksCorrectlyTest() {
    assertThat(toJSON.apply(
        JsonRawValueExampleBean.builder()
            .name("My bean")
            .json("{\"attr\": false}").build()))
        .isEqualToIgnoringWhitespace("""
            {
              "name": "My bean",
              "json": {
                "attr": false
              }
            }""");
  }

  @Test
  @DisplayName("JsonValue works correctly")
  void jsonValueWorksCorrectlyTest() {
    assertThat(toJSON.apply(JsonValueExampleBean.TYPE1))
        .isEqualTo("\"Type A\"");
  }

  @Test
  @DisplayName("JsonRootName annotation works correctly")
  void jsonRootNameAnnotationWorksCorrectlyTest() {
    toJSON = CheckedFunction1.of(
        new ObjectMapper()
            .enable(SerializationFeature.WRAP_ROOT_VALUE)
            ::writeValueAsString).unchecked();

    assertThat(toJSON.apply(
        JsonRootNameExampleBean.builder()
            .id(1)
            .name("My bean").build()))
        .isEqualToIgnoringWhitespace("""
            {
              "user": {
                "id": 1,
                "name": "My bean"
              }
            }""");

    @Language("XML")
    val xml = """
        <user xmlns="users">
          <id xmlns="">1</id>
          <name xmlns="">My bean</name>
        </user>""";

    assertThat(toXML.apply(
        JsonRootNameExampleBean.builder()
            .id(1)
            .name("My bean").build()))
        .isEqualToIgnoringWhitespace(xml);
  }

  @Test
  @DisplayName("JsonSerializer annotation works correctly")
  void jsonSerializerAnnotationWorksCorrectlyTest() throws ParseException {
    val toParse = "20-12-2014 02:30:00";
    assertThat(toJSON.apply(
        JsonSerializeExampleBean.builder()
            .eventDate(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(toParse))
            .name("party").build()))
        .isEqualToIgnoringWhitespace("""
            {
              "name" : "party",
              "eventDate" : "%s"
            }""".formatted(toParse));
  }
}
