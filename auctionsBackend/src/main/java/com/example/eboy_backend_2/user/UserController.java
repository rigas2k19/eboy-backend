package com.example.eboy_backend_2.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    UserController(UserService userService){ this.userService = userService;}

    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<User> all(){ return this.userService.all();}

    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    User newUser(@RequestBody User newUser){ return this.userService.newUser(newUser);}

    @CrossOrigin(origins = "*")
    @GetMapping("/users/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    User one(@PathVariable String username){ return this.userService.one(username); }

    @CrossOrigin(origins = "*")
    @PutMapping("/users/approve/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    User ApproveUser(@RequestBody User newUser, @PathVariable String username){
        return this.userService.approveUser(newUser, username);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void deleteUser(@PathVariable String username){ this.userService.deleteUser(username);}


    @CrossOrigin(origins = "*")
    @GetMapping("/users/isApproved/{username}")
    User isApproved(@PathVariable String username){ return this.userService.one(username); }

    @CrossOrigin(origins = "*")
    @GetMapping("/usernames")
    List<String> getUsernames(){return this.userService.getUsernames();}
}