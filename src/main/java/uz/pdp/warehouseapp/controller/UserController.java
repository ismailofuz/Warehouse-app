package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserList(Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        return "users";
    }
    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "user_form";
    }
    @GetMapping("users/save")
    public String saveUser(User user){
        userService.save(user);
        return "redirect:/users";
    }


}
