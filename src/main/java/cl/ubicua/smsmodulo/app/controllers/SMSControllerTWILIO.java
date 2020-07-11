package cl.ubicua.smsmodulo.app.controllers;

import cl.ubicua.smsmodulo.app.models.entity.SMS;
import cl.ubicua.smsmodulo.app.models.entity.SMSsend;
import cl.ubicua.smsmodulo.app.models.entity.SMSstatus;
import cl.ubicua.smsmodulo.app.models.service.IServiceSMSDao;
import cl.ubicua.smsmodulo.app.models.service.IServiceStatusDao;
import cl.ubicua.smsmodulo.app.models.service.SMSServiceTWILIO;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Controller
public class SMSControllerTWILIO {

    @Autowired
    SMSServiceTWILIO service;


    @Autowired
    private SimpMessagingTemplate webSocket;

    @Autowired
    private IServiceStatusDao serviceStatusDao;

    @Autowired
    private IServiceSMSDao serviceSMSDao;



    private final String  TOPIC_DESTINATION = "/topic/sms";

    @GetMapping(value = "/twiliosms")
    public String index(Map<String,Object> model){

        SMS sms = new SMS();
        model.put("sms",sms);
        model.put("titulo","Enviando SMS con TWILIO");

        return "twiliosms";
    }


    @RequestMapping(value = "/sms", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String  smsSubmit(@RequestBody SMS sms) {


        try{


            service.send(sms);
            /*Llenamos el Entity*/
            SMSsend ssend = new SMSsend();

            ssend.setNumero(sms.getNumero());
            ssend.setMensaje(sms.getMensaje());
            ssend.setFecha(new Date());

            serviceSMSDao.save(ssend);
        }
        catch(ApiException e){

            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
         webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getNumero());
        return "redirect:/twiliosms";
    }

    @RequestMapping(value = "/smscallback", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String smsCallback(@RequestBody MultiValueMap<String, String> map) {


        service.receive(map);

        SMSstatus smSstatus = new SMSstatus();

        smSstatus.setSmssid(map.getFirst("SmsSid"));
        smSstatus.setStatus(map.getFirst("SmsStatus"));
        smSstatus.setMessagestatus(map.getFirst("MessageStatus"));
        smSstatus.setTo(map.getFirst("To"));
        smSstatus.setMessageid(map.getFirst("MessageSid"));
        smSstatus.setAccountid(map.getFirst("AccountSid"));
        smSstatus.setDesde(map.getFirst("From"));
        smSstatus.setFecha(new Date());

        serviceStatusDao.save(smSstatus);


        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a callback request! Here are the contents: "+map.toString());

        return "redirect:/twiliosms";

    }





    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}
