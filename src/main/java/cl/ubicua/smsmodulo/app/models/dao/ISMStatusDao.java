package cl.ubicua.smsmodulo.app.models.dao;
import cl.ubicua.smsmodulo.app.models.entity.SMSstatus;
import org.springframework.data.repository.CrudRepository;


public interface ISMStatusDao extends CrudRepository<SMSstatus, Long>{


}