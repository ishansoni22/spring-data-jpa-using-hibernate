package com.ishan.springdatajpausinghibernate.domain;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import lombok.Getter;

@Entity
@Getter
@NamedEntityGraph(
    name = "author.books",
    attributeNodes = @NamedAttributeNode(value = "books")
)

public class Author {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Embedded
  private AuthorName authorName;

  @ManyToMany
  @JoinTable(
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  private List<Book> books;

  //Other methods!

}
