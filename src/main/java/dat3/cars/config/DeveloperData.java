package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repositories.CarsRepository;
import dat3.cars.repositories.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeveloperData implements ApplicationRunner {

    final CarsRepository carsRepository;
    final MemberRepository memberRepository;

    public DeveloperData(CarsRepository carsRepository, MemberRepository memberRepository) {
        this.carsRepository = carsRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Ford","F-150"));
        cars.add(new Car("Honda","Civic"));
        cars.add(new Car("Toyota","Camry"));
        cars.add(new Car("Nissan","Altima"));
        cars.add(new Car("BMW","3 series"));
        cars.add(new Car("Porche","911"));
        cars.add(new Car("Audi","E-tron"));
        carsRepository.saveAll(cars);

        List<Member> members = new ArrayList<>();
        members.add(new Member("BrokaNeelson","SUSHI123","BKA.neelson@example.com","Broka","Neelson","82' Main Street","Nottingham","8000"));
        memberRepository.saveAll(members);
    }
}
