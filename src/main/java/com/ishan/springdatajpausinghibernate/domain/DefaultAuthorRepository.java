package com.ishan.springdatajpausinghibernate.domain;

import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultAuthorRepository implements AuthorRepository {

  @PersistenceContext
  private EntityManager em;

  /**
   * Re-create the N+1 select issue Lazy fetching of related entities creates too many queries
   */
  @Override
  public List<Author> getAllAuthors() {
    Query query = em.createQuery("select a from Author a");
    // N+1 select issue
    List<Author> authors = query.getResultList();
    return authors;
  }

  /**
   * Use NamedEntityGraphs to solve the issue
   */
  @Override
  public List<Author> getAllAuthorsUsingNamedEntityGraph() {
    EntityGraph<?> graph = em.getEntityGraph("author.books");
    Query query =
        em.createQuery("select a from Author a").setHint("javax.persistence.loadgraph", graph);

    // Only 1 query will be fired to get all authors & their books!
    List<Author> authors = query.getResultList();
    return authors;
  }

  @Override
  public List<Author> getAuthorByName(String name) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Author> query = builder.createQuery(Author.class);
    Root<Author> author = query.from(Author.class);

    Predicate firstNamePredicate = builder.equal(author.get("authorName").get("firstName"), name);
    Predicate lastNamePredicate = builder.equal(author.get("authorName").get("lastName"), name);

    Predicate namePredicate = builder.or(firstNamePredicate, lastNamePredicate);

    query.where(namePredicate);
    TypedQuery<Author> typedQuery = em.createQuery(query);

    return typedQuery.getResultList();
  }

  @Override
  public List<Author> getAuthorByNameUsingNamedEntityGraph(String name) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Author> query = builder.createQuery(Author.class);
    Root<Author> author = query.from(Author.class);

    Predicate firstNamePredicate = builder.equal(author.get("authorName").get("firstName"), name);
    Predicate lastNamePredicate = builder.equal(author.get("authorName").get("lastName"), name);

    Predicate namePredicate = builder.or(firstNamePredicate, lastNamePredicate);

    query.where(namePredicate);

    EntityGraph<?> graph = em.getEntityGraph("author.books");
    TypedQuery<Author> typedQuery =
        em.createQuery(query)
            .setHint("javax.persistence.loadgraph", graph);

    return typedQuery.getResultList();
  }

}
