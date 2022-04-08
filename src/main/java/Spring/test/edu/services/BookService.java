package Spring.test.edu.services;

import Spring.test.edu.dtos.RentBookDto;
import Spring.test.edu.models.Book;
import Spring.test.edu.models.User;
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
            return bookRepository.save(book);
        } catch (Exception e) {
            return null;
        }

    }

    public Book returnBook(long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setUser(null);
            bookRepository.save(book.get());
            return book.get();
        }
        return null;
    }

    public Book rentBook(RentBookDto dto) {
        Optional<Book> book = bookRepository.findById(dto.getBookId());
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (!book.isPresent()) {
            return null;
        }
        if (!user.isPresent()) {
            book.get().setUser(null);
            return book.get();
        }
        if (book.get().getUser() == null) {
            book.get().setUser(user.get());
            user.get().getBooks().add(book.get());
            bookRepository.save(book.get());
            //userRepository.save(user.get());
            return book.get();
        } else return book.get();
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
            if (book.getDateOfRelease() != null) bookEdit.setDateOfRelease(book.getDateOfRelease());
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
