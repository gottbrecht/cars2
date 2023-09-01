package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)


public class MemberResponse {
    private String username; //PK
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;

    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Integer ranking;
    Boolean approved;

    public MemberResponse(Member m, boolean includeAll) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        if(includeAll) {
            this.created = m.getCreated();
            this.edited = m.getLastEdited();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }

    }


}
