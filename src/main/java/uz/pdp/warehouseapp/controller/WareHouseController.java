package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseapp.entity.Warehouse;
import uz.pdp.warehouseapp.service.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("/warehouses")
public class WareHouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public String showWarehouseList(Model model){
//        List<Warehouse> warehouseList = warehouseService.getAll();
//        model.addAttribute("warehouseList",warehouseList );
        return "warehouses";
    }



}
