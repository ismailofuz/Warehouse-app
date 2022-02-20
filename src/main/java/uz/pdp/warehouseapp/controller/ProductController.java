package uz.pdp.warehouseapp.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseapp.dto.ProductDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Category;
import uz.pdp.warehouseapp.entity.Measurement;
import uz.pdp.warehouseapp.entity.Product;
import uz.pdp.warehouseapp.service.MeasurementService;
import uz.pdp.warehouseapp.service.ProductService;
import uz.pdp.warehouseapp.util.Constants;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @GetMapping(path = "/show")
    public String showProduct(Model model,
                              @RequestParam(value = "page",defaultValue = Constants.DEFAULT_PAGE_NUMBER) Integer page,
                              @RequestParam(value = "size",defaultValue = Constants.DEFAULT_PAGE_SIZE) Integer size){
        if(page>0){
            page=page-1;
        }
       Page<Product>products= productService.getAll(PageRequest.of(page,size));
        System.out.println("products.getTotalElements() = " + products.getTotalElements());
        if (products.getTotalElements()==0){
            model.addAttribute("message",new Response("Not found any product",false));
        }
        int totalPages = products.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        List<Product>content = products.getContent();
        model.addAttribute("message",new Response());
        model.addAttribute("products",content);
        int pageNumber = products.getPageable().getPageNumber();
        model.addAttribute("currentPage",pageNumber);
        return "product/showProduct";
    }
    @GetMapping(path = "/show/{id}")
    public String showPeoductById(@PathVariable Integer id,Model model){
        List<ResponseEntity<?>>photos=productService.getPhotos(id);
        model.addAttribute("photos",photos);
        return "product/showProductById";
    }
}
