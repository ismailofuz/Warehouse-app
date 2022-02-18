package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.ClientDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Client;
import uz.pdp.warehouseapp.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Iterable<Client> get() {
        return clientRepository.findAll();
    }

    public Client getById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        }
        return new Client();
    }

    public Response add(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        Client save = clientRepository.save(client);
        return new Response("Success",true);
    }

    public Response del(Integer id) {
return new Response();
    }
}
