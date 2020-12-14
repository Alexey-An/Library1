package entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="authorId")
    Integer id;

    @Column(name="authorName")
    @Getter
    String authorName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authorList")
    @Getter
    private List<BookEntity> booksList;

}
