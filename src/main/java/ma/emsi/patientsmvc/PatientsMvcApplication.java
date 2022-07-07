package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);

    }
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
   // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
           patientRepository.save(new Patient(null,"abdo",new Date(),false,150));
            patientRepository.save(new Patient(null,"youssef",new Date(),true,190));
            patientRepository.save(new Patient(null,"mohammed",new Date(),false,120));
            patientRepository.save(new Patient(null,"saad",new Date(),true,100));
            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
    @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
          securityService.saveNewUser("abdo","1234","1234");
            securityService.saveNewUser("anas","1234","1234");
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("abdo","ADMIN");
            securityService.addRoleToUser("abdo","USER");
            securityService.addRoleToUser("anas","USER");

        };
    }


}
