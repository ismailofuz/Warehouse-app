package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseapp.dto.ClientDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Client;
import uz.pdp.warehouseapp.repository.ClientRepository;
import uz.pdp.warehouseapp.service.ClientService;

import java.util.List;

    @Controller("/client")
    public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/getAll")
    public Iterable<Client>get(){
       return clientService.get();
    }
    @GetMapping("/getById/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }
    @PostMapping("/add")
    public Response  add(@RequestBody ClientDto clientDto){
        return clientService.add(clientDto);
    }
    @DeleteMapping("delete/{id}")
    public Response del(@PathVariable Integer id){
        return clientService.del(id);
    }

}
