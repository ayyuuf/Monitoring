package backend.sitemanagment.service;



import backend.sitemanagment.exception.UserNotFoundException;
import backend.sitemanagment.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

//    public void save(User user);
//
//    public int count();
//
//    public User getById(int Id);
//
//    public String login(User token);

//    public User findByUsername(User user);

    public void saveUser(User user);
    public User getUserByNameAndPassword(String userName, String password) throws UserNotFoundException, UserNotFoundException;
    public long count();
}