package backend.sitemanagment.service.impl;

import backend.sitemanagment.dao.impl.MonitoringDAOImpl;
import backend.sitemanagment.model.Monitoring;
import backend.sitemanagment.service.MonitoringService;
import com.sun.jersey.api.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private MonitoringDAOImpl monitoringDAO;


    @Override
    public List<Map<String, Object>> getAllMonitorings() {

        return monitoringDAO.getAllMonitorings();
    }

    @Override
    public Monitoring findById(int monitoring_id) {
        return monitoringDAO.findById(monitoring_id);
    }

    @Override
    public int countService() {
        return monitoringDAO.countService();
    }

    @Override
    public int countEngine() {
        return monitoringDAO.countEngine();
    }


    @Override
    public void addMonitoring(Monitoring monitoring) {
        monitoringDAO.addMonitoring(monitoring);
    }

    @Override
    public void updateMonitoring(Monitoring monitoring) {
        monitoringDAO.updateMonitoring(monitoring);
    }

    @Override
    public void deleteMonitoring(int monitoring_id) {
        monitoringDAO.deleteMonitoring(monitoring_id);

    }
}
