package ru.vlapin.demo.jacksondemo.jackson.deserialization;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class JacksonDeserializationTest {

  ObjectMapper objectMapper;

  @SneakyThrows
  private <T> T fromJson(Class<T> aClass, @Language("JSON") String json) {
    return objectMapper.readerFor(aClass).readValue(json);
  }

  @Test
  @DisplayName("JsonCreator annotation works correctly")
  void jsonCreatorAnnotationWorksCorrectlyTest() {
    assertThat(fromJson(
        JsonCreatorExampleBean.class,
        """
        {
          "id": 1,
          "theName": "My bean"
        }"""))
        .isEqualTo(new JsonCreatorExampleBean()
                       .setId(1)
                       .setName("My bean"));

//    LocalDate.now()
//    DateFormat
  }
}
