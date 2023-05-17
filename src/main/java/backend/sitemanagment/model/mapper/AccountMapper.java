package backend.sitemanagment.model.mapper;

import backend.sitemanagment.model.Account;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        LogbackMDCAdapter csvRecord = new LogbackMDCAdapter();
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setScreen_name(rs.getString("screen_name"));
        account.setEmail(rs.getString("email"));
        account.setPhone_no(rs.getString("phone_no"));
        account.setPassword(rs.getString("password"));
        account.setStatus(rs.getString("status"));
        account.setType(rs.getString("type"));
        account.setApp_name(rs.getString("app_name"));
        account.setConsumer_key(rs.getString("consumer_key"));
        account.setConsumer_secret(rs.getString("consumer_secret"));
        account.setAccess_token(rs.getString("access_token"));
        account.setAccess_token_secret(rs.getString("access_token_secret"));
        account.setUsername(rs.getString("username"));
        account.setDescription(rs.getString("description"));
        return account;
    }
}
