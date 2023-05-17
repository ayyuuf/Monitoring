package backend.sitemanagment.dao.impl;


import backend.sitemanagment.dao.AccountDAO;
import backend.sitemanagment.model.Account;
import backend.sitemanagment.model.mapper.AccountMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Client client = Client.create();

    @Override
    public List<Account> getAllAccount() {
        String query = "SELECT * FROM account";
        RowMapper<Account> rowMapper = new AccountMapper();
        List<Account> list = jdbcTemplate.query(query, rowMapper);
        return list;
    }



    @Override
    public void addAccount(Account account) {
        // Check screen name
        String query = "SELECT * from account where email='" + account.getEmail() + "' and  type='" + account.getType() + "'";
        System.out.println(query);
        List<Map<String, Object>> email = jdbcTemplate.queryForList(query);
        System.out.println(email);
        if (email.size() == 0) {
            // Insert account
            query = "INSERT INTO account( screen_name, email, phone_no, password, status, type, app_name, consumer_key, consumer_secret, access_token, access_token_secret, username, description) VALUES('" + account.getScreen_name() + "','" + account.getEmail() + "','" + account.getPhone_no() + "','" + account.getPassword() + "','" + account.getStatus() + "','" + account.getType() + "','" + account.getApp_name() + "','" + account.getConsumer_key() + "','" + account.getConsumer_secret() + "','" + account.getAccess_token() + "', '" + account.getAccess_token_secret() + "', '" + account.getUsername() + "', '" + account.getDescription() + "')";
            System.out.println(query);
            jdbcTemplate.update(query);
            String url = env.getProperty("es.url") + env.getProperty("es.index.account")+"/_doc";
            System.out.println(url);
            WebResource webResource = client
                    .resource(url);

            String input = "{\n" +
                    "  \"screen_name\":\"" + account.getScreen_name() + "\",\n" +
                    "  \"email\":\"" + account.getEmail() + "\",\n" +
                    "  \"phone_no\":\"" + account.getPhone_no() + "\",\n" +
                    "  \"password\":\"" + account.getPassword() + "\",\n" +
                    "  \"type\":\"" + account.getType() + "\",\n" +
                    "  \"status\":\"" + account.getStatus() + "\",\n" +
                    "  \"app_name\":\"" + account.getApp_name() + "\",\n" +
                    "  \"consumer_key\":\"" + account.getConsumer_key() + "\",\n" +
                    "  \"consumer_secret\":\"" + account.getConsumer_secret() + "\",\n" +
                    "  \"access_token\":\"" + account.getAccess_token() + "\",\n" +
                    "  \"access_token_secret\":\"" + account.getAccess_token_secret() + "\",\n" +
                    "  \"description\": \"" + account.getDescription() + "\",\n" +
                    "  \"username\": \"" + account.getUsername() + "\"\n" +
                    "}";

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        } else {
            // Condition data already exist
            String massage = "Please Use another email";

        }

    }


    @Override
    public void updateAccount(Account account) {
        String query = "UPDATE account SET screen_name='" + account.getScreen_name() + "', email= '" + account.getEmail() + "', phone_no='" + account.getPhone_no() + "' , password ='" + account.getPassword() + "', status ='" + account.getStatus() + "', type='" + account.getType() + "' ,app_name='" + account.getApp_name() + "' ,consumer_key='" + account.getConsumer_key() + "' ,consumer_secret='" + account.getConsumer_secret() + "' ,access_token='" + account.getAccess_token() + "', access_token_secret='" + account.getAccess_token_secret() + "' , username='" + account.getUsername() + "', description='" + account.getDescription() + "' WHERE id='" + account.getId() + "'";
        System.out.println(query);
        jdbcTemplate.update(query);
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM account WHERE id=?";
        jdbcTemplate.update(query, id);

    }

    @Override
    public Account findByEmail(String email) {
        String query = "SELECT * FROM account WHERE email = ?";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
        Account account = jdbcTemplate.queryForObject(query, rowMapper, email);
        return account;
    }

    @Override
    public void readFile(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(';').parse(fileReader);
        List<Map<String, Object>> list = new ArrayList<>();

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            Map<String, Object> m = new HashMap<>();
            if (!csvRecord.get(0).isEmpty()) {
                m.put("screen_name", csvRecord.get(0));
                m.put("email", csvRecord.get(1));
                m.put("phone_no", csvRecord.get(2));
                m.put("password", csvRecord.get(3));
                m.put("status", csvRecord.get(4));
                m.put("type", csvRecord.get(5));
                m.put("app_name", csvRecord.get(6));
                m.put("consumer_key", csvRecord.get(7));
                m.put("consumer_secret", csvRecord.get(8));
                m.put("access_token", csvRecord.get(9));
                m.put("access_token_secret", csvRecord.get(10));
                m.put("username", csvRecord.get(11));
                m.put("description", csvRecord.get(12));

                list.add(m);
            }
        }
        for (Map<String, Object> data :
                list) {
            String query = "INSERT INTO account( screen_name, email, phone_no, password, status, type, app_name, consumer_key, consumer_secret, access_token, access_token_secret, username, description) VALUES('" + data.get("screen_name").toString() + "','" + data.get("email").toString() + "','" + data.get("phone_no").toString() + "','" + data.get("password").toString() + "','" + data.get("status").toString() + "','" + data.get("type").toString() + "','" + data.get("app_name").toString() + "','" + data.get("consumer_key").toString() + "','" + data.get("consumer_secret").toString() + "','" + data.get("access_token").toString() + "', '" + data.get("access_token_secret").toString() + "', '" + data.get("username").toString() + "', '" + data.get("description").toString() + "')";
            System.out.println(query);
            jdbcTemplate.update(query);
            String url = env.getProperty("es.url") + env.getProperty("es.index.account")+"/_doc";
            WebResource webResource = client
                    .resource(url);
            String input = "{\n" +
                    "  \"screen_name\":\"" + data.get("screen_name").toString() + "\",\n" +
                    "  \"email\":\"" + data.get("email".toString()) + "\",\n" +
                    "  \"phone_no\":\"" + data.get("phone_no").toString() + "\",\n" +
                    "  \"password\":\"" + data.get("password").toString() + "\",\n" +
                    "  \"type\":\"" + data.get("type").toString() + "\",\n" +
                    "  \"status\":\"" + data.get("status".toString()) + "\",\n" +
                    "  \"app_name\":\"" + data.get("app_name").toString() + "\",\n" +
                    "  \"consumer_key\":\"" + data.get("consumer_key").toString() + "\",\n" +
                    "  \"consumer_secret\":\"" + data.get("consumer_secret").toString() + "\",\n" +
                    "  \"access_token\":\"" + data.get("access_token").toString() + "\",\n" +
                    "  \"access_token_secret\":\"" + data.get("access_token_secret").toString() + "\",\n" +
                    "  \"description\": \"" + data.get("description").toString() + "\",\n" +
                    "  \"username\": \"" + data.get("username").toString() + "\"\n" +
                    "}";

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        }
    }

    @Override
    public List<Map<String, Object>> countAll() {
        String query = "SELECT type, COUNT(*) as total FROM account GROUP BY type";
        return jdbcTemplate.queryForList(query);
    }
    @Override
    public List<Map<String, Object>> countByStatus() {
        List<Map<String, Object>> data;
        {
            String query = "SELECT COUNT(*) as total, type, status FROM account GROUP BY status, type";
            data = jdbcTemplate.queryForList(query);
        }

        Map<String, Object> raw = new LinkedHashMap<>();

        for (Map<String, Object> d : data) {
            List<Map<String, Object>> value = new ArrayList<>();
            if (raw.get(d.get("type").toString()) != null) {
                value = (ArrayList<Map<String, Object>>) raw.get(d.get("type").toString());
            }

            Map<String, Object> mapValue = new LinkedHashMap<>();
            mapValue.put("name", d.get("status").toString());
            mapValue.put("count", Integer.parseInt(d.get("total").toString()));

            value.add(mapValue);

            raw.put(d.get("type").toString(), value);
        }

        List<Map<String, Object>> result  = new ArrayList<>();
        for (Map.Entry<String, Object> entry : raw.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", entry.getKey());
            map.put("statistic", entry.getValue());
            result.add(map);
        }

        return result;
    }
    @Override
    public List<Map<String, Object>> dailyUpdate(Date date) {
        String query = "SELECT  COUNT(*) as total,type, time FROM account WHERE time >= DATE_SUB(DATE(NOW()), INTERVAL 1 DAY) GROUP BY DATE(time),type ORDER BY (time)";
        return jdbcTemplate.queryForList(query);
    }
//        List<Map<String, Object>> data;
//        {
//            String query = "SELECT  COUNT(*) as total,type, time FROM account WHERE time >= DATE_SUB(DATE(NOW()), INTERVAL 1 DAY) GROUP BY DATE(time),type ORDER BY (time)";
//            data = jdbcTemplate.queryForList(query);
//        }
//
////        LocalDate currentDate = LocalDate.now();
////        // Define a date formatter
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        // Print the dates for the last 7 days
////        for (int i = 0; i < 7; i++) {
////            String formattedDate = currentDate.minusDays(i).format(formatter);
////            System.out.println(formattedDate);
////        }
//
//
//        Map<String, Object> raw = new LinkedHashMap<>();
//
//        for (Map<String, Object> d : data) {
//            List<Map<String, Object>> value = new ArrayList<>();
//            if (raw.get(d.get("type").toString()) != null) {
//                value = (ArrayList<Map<String, Object>>) raw.get(d.get("type").toString());
//            }
//
//            Map<String, Object> mapValue = new LinkedHashMap<>();
//            mapValue.put("date", d.get("time").toString());
//            mapValue.put("total", Integer.parseInt(d.get("total").toString()));
//
//            value.add(mapValue);
//
//            raw.put(d.get("type").toString(), value);
//        }
//
//        List<Map<String, Object>> result  = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : raw.entrySet()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("type", entry.getKey());
//            map.put("data", entry.getValue());
//            result.add(map);
//        }
//        return result;
//    }


//    @Override
//    public List<Map<String, Object>> dailyUpdate(Date date) {
//        List<Map<String, Object>> data;
//        {
//            String query = "SELECT  COUNT(*) as total,type, time FROM account WHERE time >= DATE_SUB(DATE(NOW()), INTERVAL 7 DAY) GROUP BY DATE(time),type ORDER BY (time)";
//            data = jdbcTemplate.queryForList(query);
//        }
//
//        LocalDate currentDate = LocalDate.now();
//        // Define a date formatter
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        // Print the dates for the last 7 days
//        for (int i = 0; i < 7; i++) {
//            String formattedDate = currentDate.minusDays(i).format(formatter);
//            System.out.println(formattedDate);
//        }
//
//
//        Map<String, Object> raw = new LinkedHashMap<>();
//
//        for (Map<String, Object> d : data) {
//            List<Map<String, Object>> value = new ArrayList<>();
//            if (raw.get(d.get("type").toString()) != null) {
//                value = (ArrayList<Map<String, Object>>) raw.get(d.get("type").toString());
//            }
//
//            Map<String, Object> mapValue = new LinkedHashMap<>();
//            mapValue.put("date", d.get("time").toString());
//            mapValue.put("total", Integer.parseInt(d.get("total").toString()));
//
//            value.add(mapValue);
//
//            raw.put(d.get("type").toString(), value);
//        }
//
//        List<Map<String, Object>> result  = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : raw.entrySet()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("type", entry.getKey());
//            map.put("data", entry.getValue());
//            result.add(map);
//        }
//        return result;
//    }


}
