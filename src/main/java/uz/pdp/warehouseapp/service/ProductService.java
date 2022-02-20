package uz.pdp.warehouseapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseapp.dto.ProductDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.*;
import uz.pdp.warehouseapp.repository.*;
import uz.pdp.warehouseapp.util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;
    final MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository,
                          MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.measurementRepository = measurementRepository;
    }

    public List<Category> getCategory() {
        return categoryRepository.findCategoryForChoose();
    }
    public List<Measurement> getMeasurementForChoose() {
        return measurementRepository.getMeasurementForChoose();
    }

    public Response saveProduct(ProductDto productDto,MultipartHttpServletRequest request) {
       Response response=new Response();
        boolean byName = findProductByName(productDto.getName());
        if (!byName){
            List<MultipartFile> attachment = request.getFiles("attachment");
            if(attachment.size()>0) {
                List<Attachment>attachments=new ArrayList<>();
                for (MultipartFile multipartFile : attachment) {
                    try {
                        byte[] bytes = multipartFile.getBytes();
                        String originalFilename =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ multipartFile.getOriginalFilename();
                        String contentType = multipartFile.getContentType();
                        long size = multipartFile.getSize();
                        Attachment attachmentNew=new Attachment(originalFilename,size,contentType);
                        Attachment save = attachmentRepository.save(attachmentNew);
                        attachments.add(save);
                        AttachmentContent attachmentContent=new AttachmentContent(bytes,save);
                        attachmentContentRepository.save(attachmentContent);

                    } catch (IOException e) {
                        response.setMessage("Something wrong");
                        return response;
                    }
                }
                Measurement measurement = measurementRepository.getById(productDto.getMeasurement());
                Category category = categoryRepository.getById(productDto.getCategory());
                Product product=new Product(productDto.getName(),productDto.isActive(),category,measurement,attachments);
                Product save = productRepository.save(product);
                save.setCode("#00"+save.getId());
                productRepository.save(save);
                response.setSuccess(true);
                response.setMessage("Add product code: "+"#00"+save.getId());
                return response;
            }
            response.setMessage("Not found photo");
            return response;

        }
        response.setMessage("This name already exist");
        return response;
    }
    public boolean findProductByName(String name) {
        for (Product product : productRepository.findAll()) {
            if (product.getName().trim().equalsIgnoreCase(name.trim()))
                return true;
        }
        return false;
    }
}
