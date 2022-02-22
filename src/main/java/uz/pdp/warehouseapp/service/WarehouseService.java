package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.entity.Warehouse;
import uz.pdp.warehouseapp.repository.UserRepository;
import uz.pdp.warehouseapp.repository.WarehouseRepository;

import java.util.List;

@Service

public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    UserRepository userRepository;

    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }



}
