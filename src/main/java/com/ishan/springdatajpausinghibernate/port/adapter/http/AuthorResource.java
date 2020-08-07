package com.ishan.springdatajpausinghibernate.port.adapter.http;

import com.ishan.springdatajpausinghibernate.application.AuthorApplicationService;
import com.ishan.springdatajpausinghibernate.domain.Author;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorResource {

  @Autowired
  private AuthorApplicationService authorApplicationService;

  @GetMapping("/authors")
  public ResponseEntity<List<Author>> getAuthors() {
    return
        ResponseEntity.ok(authorApplicationService.getAllAuthors());
  }

  @GetMapping("/authors/name")
  public ResponseEntity<List<Author>> getAuthorsByName(@RequestBody String name) {
    return
        ResponseEntity.ok(authorApplicationService.getAuthorsByName(name));
  }

}
