package Spring.test.edu.controllers;

import Spring.test.edu.dtos.RentBookRequest;
import Spring.test.edu.models.Book;
import Spring.test.edu.services.BookService;
import Spring.test.edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        if (books.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        System.out.println(book);
        Book newBook = bookService.createBook(book);
        if (newBook != null) {
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } else return new ResponseEntity<>("Book failed to Create", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable("id") long id) {
        Book newBook = bookService.returnBook(id);
        if (newBook == null) {
            return new ResponseEntity<>("Book not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @PostMapping("/rent/{bookId}/{userId}")
    public ResponseEntity<?> rentBook(@PathVariable Map<String,String> pathVarsMap) {
        long bookId = Long.parseLong(pathVarsMap.get("bookId"));
        long userId = Long.parseLong(pathVarsMap.get("userId"));
        Book newBook = bookService.rentBook(bookId,userId);
        if (newBook == null) {
            return new ResponseEntity<>("Book not Found", HttpStatus.NOT_FOUND);
        }
        if (newBook.getUser() == null) {
            return new ResponseEntity<>("User not Found", HttpStatus.NOT_FOUND);
        }
        if (newBook.getUser().getId() == userId) {
            return new ResponseEntity<>(newBook, HttpStatus.OK);
        } else return new ResponseEntity<>("Book is already Rented", HttpStatus.BAD_REQUEST);
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
