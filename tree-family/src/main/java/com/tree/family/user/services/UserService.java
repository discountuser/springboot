package com.tree.family.user.services;

import com.tree.family.user.dao.User_;
import com.tree.family.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User_> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User_> getByUserId(long userId){
        if(0!=userId){
            return userRepository.getByUserId(userId);
        }else{
            return Optional.empty();
        }
    }

    /**
     * @param emailAddress emailAddress
     * @return User_
     */
    public Optional<User_> getByEmailAddress(String emailAddress){
        if(null!=emailAddress){
            return userRepository.getByEmailAddress(emailAddress);
        }else{
            System.out.println("Empty userId");
            return null;
        }
    }


    public User_ addUser(User_ user){
        return  saveUser(user);
    }

    public User_ updateUser(User_ user){
        return  saveUser(user);
    }

    public void deleteUser(User_ user){
        userRepository.delete(user);
    }



    private User_ saveUser(User_ user){
        return userRepository.save(user);
    }




}
