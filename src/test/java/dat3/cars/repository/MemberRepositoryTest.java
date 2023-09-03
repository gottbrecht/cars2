package dat3.cars.repository;


import dat3.cars.entity.Member;
import dat3.cars.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUP() {
        if(!isInitialized) {
            memberRepository.save(new Member("","Fantastic","lola.fiola@example.com","Lola","Fiola","17 Moon Road","Caketown","5000"));
            memberRepository.save(new Member("BrokaNeelson","SUSHI123","BKA.neelson@example.com","Broka","Neelson","82' Main Street","Nottingham","8000"));
            isInitialized = true;
        }
    }
    @Test
    public void deleteAll() {
        memberRepository.deleteAll();
        assertEquals(0,memberRepository.count());
    }

    @Test
    public void testAll(){
        Long count = memberRepository.count();
        assertEquals(2,count);
    }
}
