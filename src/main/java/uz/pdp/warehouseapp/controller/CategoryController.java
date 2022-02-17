package uz.pdp.warehouseapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.warehouseapp.dto.CategoryDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Category;
import uz.pdp.warehouseapp.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "warehouse/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showCategory(Model model){
      model.addAttribute("categoryDto",new CategoryDto());
      List<Category> categories=categoryService.getAllCategory();
      model.addAttribute("categories",categories);
        List<Category> chooseList = categories.stream().filter(Category::isActive).collect(Collectors.toList());
        model.addAttribute("categoriesChoose",chooseList);


        if(categories.isEmpty()){
          model.addAttribute("danger","Not found any category");
      }
        return "/product/categoryOperation";
    }
    @PostMapping(path = "/add")
    public String addCategory(CategoryDto categoryDto,Model model){
        Response response=categoryService.addCategory(categoryDto);

        model.addAttribute("categoryDto",new CategoryDto());
        List<Category> categories=categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        List<Category> chooseList = categories.stream().filter(Category::isActive).collect(Collectors.toList());
        model.addAttribute("categoriesChoose",chooseList);
        if(response.isSuccess()){
            model.addAttribute("success",response.getMessage());
            return "/product/categoryOperation";
        }
        else
            model.addAttribute("danger",response.getMessage());
        return "/product/categoryOperation";
    }
}
