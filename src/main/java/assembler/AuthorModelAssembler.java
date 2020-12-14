package assembler;

import controller.AuthorController;
import controller.BookController;
import entity.AuthorEntity;
import entity.BookEntity;
import model.AuthorModel;
import model.BookModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class AuthorModelAssembler extends RepresentationModelAssemblerSupport<AuthorEntity, AuthorModel> {

    public AuthorModelAssembler() {
        super(AuthorController.class, AuthorModel.class);
    }

    @Override
    public AuthorModel toModel(AuthorEntity authorEntity) {
        AuthorModel authorModel = instantiateModel(authorEntity);

        authorModel.add(
                linkTo(methodOn(AuthorController.class)
                        .getAuthorById(authorEntity.getId())
                ).withSelfRel());

        authorModel.setAuthorId(authorEntity.getId());
        authorModel.setAuthorName(authorEntity.getAuthorName());
        authorModel.setBooks(toBookModel(authorEntity.getBooksList()));
        return authorModel;
    }

    @Override
    public CollectionModel<AuthorModel> toCollectionModel(Iterable<? extends AuthorEntity> authorEntities) {
        CollectionModel<AuthorModel> authorModels = super.toCollectionModel(authorEntities);
        authorModels.add(linkTo(methodOn(AuthorController.class).getAllAuthors()).withSelfRel());
        return authorModels;
    }

    private List<BookModel> toBookModel(List<BookEntity> bookEntityList) {
        if (bookEntityList.isEmpty()) {
            return Collections.emptyList();
        }

        return bookEntityList.stream()
                .map(bookEntity -> BookModel.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .build()
                .add(linkTo(methodOn(BookController.class)
                .getBookById(bookEntity.getId()))
                .withSelfRel())).collect(Collectors.toList());
    }
}
