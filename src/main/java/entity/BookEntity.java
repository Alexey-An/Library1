package entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="Book")
public class BookEntity {

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookId")
    private Integer id;

    @Column(name="bookTitle")
    @Getter
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Author_Book",
            joinColumns = @JoinColumn(name="authorId"),
            inverseJoinColumns = @JoinColumn(name="bookId"))
    private List<AuthorEntity> authorsList;

}
