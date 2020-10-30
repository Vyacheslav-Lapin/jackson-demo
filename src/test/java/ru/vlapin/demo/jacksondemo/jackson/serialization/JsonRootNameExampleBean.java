package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonRootName(value = "user", namespace = "users")
public class JsonRootNameExampleBean {

  int id;
  String name;
}
