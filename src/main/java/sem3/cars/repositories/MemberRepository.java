package sem3.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.cars.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
}
