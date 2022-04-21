package Spring.test.edu.models;


import Spring.test.edu.configs.StringToEnumConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.EnumSet;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    @Column(unique = true, nullable = false)
    private Long isbn;
    @NotBlank(message = "title is mandatory")
    @Column(nullable = false)
    private String title;
    @NotBlank(message = "author is mandatory")
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Integer pageNumber;
    @Convert(converter = StringToEnumConverter.class)
    @Column
    private EnumSet<Genre> genres;
    @Column(nullable = false)
    private Date dateOfRelease;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"books", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private User user;

    public Book() {

    }

    public Book(Long isbn, String title, String author, Integer pageNumber, EnumSet<Genre> genres) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
        this.genres = genres;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public EnumSet<Genre> getGenres() {
        return genres;
    }

    public void setGenres(EnumSet<Genre> genres) {
        this.genres = genres;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public User getUser() {
        return user;
    }

    public Book setUser(User user) {
        this.user = user;
        return this;
    }

    public Book bookCompare(Book book,Book bookEdit){


        if (book.getTitle() != null) bookEdit.title = book.title;
        if (book.getAuthor() != null) bookEdit.author = book.author;
        if (book.getIsbn() != null) bookEdit.isbn = book.isbn;
        if (book.getPageNumber() != null) bookEdit.pageNumber = book.pageNumber;
        if (book.getGenres() != null) bookEdit.genres = book.genres;
        if (book.getDateOfRelease() != null) bookEdit.dateOfRelease = book.dateOfRelease;

        return bookEdit;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pageNumber=" + pageNumber +
                ", genres=" + genres +
                ", dateOfRelease=" + dateOfRelease +
                ", user=" + user +
                '}';
    }


}
