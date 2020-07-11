package cl.ubicua.smsmodulo.app.models.service;

import cl.ubicua.smsmodulo.app.models.dao.ISMSendDao;
import cl.ubicua.smsmodulo.app.models.entity.SMSsend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ServiceSMSDaoImpl implements  IServiceSMSDao {
    @Autowired
    private ISMSendDao ismSendDao ;


    @Override
    @Transactional(readOnly = true)
    public List<SMSsend> findAll() {
        // TODO Auto-generated method stub
        return (List<SMSsend>) ismSendDao.findAll();
    }

    @Override
    @Transactional
    public void save(SMSsend smSsend) {
        ismSendDao.save(smSsend);

    }

    @Override
    @Transactional(readOnly = true)
    public SMSsend findOne(Long id) {
        // TODO Auto-generated method stub
        return ismSendDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ismSendDao.deleteById(id);

    }


}
