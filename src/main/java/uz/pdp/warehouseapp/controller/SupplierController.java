package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Supplier;
import uz.pdp.warehouseapp.service.SupplierService;

import java.util.List;

@RestController()
@RequestMapping(path = "/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("/get")
    public List<Supplier> getAll(){
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id){
        return supplierService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public Response edit(@PathVariable Integer id, Supplier supplier){
        return supplierService.edit(id, supplier);
    }

    @PostMapping("/add")
    public Response add(@RequestBody Supplier supplier){
        return supplierService.add(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Integer id){
        return supplierService.delete(id);
    }

}
