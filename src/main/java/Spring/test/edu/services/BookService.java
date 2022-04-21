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
    //TODO
    public Book rentBook(long bookId, long userId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (!bookOpt.isPresent()) {
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
        }
        return book;
    }

    public Book updateBook(long id, Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book bookEdit = bookData.get();
            return bookRepository.save(bookEdit.bookCompare(book,bookEdit));
        } else {
            return null;
        }
    }

    public boolean deleteBook(long id) {
        bookRepository.deleteById(id);
        return true;
    }

}
