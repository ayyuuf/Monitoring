package backend.sitemanagment.service.impl;

import backend.sitemanagment.dao.AccountDAO;
import backend.sitemanagment.model.Account;
import backend.sitemanagment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<Account> getAllAccount() {
        return accountDAO.getAllAccount();
    }

    @Override
    public void addAccount(Account account) {
        accountDAO.addAccount(account);


    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    @Override
    public void deleteById(int id) {
        accountDAO.deleteById(id);
    }

    @Override
    public Account findByEmail(String email) {
        return accountDAO.findByEmail(email);
    }

    @Override
    public void readFile(MultipartFile file) throws IOException {
        accountDAO.readFile(file);


    }

    @Override
    public List<Map<String, Object>> countAll() {
        return accountDAO.countAll();
    }

    @Override
    public List<Map<String, Object>> countByStatus() {
        return (List<Map<String, Object>>) accountDAO.countByStatus();
    }

    @Override
    public List<Map<String, Object>> dailyUpdate(Date date) {
        return accountDAO.dailyUpdate(date);
    }


}
