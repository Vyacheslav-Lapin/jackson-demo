package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonRootName("")
@RequiredArgsConstructor
public enum JsonValueExampleBean {

  TYPE1(1, "Type A"),
  TYPE2(2, "Type B");

  Integer id;

  @Getter(onMethod_ = @JsonValue)
  String name;
}
