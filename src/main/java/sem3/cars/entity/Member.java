package sem3.cars.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class Member {
    @Id
    private String username;
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column()
    private String zip;

    private int ranking;
    private boolean approved;

    @UpdateTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Member(String username, String password, String email, String firstName, String lastName, String street, String city, String zip) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;

    }
}
