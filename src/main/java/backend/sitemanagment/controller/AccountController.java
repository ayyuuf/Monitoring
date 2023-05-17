package backend.sitemanagment.controller;

import backend.sitemanagment.model.Account;
import backend.sitemanagment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<?> getAllAccount() {

        List<Account> accounts = accountService.getAllAccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @PostMapping("/save-account")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {

         accountService.addAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-account")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {

        accountService.updateAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

        accountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {

        Account account = accountService.findByEmail(email);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> readFile(
            @RequestParam("file")MultipartFile file) {

        if (isCSVFile(file)) {
            try {

                accountService.readFile(file);
                return ResponseEntity.ok("Success");
            } catch (Exception e)  {
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("file isn't csv", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private boolean isCSVFile(MultipartFile file){
        if(!"text/csv".equals(file.getContentType())){
            return false;
        }
        return true;
    }

    @GetMapping("/countAll")
    public ResponseEntity<?> countAll() {

        List<Map<String, Object>> account = accountService.countAll();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/countByStatus")
    public ResponseEntity<?> countByStatus() {

        List<Map<String, Object>> account = accountService.countByStatus();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/dailyUpdate")
    public ResponseEntity<?> dailyUpdate() {

        List<Map<String, Object>> account = accountService.dailyUpdate(new Date());
        return new ResponseEntity<>(account, HttpStatus.OK);

    }
}
