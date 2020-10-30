package ru.vlapin.demo.jacksondemo.model.jsonplaceholder;

import static lombok.AccessLevel.PRIVATE;

import lombok.Data;
import lombok.Setter;

@Data
@Setter(PRIVATE)
public class Album {

  Long userId;
  Long id;
  String title;
}
