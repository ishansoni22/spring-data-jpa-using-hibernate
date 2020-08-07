package com.ishan.springdatajpausinghibernate.application;

import com.ishan.springdatajpausinghibernate.domain.Author;
import com.ishan.springdatajpausinghibernate.domain.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorApplicationService {

  @Autowired
  private AuthorRepository authorRepository;

  @Transactional
  public List<Author> getAllAuthors() {
    return authorRepository.getAllAuthors();
  }

  @Transactional
  public List<Author> getAllAuthorsUsingNamedEntityGraph() {
    return authorRepository.getAllAuthorsUsingNamedEntityGraph();
  }

  @Transactional
  public List<Author> getAuthorsByName(String name) {
    return authorRepository.getAuthorByNameUsingNamedEntityGraph(name);
  }

}
