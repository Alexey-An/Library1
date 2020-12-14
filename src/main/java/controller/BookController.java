package controller;

import assembler.BookModelAssembler;
import entity.BookEntity;
import entity.BookRepository;
import model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookModelAssembler bookModelAssembler;

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Integer id){
        return bookRepository.findById(id)
                .map(bookModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/books")
    public ResponseEntity<CollectionModel<BookModel>> getAllBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        return new ResponseEntity<>(
                bookModelAssembler.toCollectionModel(bookEntities),
                HttpStatus.OK
        );
    }
}
