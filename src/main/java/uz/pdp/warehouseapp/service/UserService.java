package uz.pdp.warehouseapp.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.dto.UserDTO;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {


    final UserRepository userRepository;

    final EmailSenderService emailSenderService;

    public UserService(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    public Response checkUser(UserDTO userDTO) {
        Response response=new Response();
        Optional<User> userOptional = userRepository.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if(userOptional.isPresent()){
            response.setMessage("Successful login");
            response.setSuccess(true);
            return response;
        }
        response.setMessage("Email or password wrong");
        return response;
    }
    public Response setdCodeForRemember(String email) {
        Response response=new Response();

        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
         emailSenderService.setMailSender(user.getEmail(),"Get password login foe Warehouse","password:"+user.getPassword());
            response.setMessage("Check your email");
            response.setSuccess(true);
            return response;
        }
        response.setMessage("Not found email");
        return response;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Response saveUSer(UserDTO userDTO) {
        String phoneNumber="+998"+userDTO.getOperatorCode()+userDTO.getPhoneNumber();
        Response response=new Response();
        Optional<User> optionalUser = userRepository.findAllByEmailOrPhoneNumber(userDTO.getEmail(), phoneNumber);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getEmail().equals(userDTO.getEmail()))
                response.setMessage("This "+userDTO.getEmail()+" email already exist");
            else
                response.setMessage("This "+userDTO.getPhoneNumber()+" phone number already exist");

                return response;
        }
        User user=new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber(), userDTO.getEmail(), userDTO.getPassword());
        User save = userRepository.save(user);
        save.setCode("WZ:000"+save.getId());
        userRepository.save(save);
        response.setMessage("You are registered");
        response.setSuccess(true);
        return response;
    }
}
