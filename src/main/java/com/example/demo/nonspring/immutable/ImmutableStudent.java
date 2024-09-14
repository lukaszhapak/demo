package com.example.demo.nonspring.immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;

// no setter

@Getter
@AllArgsConstructor
final class ImmutableStudent {

  private final String name;

  // getter for list should return immutable list or copy of that list

}
