package Spring.test.edu.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String username;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private List<Book> books = new ArrayList<>();


    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", books=" + books +
                '}';
    }
}
