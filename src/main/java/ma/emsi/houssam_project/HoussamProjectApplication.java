package ma.emsi.houssam_project;

import ma.emsi.houssam_project.dao.MemberRepository;
import ma.emsi.houssam_project.entities.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HoussamProjectApplication implements CommandLineRunner {
    @Autowired MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(HoussamProjectApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
                Member M1 = memberRepository.save(new Member(null, "houssam@gmail.com", "houssam", "G1-OC" , "123456" , null ) );
                Member M2 = memberRepository.save(new Member(null, "hamza@gmail.com", "hamza", "G1-OC" , "123456" , null ) );
                Member M3 = memberRepository.save(new Member(null, "said@gmail.com", "said", "G1-OC" , "123456" , null ) );
                Member M4 = memberRepository.save(new Member(null, "karim@gmail.com", "karim", "G1-OC" , "123456" , null ) );

    }

}
