package ru.vlapin.demo.jacksondemo.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(staticName = "from")
public final class Dog {

  final String name;
}
