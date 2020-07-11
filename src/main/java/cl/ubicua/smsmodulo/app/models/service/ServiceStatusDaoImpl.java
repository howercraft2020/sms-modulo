package cl.ubicua.smsmodulo.app.models.service;

import cl.ubicua.smsmodulo.app.models.dao.ISMStatusDao;
import cl.ubicua.smsmodulo.app.models.entity.SMSstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceStatusDaoImpl  implements  IServiceStatusDao{

    @Autowired
    private ISMStatusDao ismStatusDao ;

    @Override
    @Transactional(readOnly = true)
    public List<SMSstatus> findAll() {
        // TODO Auto-generated method stub
        return (List<SMSstatus>) ismStatusDao.findAll();
    }

    @Override
    @Transactional
    public void save(SMSstatus smSstatus) {
        ismStatusDao.save(smSstatus);

    }

    @Override
    @Transactional(readOnly = true)
    public SMSstatus findOne(Long id) {
        // TODO Auto-generated method stub
        return ismStatusDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ismStatusDao.deleteById(id);

    }
}
