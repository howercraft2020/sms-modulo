package cl.ubicua.smsmodulo.app.models.service;

import cl.ubicua.smsmodulo.app.models.entity.SMSstatus;

import java.util.List;

public interface IServiceStatusDao {

    public List<SMSstatus> findAll();

    public void save(SMSstatus smSstatus);

    public SMSstatus findOne(Long id);


    public void delete(Long id);
}
