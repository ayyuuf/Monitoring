package backend.sitemanagment.dao;



import backend.sitemanagment.model.Monitoring;

import java.util.List;
import java.util.Map;

public interface MonitoringDAO {

    List<Map<String, Object>> getAllMonitorings();

    Monitoring findById(int monitoring_id);

    public int countService();

    public int countEngine();
    public void addMonitoring(Monitoring monitoring);
    public void updateMonitoring(Monitoring monitoring);
    public void deleteMonitoring(int monitoring_id);


}
