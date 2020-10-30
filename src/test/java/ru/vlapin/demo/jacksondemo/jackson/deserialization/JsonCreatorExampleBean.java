package ru.vlapin.demo.jacksondemo.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.NonFinal;

@Data
public class JsonCreatorExampleBean {
  @NonFinal int id;

  @JsonProperty("theName") @NonFinal String name;
}
