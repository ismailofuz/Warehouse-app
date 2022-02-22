package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.warehouseapp.entity.Currency;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.service.CurrencyNotFoundExaption;
import uz.pdp.warehouseapp.service.CurrencyService;
import uz.pdp.warehouseapp.service.UserNotFoundExaption;

import java.util.List;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @GetMapping
    public String showUserList(Model model) {
        List<Currency> listUsers = currencyService.listAll();
        model.addAttribute("listCurrency", listUsers);
        return "currency";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("currency", new Currency());
        model.addAttribute("pageTitle", "Add New Currency");
        return "currency_form";
    }

    @PostMapping("/save")
    public String saveUser(Currency currency, RedirectAttributes redirectAttributes) {
        currencyService.save(currency);
        redirectAttributes.addFlashAttribute("message", "The Currency has been saved successfully");
        return "redirect:/currency";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Currency currency = currencyService.get(id);
            model.addAttribute("currency", currency);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "currency_form";
        } catch (CurrencyNotFoundExaption e) {
            redirectAttributes.addFlashAttribute("message", "The Currency has been saved successfully");
            return "redirect:/currency";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            currencyService.delete(id);
            redirectAttributes.addFlashAttribute("message","The Currency ID " + id + " has been deleted.");

        } catch (UserNotFoundExaption e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/currency";
    }

}
