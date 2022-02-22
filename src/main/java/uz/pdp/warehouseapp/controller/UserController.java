package uz.pdp.warehouseapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uz.pdp.warehouseapp.db.EmailCode;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.dto.UserDTO;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.service.EmailSenderService;
import uz.pdp.warehouseapp.service.UserService;

import java.util.Optional;


@Controller
@RequestMapping(path = "/warehouse/user")
public class UserController {
    final UserService userService;
    final EmailSenderService emailSenderService;

    public UserController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, Model model) {
        Response response = userService.checkUser(userDTO);
        model.addAttribute("message", response);
        if (response.isSuccess())
            return "dashboard";
        else
            model.addAttribute("userDto", new UserDTO());
        return "login";
    }

    @PostMapping(path = "/code")
     //@EventListener(ApplicationReadyEvent.class)
    public String sendCode(Model model, @ModelAttribute("email") String email) {
        Optional<User> user=userService.getByEmail(email);
        Response response = userService.setdCodeForRemember(email);
        model.addAttribute("userDto", new UserDTO());
        model.addAttribute("message",response);
        return "login";

    }

    @GetMapping(path = "/code")
    public String emailInput(Model model) {
        model.addAttribute("message", new Response("Enter your email", true));
        return "/user/emailInput";
    }
    @GetMapping(path = "/register")
    public String register(Model model){
        model.addAttribute("userDto",new UserDTO());
        model.addAttribute("message",new Response());
        return "/user/register";
    }
    @PostMapping(path = "/register")
    public String saveNewUser(UserDTO userDTO,Model model){
        Response response=userService.saveUSer(userDTO);
        if(response.isSuccess()){
            String code=String.valueOf(Math.random()*89999+10000);
            EmailCode.emailCode.put(userDTO.getEmail(),code );
            emailSenderService.setMailSender(userDTO.getEmail(),"Verification code",
                    "Code: "+code);
            model.addAttribute("userDto",userDTO);
            model.addAttribute("message",new Response());
            return "/user/verficationCode";
        }
        model.addAttribute("message",response);
         return "redirect:/warehouse/user/register";
    }
  @PostMapping(path = "/verification/{email}")
    public String register(@PathVariable String email, Model model,
                           @ModelAttribute("code") String code){
      String codeOrginal = EmailCode.emailCode.get(email);
      if(code.equals(codeOrginal)){
          return "redirect:/";
      }

      model.addAttribute("userDto",new UserDTO());
        model.addAttribute("message",new Response("Verification code is wrong",false));
        return "/user/register";
    }
}