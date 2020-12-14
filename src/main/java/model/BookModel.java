package model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "book")
@Relation(collectionRelation = "authors")

public class BookModel extends RepresentationModel<BookModel> {

    private Integer id;
    private String title;
    private List<AuthorModel> authors;

}
