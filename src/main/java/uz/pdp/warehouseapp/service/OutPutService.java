package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.OutputDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Client;
import uz.pdp.warehouseapp.entity.Currency;
import uz.pdp.warehouseapp.entity.Output;
import uz.pdp.warehouseapp.entity.Warehouse;
import uz.pdp.warehouseapp.repository.ClientRepository;
import uz.pdp.warehouseapp.repository.CurrencyRepository;
import uz.pdp.warehouseapp.repository.OutPutRepository;
import uz.pdp.warehouseapp.repository.WarehouseRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class OutPutService {
    @Autowired
    OutPutRepository outPutRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    public List<Output> getAll() {
        return outPutRepository.findAll();
    }
     public List<Warehouse> getAllWarehouse(){
         List<Warehouse> all = warehouseRepository.findAll();
         return all;
     }
    public Response addOutPut(OutputDto outputDto) {
        Output output = new Output();
        Optional<Client> client = clientRepository.findById(outputDto.getClientId());
        Warehouse warehouse = warehouseRepository.getById(outputDto.getWarehouseId());
        Optional<Currency> currency = currencyRepository.findById(outputDto.getCurrencyId());

        output.setClient(client.get());
        output.setCurrency(currency.get());
        output.setWarehouse(warehouse);
        outPutRepository.save(output);
        return new Response("Added",true);
    }

    public List<Currency> getAllCurrency() {
        return  currencyRepository.findAll();
    }

    public Output getByIdi(Integer id) {
        return outPutRepository.findById(id).orElse(new Output());
    }

    public Response edit(OutputDto outputDto,Integer id) {
        Output output = outPutRepository.findById(id).get();
        Optional<Client> client = clientRepository.findById(outputDto.getClientId());
        Warehouse warehouse = warehouseRepository.getById(outputDto.getWarehouseId());
        Optional<Currency> currency = currencyRepository.findById(outputDto.getCurrencyId());

        output.setClient(client.get());
        output.setCurrency(currency.get());
        output.setWarehouse(warehouse);
        outPutRepository.save(output);
         return new Response("Edited",true);
    }
}
