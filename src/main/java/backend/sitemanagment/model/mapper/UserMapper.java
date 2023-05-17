package backend.sitemanagment.model.mapper;

import backend.sitemanagment.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
