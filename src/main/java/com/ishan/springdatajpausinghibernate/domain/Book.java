package com.ishan.springdatajpausinghibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;

@Entity
@Getter
public class Book {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Embedded
  private BookName name;

  @JsonIgnore
  @ManyToMany(mappedBy = "books")
  private List<Author> authors;

}
