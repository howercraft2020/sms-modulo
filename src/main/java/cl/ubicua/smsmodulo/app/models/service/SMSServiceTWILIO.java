package cl.ubicua.smsmodulo.app.models.service;

import cl.ubicua.smsmodulo.app.models.entity.SMS;
import com.twilio.rest.notify.v1.service.Notification;
import com.twilio.rest.notify.v1.service.Binding;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class SMSServiceTWILIO {

    @Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
    private String ACCOUNT_SID;

    @Value("#{systemEnvironment['TWILIO_AUTH_TOKEN']}")
    private String AUTH_TOKEN;

    @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
    private String FROM_NUMBER;


    @Value("#{systemEnvironment['TWILIO_SERVICE_SID']}")
    private String SERVICE_SID;

    @Value("#{systemEnvironment['TWILIO_NOTI_SID']}")
    private String NOTI_SID;


    public void send_bulk( ArrayList<String> lista,String lista_id, String mensaje){


        List<Binding> toBinding = new ArrayList<>();
        for(int i=0;i<lista.size();i++) {
            toBinding.add(

                    Binding.creator(
                            NOTI_SID,
                            lista_id,
                            Binding.BindingType.SMS,
                            lista.get(i))
                            .create());
        }

        Notification notification = Notification
                .creator(NOTI_SID)
                .setBody(mensaje)
                .setIdentity(lista_id)
                .create();

        System.out.println(notification.getSid());
        System.out.println(notification.getIdentities().toString());


    }


    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // to number, from: always twilio snd a message



    Message message =
                Message.creator(new PhoneNumber(sms.getNumero()),
                        new PhoneNumber(FROM_NUMBER), sms.getMensaje()).setStatusCallback(URI.create("http://896658ca4184.ngrok.io/smscallback"))
                        .create();


    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
