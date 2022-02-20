package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.thymleafex.model.Login;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    //Check for Credentials
    @PostMapping("/login")
    public String login(@ModelAttribute(name = "user") User user) {
        User login = userService.login(user.getEmail(), user.getPassword());
        System.out.printf("login");
        if (Objects.nonNull(login)) {
            return "redirect:/";
        }
        return "users";
    }
}