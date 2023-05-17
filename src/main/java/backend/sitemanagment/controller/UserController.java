package backend.sitemanagment.controller;


import backend.sitemanagment.dao.UserDAO;
import backend.sitemanagment.exception.UserNotFoundException;
import backend.sitemanagment.model.User;
import backend.sitemanagment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {

        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> save(@RequestBody User user){

        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
                if(user.getUserName() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/count")
    public ResponseEntity<?> count(){

        long user= userService.count();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


////    @GetMapping("/")
////    public ResponseEntity<?> getUsers() {
////
////        List<User> users = userService.getAllUsers();
////        return new ResponseEntity<>(users, HttpStatus.OK);
////    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User token){
//        String login= userService.login(token);
//        return new ResponseEntity<>(login, HttpStatus.CREATED);
//    }
//
//
////    @GetMapping("/findByUsername")
////    public ResponseEntity<?> findByUSer(){
////        String user = userService.findByUsername();
////        return new ResponseEntity<>(user, HttpStatus.OK);
////    }
}
