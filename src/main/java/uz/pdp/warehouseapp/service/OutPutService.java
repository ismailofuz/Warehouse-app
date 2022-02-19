package uz.pdp.warehouseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.OutputDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Output;
import uz.pdp.warehouseapp.repository.OutPutRepository;

import java.util.List;

@Service
public class OutPutService {
@Autowired
    OutPutRepository outPutRepository;
    public List<Output> getAll() {
        return outPutRepository.findAll();
    }

    public Response addOutPut(OutputDto outputDto) {
        return new Response();
    }
}
