package uz.pdp.warehouseapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseapp.dto.ProductDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Category;
import uz.pdp.warehouseapp.entity.Measurement;
import uz.pdp.warehouseapp.service.MeasurementService;
import uz.pdp.warehouseapp.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(path = "/warehouse/product")
public class ProductController {
  final ProductService productService;
  final MeasurementService measurementService;

    public ProductController(ProductService productService, MeasurementService measurementService) {
        this.productService = productService;
        this.measurementService = measurementService;
    }

    @GetMapping
    public String addProduct(Model model){
        List<Category> categories=productService.getCategory();
        if(categories.isEmpty())
            return "redirect:/warehouse/category";
        List<Measurement> measurements=productService.getMeasurementForChoose();
        if(measurements.isEmpty())
            return "redirect:/warehouse/measurement";
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories",categories);
        model.addAttribute("measurements",measurements);
        model.addAttribute("message",new Response());
        return "/product/addProduct";
    }

    @PostMapping(path = "/add")
    public String saveProduct(Model model, @ModelAttribute ProductDto productDto,MultipartHttpServletRequest request) {
        System.out.println(productDto);
       Response response=productService.saveProduct(productDto,request);
        List<Category> categories=productService.getCategory();
        if(categories.isEmpty())
            return "redirect:/warehouse/category";
        List<Measurement> measurements=productService.getMeasurementForChoose();
        if(measurements.isEmpty())
            return "redirect:/warehouse/measurement";
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories",categories);
        model.addAttribute("measurements",measurements);
        model.addAttribute("message",response);
        return "/product/addProduct";
    }
}
