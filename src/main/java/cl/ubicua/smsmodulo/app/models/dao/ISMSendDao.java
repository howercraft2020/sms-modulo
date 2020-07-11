package cl.ubicua.smsmodulo.app.models.dao;

import cl.ubicua.smsmodulo.app.models.entity.SMSsend;

import org.springframework.data.repository.CrudRepository;


public interface ISMSendDao extends CrudRepository<SMSsend, Long>{


}