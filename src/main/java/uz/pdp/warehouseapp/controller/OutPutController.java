package uz.pdp.warehouseapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseapp.dto.ClientDto;
import uz.pdp.warehouseapp.dto.OutputDto;
import uz.pdp.warehouseapp.dto.Response;
import uz.pdp.warehouseapp.entity.Client;
import uz.pdp.warehouseapp.entity.Currency;
import uz.pdp.warehouseapp.entity.Output;
import uz.pdp.warehouseapp.entity.Warehouse;
import uz.pdp.warehouseapp.repository.OutPutRepository;
import uz.pdp.warehouseapp.service.ClientService;
import uz.pdp.warehouseapp.service.OutPutService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/warehouse/output")
public class OutPutController {
    @Autowired
    OutPutRepository outPutRepository;
    @Autowired
    OutPutService outPutService;
    @Autowired
    ClientService clientService;
   @GetMapping()
    public String showOutPut(Model model){
       model.addAttribute("outPutDto",new ClientDto());
       List<Client>clients=clientService.getAll();
       model.addAttribute("clients",clients);
       List<Warehouse>warehouses=outPutService.getAllWarehouse();
       model.addAttribute("warehouses",warehouses);
       List<Currency>currencies=outPutService.getAllCurrency();
       model.addAttribute("currencies",currencies);
       List<Output> outputs=outPutService.getAll();

//        List<Output> outputList = outputs.stream().sorted((o1, o2) -> o1.getClient().getName().compareTo(o2.getClient().getName())).
//                collect(Collectors.toList());
      model.addAttribute("outputs",outputs);
      //  model.addAttribute("outputList",outputList);
        if(outputs.isEmpty()){
            model.addAttribute("message",new Response("Not found any client",false));
        }else
            model.addAttribute("message",new Response("Total client amount: "+outputs.size(),true));
        return "/output/outputOperation";
    }

    @PostMapping(path = "/add")
    public String addCategory(OutputDto outputDto, Model model){
        Response response=outPutService.addOutPut(outputDto);

        List<Client>clients=clientService.getAll();
        model.addAttribute("clients",clients);
        List<Warehouse>warehouses=outPutService.getAllWarehouse();
        model.addAttribute("warehouses",warehouses);
        List<Currency>currencies=outPutService.getAllCurrency();
        model.addAttribute("currencies",currencies);
        List<Output>outputs=outPutRepository.findAll();
        model.addAttribute("output",outputs);
//        List<Output> outputList = outputs.stream().sorted((o1, o2) -> o1.getClient().getName().compareTo(o2.getClient().getName())).
//                collect(Collectors.toList());
        model.addAttribute("massage",response);
    //    model.addAttribute("outputList",outputList);
        return "redirect:/warehouse/output";
    }
    @GetMapping("/edite/{id}")
    public String editeOutPut(@PathVariable Integer id, Model model){
        Output output=outPutService.getByIdi(id);
        List<Client>clients=clientService.getAll();
        model.addAttribute("clients",clients);
        List<Warehouse>warehouses=outPutService.getAllWarehouse();
        model.addAttribute("warehouses",warehouses);
        List<Currency>currencies=outPutService.getAllCurrency();
        model.addAttribute("currencies",currencies);
        List<Output> outputs=outPutService.getAll();
        List<Output> outputList = outputs.stream().sorted((o1, o2) -> o1.getClient().getName().compareTo(o2.getClient().getName())).
                collect(Collectors.toList());
        model.addAttribute("output",output);
        model.addAttribute("outputList",outputList);
        if(outputs.isEmpty()){
            model.addAttribute("message",new Response("Not found any client",false));
        }else
            model.addAttribute("message",new Response("Total client amount: "+outputs.size(),true));
     return "/output/outputEdite";
    }
    @PostMapping("/edite/{id}")
    public String editeOut(OutputDto output, Model model, @PathVariable Integer id){
           Response response= outPutService.edit(output,id);
        List<Client>clients=clientService.getAll();
        model.addAttribute("clients",clients);
        List<Warehouse>warehouses=outPutService.getAllWarehouse();
        model.addAttribute("warehouses",warehouses);
        List<Currency>currencies=outPutService.getAllCurrency();
        model.addAttribute("currencies",currencies);
        List<Output> outputs=outPutService.getAll();
//        List<Output> outputList = outputs.stream().sorted((o1, o2) -> o1.getClient().getName().compareTo(o2.getClient().getName())).
//                collect(Collectors.toList());
        model.addAttribute("outputList",outputs);
        return "redirect:/warehouse/output";
    }

}
