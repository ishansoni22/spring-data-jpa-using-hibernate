package com.ishan.springdatajpausinghibernate.domain;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class BookName {
  private String name;

  private BookName() {
  }

  public BookName(String name) {
    this.name = Objects.requireNonNull(name);
  }

}
