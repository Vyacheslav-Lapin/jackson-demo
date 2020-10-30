package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class JsonGetterExampleBean {
  int id;

  @Getter(onMethod_ = @JsonGetter("name"))
  String theName;
}
