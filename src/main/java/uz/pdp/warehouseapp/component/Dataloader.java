package uz.pdp.warehouseapp.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.repository.UserRepository;

@Component
public class Dataloader implements CommandLineRunner {
@Value("${spring.jpa.hibernate.ddl-auto}")
private String ddl;
final UserRepository userRepository;

    public Dataloader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       if("create-drop".contains(ddl)){
           User user=new User("Javokhirbek","Rakhimov","+998997834961",
                   "javohirbekrakhimov@gmail.com","997834961");
           user.setCode("WZ:0001");
           user.setActive(true);
           userRepository.save(user);
       }
    }
}
