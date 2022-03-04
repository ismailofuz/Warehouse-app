package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uz.pdp.warehouseapp.dto.WarehouseDto;
import uz.pdp.warehouseapp.entity.Warehouse;
import uz.pdp.warehouseapp.repository.UserRepository;
import uz.pdp.warehouseapp.repository.WarehouseRepository;
import uz.pdp.warehouseapp.service.CurrencyNotFoundException;
import uz.pdp.warehouseapp.service.UserNotFoundExaption;
import uz.pdp.warehouseapp.service.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String showUserList(Model model) {
        List<Warehouse> warehouseList = warehouseService.listAll();
        model.addAttribute("warehouseList", warehouseList);
        return "warehouse";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("pageTitle", "Add New Warehouse");
        return "warehouse_form";
    }

    @PostMapping("/save")
    public String saveUser(WarehouseDto warehouseDto, RedirectAttributes redirectAttributes) {
        warehouseService.save(warehouseDto);
        redirectAttributes.addFlashAttribute("message", "The Warehouse has been saved successfully");
        return "redirect:/warehouse";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Warehouse warehouse = warehouseService.get(id);
            model.addAttribute("warehouse", warehouse);
            model.addAttribute("usersList", userRepository.findAll());

            model.addAttribute("pageTitle", "Edit Warehouse (ID: " + id + ")");
            return "warehouse_form";
        } catch (CurrencyNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The Warehouse has been saved successfully");
            return "redirect:/warehouse";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            warehouseService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The Warehouse ID " + id + " has been deleted.");

        } catch (UserNotFoundExaption e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/warehouse";
    }



}
