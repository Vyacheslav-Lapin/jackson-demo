package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
class JsonAnyGetterExampleBean {
  String name;

  @Singular
  @Getter(onMethod_ = @JsonAnyGetter)
  Map<String, String> properties;
}
