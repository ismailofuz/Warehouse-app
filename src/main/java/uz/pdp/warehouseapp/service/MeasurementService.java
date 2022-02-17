package uz.pdp.warehouseapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.warehouseapp.dto.MeasurementDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Measurement;
import uz.pdp.warehouseapp.repository.MeasurementRepository;

import java.util.List;

@Service
public class MeasurementService {
    final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> getAllMeasurement() {
        return measurementRepository.findAll();
    }

    public Response addMeasurement(MeasurementDto categoryDto) {

      Response response=new Response();
//        boolean categoryByName = MeasurementByName(categoryDto.getName());
//        if(!categoryByName) {
//            if (!categoryDto.getParentMeasurementId().equals(-1)) {
//                if (categoryDto.getParentMeasurementId().equals(0)) {
//                    Measurement category = new Measurement(categoryDto.getName(), categoryDto.isActive());
//                    measurementRepository.save(category);
//                }else {
//                    Measurement category=new Measurement(categoryDto.getName(), measurementRepository.getById(categoryDto.getParentMeasurementId()),categoryDto.isActive());
//                    measurementRepository.save(category);
//                }
//                response.setSuccess(true);
//                response.setMessage("Add category");
//                return response;
//            }
//            response.setMessage("Please choose parent category");
//            return response;
//        }
//        response.setMessage("This name already exist");
        return response;
    }
    public boolean MeasurementByName(String name){
        for (Measurement category : measurementRepository.findAll()) {
            if(category.getName().trim().toLowerCase().equals(name.trim().toLowerCase()))
                return true;
        }
        return false;
    }

    public Measurement getMeasurementByID(Integer id) {
        return measurementRepository.findById(id).orElse(new Measurement());
    }
    public Response updateMeasurement(Measurement category) {
        Response response=new Response();
        boolean hasName=false;
        for (Measurement category1 : measurementRepository.findAll()) {
            if(category1.getName().trim().toLowerCase().equals(category.getName().trim().toLowerCase())&&!category1.getId().equals(category.getId())){
                hasName=true;
            }
        }
        if(!hasName) {
            measurementRepository.save(category);
            response.setSuccess(true);
            response.setMessage("Edite category");
            return response;
        }
        response.setMessage("This name already exist");
        return response;
    }
}
