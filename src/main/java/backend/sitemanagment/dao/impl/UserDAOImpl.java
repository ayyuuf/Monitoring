package backend.sitemanagment.dao.impl;


import backend.sitemanagment.dao.UserDAO;
import backend.sitemanagment.model.Account;
import backend.sitemanagment.model.User;
import backend.sitemanagment.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * from user";
        RowMapper<User> rowMapper = new UserMapper();
        List<User> list = jdbcTemplate.query(query, rowMapper);
        return list;
    }

    @Override
    public void save(User user) {
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        System.out.println(hashedPassword);
        String query = ("INSERT INTO user( user_name, password) VALUES('" + user.getUserName() + "','" + user.getPassword() + "')");
        jdbcTemplate.update(query);
        System.out.println(query);
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM user";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        String query = ("SELECT * FROM user WHERE user_name=? and password=?");
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(query, rowMapper, userName, password);
        return user;
    }


}
