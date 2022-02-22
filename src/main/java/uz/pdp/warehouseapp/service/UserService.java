package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String gmail, String password) {
        User user = userRepository.findByEmailAndPhoneNumber(gmail, password);
        return user;

    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundExaption {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new UserNotFoundExaption("Could not find any users with ID "+id);
    }
    public void delete(Integer id) throws UserNotFoundExaption {
        Long count = userRepository.countById(id);
        if (count==null || count == 0){
            throw new UserNotFoundExaption("Could not find any users with ID "+id);
        }
        userRepository.deleteById(id);
    }
}
