package backend.sitemanagment.dao;


import backend.sitemanagment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    public long count();
    public User findByUserNameAndPassword(String userName, String password);

    List<User> getAllUsers();

    public void save(User user);


}
