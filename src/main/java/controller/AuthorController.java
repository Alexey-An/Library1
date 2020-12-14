package controller;

import assembler.AuthorModelAssembler;
import entity.AuthorEntity;
import entity.AuthorRepository;
import model.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorModelAssembler authorModelAssembler;

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> getAuthorById(@PathVariable Integer id) {

        return authorRepository.findById(id)
                .map(authorModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public String hello() {

        return "Hello World";
    }

    @GetMapping("/authors")
    public ResponseEntity<CollectionModel<AuthorModel>> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return new ResponseEntity<>(
                authorModelAssembler.toCollectionModel(authorEntities),
                HttpStatus.OK
        );
    }
}
