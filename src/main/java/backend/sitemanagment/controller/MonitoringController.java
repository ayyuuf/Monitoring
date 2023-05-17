package backend.sitemanagment.controller;


import backend.sitemanagment.model.Monitoring;
import backend.sitemanagment.service.impl.MonitoringServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/monitoring")
public class MonitoringController {


    @Autowired
    private MonitoringServiceImpl monitoringService;

    @GetMapping("/")
    public ResponseEntity<?> getMonitorings() {

        List<Map<String,Object>> monitorings = monitoringService.getAllMonitorings();
        return new ResponseEntity<>(monitorings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMonitoringById(@PathVariable("id") int monitoring_id) {

        Monitoring monitoring = monitoringService.findById(monitoring_id);
        return new ResponseEntity<>(monitoring, HttpStatus.OK);
    }

    @GetMapping("/countService")
    public ResponseEntity<?> countService(){

        int monitoring= monitoringService.countService();
        return new ResponseEntity<>(monitoring, HttpStatus.OK);
    }

    @GetMapping("/countEngine")
    public ResponseEntity<?> countEngine(){

        int monitoring= monitoringService.countEngine();
        return new ResponseEntity<>(monitoring, HttpStatus.OK);
    }


    @PostMapping("/save-monitoring")
    public ResponseEntity<?> addMonitoring(@RequestBody Monitoring monitoring) {

        monitoringService.addMonitoring(monitoring);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-monitoring")
    public ResponseEntity<?> updateMonitoring(@RequestBody Monitoring monitoring) {

        monitoringService.updateMonitoring(monitoring);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-monitoring/{id}")
    public ResponseEntity<?> deleteMonitoring(@PathVariable("id") int monitoring_id) {

        monitoringService.deleteMonitoring(monitoring_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
