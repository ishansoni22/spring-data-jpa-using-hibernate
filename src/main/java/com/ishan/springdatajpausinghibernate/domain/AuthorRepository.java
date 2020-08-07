package com.ishan.springdatajpausinghibernate.domain;

import java.util.List;

public interface AuthorRepository {
  List<Author> getAllAuthors();

  List<Author> getAllAuthorsUsingNamedEntityGraph();

  List<Author> getAuthorByName(String name);

  List<Author> getAuthorByNameUsingNamedEntityGraph(String name);

}
