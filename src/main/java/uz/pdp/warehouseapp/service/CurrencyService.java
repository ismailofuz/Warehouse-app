package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.entity.Currency;
import uz.pdp.warehouseapp.entity.User;
import uz.pdp.warehouseapp.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    public List<Currency> listAll(){
        return currencyRepository.findAll();
    }
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    public Currency get(Integer id) throws CurrencyNotFoundExaption {
        Optional<Currency> byId = currencyRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new CurrencyNotFoundExaption("Could not find any currency with ID "+id);
    }
    public void delete(Integer id) throws UserNotFoundExaption {
        Long count = currencyRepository.countById(id);
        if (count==null || count == 0){
            throw new UserNotFoundExaption("Could not find any users with ID "+id);
        }
        currencyRepository.deleteById(id);
    }




}
