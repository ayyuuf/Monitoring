package backend.sitemanagment.service;


import backend.sitemanagment.model.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountService {
    List<Account> getAllAccount();
    public void addAccount(Account account);
    public void updateAccount(Account account);

   public void deleteById(int id);

    public Account findByEmail(String email);
    public void readFile(MultipartFile file) throws IOException;
    public List<Map<String, Object>> countAll();
    public List<Map<String, Object>> countByStatus();
    public List<Map<String, Object>> dailyUpdate(Date date);
}
