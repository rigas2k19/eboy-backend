package com.example.eboy_backend_2.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserService(UserRepository userRepo){ this.userRepo = userRepo;}

    List<User> all() { return this.userRepo.findAll();}

    User newUser(@RequestBody User newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return this.userRepo.save(newUser);
    }

    User one(@PathVariable String id){return this.userRepo.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));}

    User approveUser(@RequestBody User newUser, @PathVariable String username){
        return this.userRepo.findById(username)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setName(newUser.getName());
                    user.setLastname(newUser.getLastname());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setAddress(newUser.getAddress());
                    user.setLocation(newUser.getLocation());
                    user.setAfm(newUser.getAfm());
                    user.setRoles((newUser.getRoles()));
                    user.setApproved(true);             //this user is now approved by the admin
                    return userRepo.save(newUser);
                })
                .orElseGet(() -> {
                    newUser.setUsername(username);
                    return this.userRepo.save(newUser);
                });
    }

    void deleteUser(@PathVariable String username) { this.userRepo.deleteById(username); }

    List<String> getUsernames(){return this.userRepo.GetUsernames();}

}
