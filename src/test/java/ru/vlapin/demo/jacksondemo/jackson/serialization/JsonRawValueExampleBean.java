package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class JsonRawValueExampleBean {

  String name;

  @JsonRawValue
  String json;
}
