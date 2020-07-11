package cl.ubicua.smsmodulo.app.models.service;


import cl.ubicua.smsmodulo.app.models.entity.SMS;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class SMSServiceAWS {



    @Value("#{systemEnvironment['AWS_ACCESS_KEY']}")
    private String AWS_ACCESS_KEY;

    @Value("#{systemEnvironment['AWS_SECRET_KEY']}")
    private String AWS_SECRET_KEY;

    public void enviar(SMS sms){

        AWSCredentials credentials = new BasicAWSCredentials(
                AWS_ACCESS_KEY,
                AWS_SECRET_KEY
        );



        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<String, MessageAttributeValue>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("Ubicua") //The sender ID shown on the device.
                .withDataType("String"));

        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
                .withStringValue("0.02946") //Sets the max price to 0.50 USD.
                .withDataType("Number"));


        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional") //Sets the type to promotional.
                .withDataType("String"));


        AmazonSNS snsClient = AmazonSNSClient.builder()
                .withRegion("us-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        PublishResult result = snsClient.publish(new PublishRequest()
                .withMessage(sms.getMensaje())
                .withPhoneNumber(sms.getNumero())
        );
        System.out.println(result); // Prints the message ID.


    }

    public void send_bulk(ArrayList<String> numeros, String topico, String mensaje){

        String hola;

    }

}
