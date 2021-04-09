package com.example.Capstone.services;

import com.example.Capstone.entities.Role;
import com.example.Capstone.entities.User;
import com.example.Capstone.repositories.RoleRepository;
import com.example.Capstone.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    //HAS A relationship
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Iterable<User> findAllUsers(){
    	return userRepository.findAll();
    }
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public User findUserByName(String name) {
    	return userRepository.findByName(name);
    }
    
    public User findById(int id) {
    	return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    public User updateInfo(User user,String firstName,String lastName,String email) {
    	if(user==null&&firstName==null&&lastName==null&&email==null) {
    		return null;
    	}
    	else {
    		user.setEmail(email);
    		user.setName(firstName);
    		user.setLastName(lastName);
    		userRepository.save(user);
    		return user;
    	}
    }
    public User updatePassword(User user, String password, String confirmPassword) {
    	if(password.equals(confirmPassword)) {
    		user.setPassword(bCryptPasswordEncoder.encode(password));
    		return user;
    	}
    	return null;
    }

}