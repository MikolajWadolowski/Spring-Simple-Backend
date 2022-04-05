package Spring.test.edu.models;

import javax.persistence.*;
import java.util.Date;
import java.util.EnumSet;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,nullable = false)
    private long isbn;
    @Column(unique = true,nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int pageNumber;
    @Column
    private EnumSet<Genre> genres;
    @Column
    private Date rentalFromDate;
    @Column
    private Date rentalToDate;

    public Book(){

    }
    public Book(long isbn, String title, String author, int pageNumber,EnumSet<Genre> genres){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pageNumber = pageNumber;
        this.genres = genres;
    }

    public long getIsbn() {
        return isbn;
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

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public Date getRentalFromDate() {
        return rentalFromDate;
    }

    public void setRentalFromDate(Date rentalFromDate) {
        this.rentalFromDate = rentalFromDate;
    }

    public Date getRentalToDate() {
        return rentalToDate;
    }

    public void setRentalToDate(Date rentalToDate) {
        this.rentalToDate = rentalToDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pageNumber=" + pageNumber +
                ", genres=" + genres +
                ", rentalFromDate=" + rentalFromDate +
                ", rentalToDate=" + rentalToDate +
                '}';
    }
}
