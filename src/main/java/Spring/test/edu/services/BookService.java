package Spring.test.edu.services;

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
        return bookRepository.findById(id)
                .orElse(null);
    }

    public Book createBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            return null;
        }
    }

    public Book returnBook(long id) {
        return bookRepository.findById(id)
                .map(book -> book.setUser(null))
                .map(book -> bookRepository.save(book))
                .orElse(null);
    }

    public RentBookResponse rentBook(long bookId, long userId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<User> user = userRepository.findById(userId);
        if (!book.isPresent()) {
            return RentBookResponse.buildFailure("Book not found");
        } else if (!user.isPresent()) {
            return RentBookResponse.buildFailure("User not found");
        } else if (book.get().isRented()) {
            return RentBookResponse.buildFailure("Book already rented");
        } else {
            return RentBookResponse.buildSuccess(bookRepository.save(book.get().rentBook(user.get())));
        }
        return book;
    }

    public Book updateBook(long id, Book bookEdit) {
        return bookRepository.findById(id)
                .map(book -> bookRepository.save(book.update(bookEdit)))
                .orElse(null);
    }

    public boolean deleteBook(long id) {
        bookRepository.deleteById(id);
        return true;
    }

}
