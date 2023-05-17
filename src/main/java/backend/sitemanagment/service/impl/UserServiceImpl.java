package backend.sitemanagment.service.impl;

import backend.sitemanagment.dao.UserDAO;
import backend.sitemanagment.exception.UserNotFoundException;
import backend.sitemanagment.model.User;
import backend.sitemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO=userDAO;
    }
    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }


    @Override
    public User getUserByNameAndPassword(String userName, String password) throws UserNotFoundException {
        User user = userDAO.findByUserNameAndPassword(userName, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
    @Override
    public long count() {
        return userDAO.count();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
////
//    @Override
//    public void saveUser(User user) {
//        userDAO.saveUser(user);
//
//    }
//
//    @Override
//    public int count() {
//        return userDAO.count();
//    }
//
//    @Override
//    public User getById(int Id) {
//        return userDAO.getById(Id);
//    }
//
//    @Override
//    public String login(User token) {
//    return userDAO.login(token);

}

//    @Override
//    public User findByUsername(User user) {
//        return userDAO.findByUsername(user);
//    }



