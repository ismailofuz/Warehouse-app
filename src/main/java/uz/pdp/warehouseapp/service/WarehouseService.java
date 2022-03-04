package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.OutputDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.dto.WarehouseDto;
import uz.pdp.warehouseapp.entity.*;
import uz.pdp.warehouseapp.repository.UserRepository;
import uz.pdp.warehouseapp.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service

public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    UserRepository userRepository;


    public Response save(WarehouseDto warehouseDto) {

        List<User> allById = userRepository.findAllById(warehouseDto.getUserId());

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(warehouseDto.isActive());
        warehouse.setUsers(allById);

        Warehouse save = warehouseRepository.save(warehouse);
        return new Response("Added", true, save);
    }

    public List<Warehouse> listAll(){
        return warehouseRepository.findAll();
    }



    public Warehouse get(Integer id) throws CurrencyNotFoundException {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new CurrencyNotFoundException("Could not find any currency with ID "+id);
    }

    public void delete(Integer id) throws UserNotFoundExaption {
        Long count = warehouseRepository.countById(id);
        if (count==null || count == 0){
            throw new UserNotFoundExaption("Could not find any users with ID "+id);
        }
        warehouseRepository.deleteById(id);
    }
}

