package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG= LogFactory.getLog(UserService.class);
    private UserRepository userRository;

    public UserService(UserRepository userRository) {
        this.userRository = userRository;
    }
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOG.info("USUARIO INSERTADO: "+user))
                .forEach(userRository::save);

    }
    public List<User> getAllUsers(){
        return userRository.findAll();
    }

    public User save(User newUser) {
        return userRository.save(newUser);
    }

    public void delete(long id) {

        userRository.delete(new User(id));
    }

    public User update(User newUser, long id) {
       return userRository.findById(id)
                .map(
                user -> {
                    user.setEmail(newUser.getEmail());
                    user.setBirthdate(newUser.getBirthdate());
                    user.setName(newUser.getName());
                    return userRository.save(user);
                }
        ).get();


    }
}
