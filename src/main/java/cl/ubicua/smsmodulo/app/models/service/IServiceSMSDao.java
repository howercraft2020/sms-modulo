package cl.ubicua.smsmodulo.app.models.service;

import cl.ubicua.smsmodulo.app.models.entity.SMSsend;

import java.util.List;
public interface IServiceSMSDao {


    public List<SMSsend> findAll();

    public void save(SMSsend smSsend);

    public SMSsend findOne(Long id);


    public void delete(Long id);

}

