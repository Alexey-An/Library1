package model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "author")
@Relation(collectionRelation = "books")
public class AuthorModel extends RepresentationModel<AuthorModel> {

    private Integer authorId;
    private String authorName;
    private List<BookModel> books;

}
