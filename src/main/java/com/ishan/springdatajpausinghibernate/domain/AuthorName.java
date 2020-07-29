package com.ishan.springdatajpausinghibernate.domain;

import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class AuthorName {
  private String firstName;
  private String lastName;

  private AuthorName() {
  }

  AuthorName(String firstName, String lastName) {
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
  }

}
