package com.ishan.springdatajpausinghibernate.application;

import com.ishan.springdatajpausinghibernate.domain.Author;
import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorApplicationService {

  @PersistenceContext
  private EntityManager em;

  @Transactional
  /**
   * Re-create the N+1 select issue
   * Lazy fetching of related entities creates too many queries
   *
   */
  public List<Author> getAllAuthors() {
    Query query = em
        .createQuery("select a from Author a");
    //N+1 select issue
    List<Author> authors = query.getResultList();
    return authors;
  }

  @Transactional
  /**
   * Use NamedEntityGraphs to solve the issue
   */
  public List<Author> getAllAuthorsUsingNamedEntityGraph() {
    EntityGraph<?> graph = em.getEntityGraph("author.books");
    Query query = em
        .createQuery("select a from Author a")
        .setHint("javax.persistence.loadgraph", graph);

    //Only 1 query will be fired to get all authors & their books!
    List<Author> authors = query.getResultList();
    return authors;
  }

}