package Spring.test.edu.controllers;

import Spring.test.edu.dtos.RentBookResponse;
import Spring.test.edu.models.Book;
import Spring.test.edu.services.BookService;
import Spring.test.edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book newBook = bookService.createBook(book);
        return ResponseEntity.ok(newBook);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Book> returnBook(@PathVariable("id") long id) {
        Book newBook = bookService.returnBook(id);
        if (newBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newBook);
    }

    //TODO
    @PostMapping("/rent/{bookId}/{userId}")
    public ResponseEntity<Book> rentBook(@PathVariable("bookId") Long bookId, @PathVariable("userId") Long userId) {
        Book newBook = bookService.rentBook(bookId,userId);
        if (newBook == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (newBook.getUser() == null) {
            return new ResponseEntity<>(newBook, HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(newBook.getUser().getId(), userId)) {
            return new ResponseEntity<>(newBook, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Book editedBook = bookService.updateBook(id, book);
        if (editedBook != null) {
            return new ResponseEntity<>(editedBook, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        if (bookService.deleteBook(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
