package assembler;

import controller.AuthorController;
import controller.BookController;
import entity.AuthorEntity;
import entity.BookEntity;
import model.AuthorModel;
import model.BookModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<BookEntity, BookModel> {

    public BookModelAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(BookEntity bookEntity) {
        BookModel bookModel = instantiateModel(bookEntity);

        bookModel.add(linkTo(methodOn(AuthorController.class)
                        .getAuthorById(bookEntity.getId()))
                        .withSelfRel()
                );
        bookModel.setId(bookEntity.getId());
        bookModel.setTitle(bookEntity.getTitle());
        bookModel.setAuthors(toAuthorModel(bookEntity.getAuthorsList()));
        return bookModel;
    }

    @Override
    public CollectionModel<BookModel> toCollectionModel(Iterable<? extends BookEntity> entities) {
        CollectionModel<BookModel> authorModels = super.toCollectionModel(entities);
        authorModels.add(linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
        return authorModels;
    }

    private List<AuthorModel> toAuthorModel(List<AuthorEntity> authorsList) {
        if (authorsList.isEmpty()) {
            return Collections.emptyList();
        }
        return authorsList.stream()
                .map(author -> AuthorModel.builder()
                                .authorId(author.getId())
                                .authorName(author.getAuthorName())
                        .build()
                        .add(linkTo(
                                methodOn(AuthorController.class)
                                .getAuthorById(author.getId())
                        ).withSelfRel())
                ).collect(Collectors.toList());
    }
}
