package com.example.eboy_backend_2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.eboy_backend_2.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import com.example.eboy_backend_2.user.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("admin");

        User newUser = new User("admin", "admin@gmail.com", password);
        newUser.addRole("ROLE_ADMIN");
        User savedUser = repo.save(newUser);

        Set<String> myset = new HashSet<String>();
        myset.add("ROLE_USER");

        password = passwordEncoder.encode("1");
        User xenu = new User("Xenu", password, "Rigas", "Vas", "xenu@gmail.com", "6986798679", "Dafni", "Papapap", "123456798", myset);
        //xenu.addRole("ROLE_USER");
        User savedUser2 = repo.save(xenu);

        myset.add("ROLE_SELLER");
        myset.add("ROLE_BIDDER");

        User user = new  User("Gi Gina", password, "Geo", "Sar", "geo@gmail.com", "1234567890", "Lofos", "steperi", "123456789", myset);
        User savedUsr = repo.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getRoles()).hasSize(1);
        assertThat(savedUser2).isNotNull();
        assertThat(savedUser2.getRoles()).hasSize(1);
        assertThat(savedUsr).isNotNull();
        assertThat(savedUsr.getRoles()).hasSize(3);
    }

    @Test
    public void testgetUserRoles(){
        User user = repo.findByUsername("Xenu").get();

        assertThat(user.getRoles().toString().equals("ROLE_USER"));
    }

}
