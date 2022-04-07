package Spring.test.edu.models;


import Spring.test.edu.configs.StringToEnumConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.EnumSet;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long isbn;
    @NotBlank(message = "title is mandatory")
    @Column(unique = true, nullable = false)
    private String title;
    @NotBlank(message = "author is mandatory")
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Integer pageNumber;
    @Convert(converter = StringToEnumConverter.class)
    @Column
    private EnumSet<Genre> genres;
    @Column
    private Date dateOfRelease;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Book() {

    }

    public Book(long isbn, String title, String author, int pageNumber, EnumSet<Genre> genres) {
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

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
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

    public void setUser(User user) {
        this.user = user;
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
