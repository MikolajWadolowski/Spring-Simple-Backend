package Spring.test.edu.services;

import Spring.test.edu.models.Book;
import Spring.test.edu.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //do zrobienia dodawanie gatunkow
    public Book createBook(Book book) {
        try {
            Book newBook = bookRepository.save(new Book(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPageNumber(), book.getGenres()));
            return newBook;
        } catch (Exception e) {
            return null;
        }
    }

    //do zrobienia edytowanie gatunkow
    public Book updateBook(long id, Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book bookEdit = bookData.get();
            if (book.getTitle() != null) bookEdit.setTitle(book.getTitle());
            if (book.getAuthor() != null) bookEdit.setAuthor(book.getAuthor());
            if (book.getIsbn() != 0) bookEdit.setIsbn(book.getIsbn());
            if (book.getPageNumber() != 0) bookEdit.setPageNumber(book.getPageNumber());
            //if(book.getGenres()!=null) bookEdit.setGenres(book.getGenres());
            return bookRepository.save(bookEdit);
        } else {
            return null;
        }
    }

    public boolean deleteBook(long id) {
        try {
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
