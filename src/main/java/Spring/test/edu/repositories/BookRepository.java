package Spring.test.edu.repositories;

import Spring.test.edu.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(long isbn);
}
