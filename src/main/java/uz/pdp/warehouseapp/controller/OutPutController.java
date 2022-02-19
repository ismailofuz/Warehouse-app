//package uz.pdp.warehouseapp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import uz.pdp.warehouseapp.dto.ClientDto;
//import uz.pdp.warehouseapp.dto.OutputDto;
//import uz.pdp.warehouseapp.dto.Response;
//import uz.pdp.warehouseapp.entity.Client;
//import uz.pdp.warehouseapp.entity.Output;
//import uz.pdp.warehouseapp.service.OutPutService;
//
//
//
//@Controller
//@RequestMapping("/warehouse/output")
//public class OutPutController {
//    @Autowired
//    OutPutService outPutService;
//  // @GetMapping()
//
////    public String showOutPut(Model model){
////       model.addAttribute("outPutDto",new ClientDto());
////       List<Output> outputs=outPutService.getAll();
////        List<Output> outputList = outputs.stream().sorted((o1, o2) -> o1.getClient().getName().compareTo(o2.getClient().getName())).
////                collect(Collectors.toList());
////        model.addAttribute("outputs",outputs);
////
////        if(outputs.isEmpty()){
////            model.addAttribute("message",new Response("Not found any client",false));
////        }else
////            model.addAttribute("message",new Response("Total client amount: "+outputs.size(),true));
////        return "/outPut/outPutOperation";
////    }
//
////    @PostMapping(path = "/add")
////    public String addCategory(OutputDto outputDto, Model model){
////        Response response=outPutService.addOutPut(outputDto);
////        model.addAttribute("clientDto",new ClientDto());
////        List<Client> clients=clientService.getAll();
////        List<Client> collect = clients.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
////        model.addAttribute("clients",collect);
////        List<Client> chooseList = clients.stream().filter(Client::isActive).collect(Collectors.toList());
////        model.addAttribute("clientChoose",chooseList);
////        model.addAttribute("message",response);
////        return "/client/clientOperation";
////    }
//}
