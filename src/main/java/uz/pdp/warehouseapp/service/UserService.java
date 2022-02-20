package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String gmail, String password){
        User user = userRepository.findByEmailAndPhoneNumber(gmail, password);
        return user;

    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);

    }
}
