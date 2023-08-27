package dat3.cars.dto;


import dat3.cars.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;

    public static Member getMemberEntity(MemberRequest m) {
        return new Member(m.username, m.getPassword(), m.getEmail(), m.firstName, m.lastName, m.getStreet(), m.getCity(), m.getZip());
    }

    public MemberRequest(Member m){
        this.username = m.getUsername();
        this.password = m.getPassword();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.city = m.getCity();
        this.zip = m.getZip();
    }

}
