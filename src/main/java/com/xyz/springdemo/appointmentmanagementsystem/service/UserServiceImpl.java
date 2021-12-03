package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dao.RoleRepository;
import com.xyz.springdemo.appointmentmanagementsystem.dao.UserRepository;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Role;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import com.xyz.springdemo.appointmentmanagementsystem.exception.MyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
            User theUser = findById(user.getId());
            if (theUser!=null) {
                userRepository.setUserInfoById(user.getUsername(),user.getPassword(), user.getId());
            }else {
                userRepository.save(user);
            }
    }


    @Override
    public User findById(int id) {
        Optional<User> result = userRepository.findById(id);
        User user=null;
        if(result.isPresent()){
            user = result.get();
        }
        return user;
    }

    @Override
    public void deleteById(int id) {
        User user = findById(id);
        roleRepository.deleteById(user.getId());
        userRepository.delete(user);

//        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteByUsername(String username) {
            User user = findByUsername(username);
            if(user==null){
                throw new RuntimeException("User not found with username"+username);
            }
            userRepository.deleteByUsername(username);
    }

    @Override
    public boolean usernameAlreadyExists(String email) {
        return (findByUsername(email) != null);
    }
}
