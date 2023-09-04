package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MemberServiceMockitoTest {

    @Mock
    //Injecting Mock
    private MemberRepository memberRepository;
    MemberService memberService;
    Member m1, m2;

    boolean isInitialized;

    @BeforeEach
    void setUp() {
    if(!isInitialized) {
       m1 = memberRepository.save(new Member("","Fantastic","lola.fiola@example.com","Lola","Fiola","17 Moon Road","Caketown","5000"));
       m2 = memberRepository.save(new Member("BrokaNeelson","SUSHI123","BKA.neelson@example.com","Broka","Neelson","82' Main Street","Nottingham","8000"));
        isInitialized = true;
    }
        memberService = new MemberService(memberRepository);

    when(memberRepository.save(any())).thenAnswer(invocation -> {
            Object [] args = invocation.getArguments();
            return new Member(
            ((Member) args[0]).getUsername(),
            ((Member) args[0]).getPassword(),
            ((Member) args[0]).getEmail(),
          //((Member) args[0]).getFirstname()
          //((Member) args[0]).getLastname(),
            ((Member) args[0]).getStreet(),
            ((Member) args[0]).getCity(),
            ((Member) args[0]).getZip()
        );
    });
        //Define mock behavior
       // when(memberRepository.findAll()).thenReturn(List.of(m1, m2));
    }

    @Test
    public void testGetMembers() {
        List<MemberResponse> responses = memberService.getMembers(true);
                assertEquals(2, responses.size(), "Expected 2 members");
    }
}