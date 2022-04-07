package Spring.test.edu.services;

import Spring.test.edu.models.Book;
import Spring.test.edu.repositories.BookRepository;
import Spring.test.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;


    public List<Book> getAllBooks() {
        Optional<Book> book = bookRepository.findById(27l);
        //  System.out.println(book.get().getGenres());
        return bookRepository.findAll();

    }

    public Book getBookById(long id) {

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else return null;
    }

    public Book createBook(Book book) {
        try {
            System.out.println(book);
            bookRepository.save(book);
            return book;
        } catch (Exception e) {
            System.out.println(e);
        }
        return book;
    }

    public Book updateBook(long id, Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book bookEdit = bookData.get();
            if (book.getTitle() != null) bookEdit.setTitle(book.getTitle());
            if (book.getAuthor() != null) bookEdit.setAuthor(book.getAuthor());
            if (book.getIsbn() != 0) bookEdit.setIsbn(book.getIsbn());
            if (book.getPageNumber() != 0) bookEdit.setPageNumber(book.getPageNumber());
            if (book.getGenres() != null) bookEdit.setGenres(book.getGenres());
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
