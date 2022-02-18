package uz.pdp.warehouseapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouseapp.dto.MeasurementDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Measurement;
import uz.pdp.warehouseapp.service.MeasurementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MeasurementController {
 final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public String showMeasurement(Model model){
        model.addAttribute("categoryDto",new MeasurementDto());
        List<Measurement> categories=measurementService.getAllMeasurement();
        List<Measurement> collect = categories.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        model.addAttribute("categories",collect);
        List<Measurement> chooseList = categories.stream().filter(Measurement::isActive).collect(Collectors.toList());
        model.addAttribute("categoriesChoose",chooseList);


        if(categories.isEmpty()){
            model.addAttribute("message",new Response("Not found any category",false));
        }else
            model.addAttribute("message",new Response("Total category amount: "+categories.size(),true));
        return "/category/categoryOperation";
    }
    @PostMapping(path = "/add")
    public String addMeasurement(MeasurementDto categoryDto,Model model){
        Response response=measurementService.addMeasurement(categoryDto);
        model.addAttribute("categoryDto",new MeasurementDto());
        List<Measurement> categories=measurementService.getAllMeasurement();
        List<Measurement> collect = categories.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        model.addAttribute("categories",collect);
        List<Measurement> chooseList = categories.stream().filter(Measurement::isActive).collect(Collectors.toList());
        model.addAttribute("categoriesChoose",chooseList);
        model.addAttribute("message",response);
        return "/category/categoryOperation";
    }
    @GetMapping(path = "/edite/{id}")
    public String editeMeasurement(@PathVariable Integer id, Model model){

        Measurement category=measurementService.getMeasurementByID(id);
        List<Measurement> categories=measurementService.getAllMeasurement();
        List<Measurement> collect = categories.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        model.addAttribute("categories",collect);
        List<Measurement> chooseList = categories.stream().filter(Measurement::isActive).collect(Collectors.toList());
        chooseList.remove(category);
        model.addAttribute("categoriesChoose",chooseList);
        if(categories.isEmpty()){

            model.addAttribute("message",new Response("Not found this category",false));
        }else {
            model.addAttribute("category",category);
        }
        model.addAttribute("message",new Response());
        return "/category/editeMeasurement";
    }
    @PostMapping(path = "/edite/{id}")
    public String updateMeasurement(Measurement category,Model model){
        Response response=measurementService.updateMeasurement(category);
        if(response.isSuccess()){
            model.addAttribute("categoryDto",new MeasurementDto());
            List<Measurement> categories=measurementService.getAllMeasurement();
            List<Measurement> collect = categories.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
            model.addAttribute("categories",collect);
            List<Measurement> chooseList = categories.stream().filter(Measurement::isActive).collect(Collectors.toList());
            chooseList.remove(category);
            model.addAttribute("categoriesChoose",chooseList);
            model.addAttribute("message",response);

            return "/category/categoryOperation";
        }
        Measurement categoryReturn=measurementService.getMeasurementByID(category.getId());
        List<Measurement> categories=measurementService.getAllMeasurement();
        model.addAttribute("categories",categories);
        List<Measurement> chooseList = categories.stream().filter(Measurement::isActive).collect(Collectors.toList());
        model.addAttribute("categoriesChoose",chooseList);

        model.addAttribute("message",response);
        if(categories.isEmpty()){

            model.addAttribute("message",new Response("Not found this category",false));
        }else {
            model.addAttribute("category",categoryReturn);
        }
        return "/category/editeMeasurement";
    }
}
