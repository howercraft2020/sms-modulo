package cl.ubicua.smsmodulo.app.controllers;

import cl.ubicua.smsmodulo.app.models.entity.SMS;
import cl.ubicua.smsmodulo.app.models.service.SMSServiceAWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
public class SMSControllerAWS {

    @Autowired
    private SMSServiceAWS smsServiceAWS;




    @GetMapping(value = {"/","/index"})
    public String index(Map<String,Object> model){

        SMS sms = new SMS();
        model.put("sms",sms);
        model.put("titulo","Enviando SMS con AWS");

        return "index";
    }
    @PostMapping("/index")
    public String enviar(SMS sms, BindingResult result, Model model, SessionStatus status){

        if(result.hasErrors()){
            model.addAttribute("titulo","Enviando SMS con AWS");
            return "index";
        }
        smsServiceAWS.enviar(sms);
        status.setComplete();
        return "redirect:/index";
    }

}
