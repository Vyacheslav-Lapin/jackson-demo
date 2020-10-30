package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"name", "id"})
public final class JsonPropertyOrderExampleBean {
  int id;
  String name;
}
