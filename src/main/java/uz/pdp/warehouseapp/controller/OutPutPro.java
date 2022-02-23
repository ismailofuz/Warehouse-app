package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.warehouseapp.dto.OutputProductDto;
import uz.pdp.warehouseapp.entity.OutputProduct;
import uz.pdp.warehouseapp.entity.Product;
import uz.pdp.warehouseapp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/warehouse/opp")
public class OutPutPro {
    @Autowired
    ProductRepository productRepository;
    @GetMapping()
    public String show(Model model){
        return "outPutProduct";
    }
    @PostMapping("/add")
    public String add(Model model, OutputProductDto outputProductDto){
        Product product = productRepository.getById(outputProductDto.getProductId());
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(product);
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());

       // list.add(outputProduct.getId());

      return "outPutProduct";
    }
}
