package backend.sitemanagment.controller;


import backend.sitemanagment.model.Server;
import backend.sitemanagment.service.impl.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    ServerServiceImpl serverService;

    @PostMapping("/save-server")
    public ResponseEntity<?> addServer(@RequestBody Server server) {

        serverService.addServer(server);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-server")
    public ResponseEntity<?> updateServer(@RequestBody Server server) {

        serverService.updateServer(server);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-server/{id}")
    public ResponseEntity<?> deleteServer(@PathVariable("id") int server_id) {

        serverService.deleteServer(server_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllServers() {

        List<Server>servers =serverService.getAllServers();
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findServerByServer_id(@PathVariable("id")int server_id) {

        Server server = serverService.findServerByServer_id(server_id);
        return new ResponseEntity<>(server, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){

        int server= serverService.count();
        return new ResponseEntity<>(server, HttpStatus.OK);
    }
}
