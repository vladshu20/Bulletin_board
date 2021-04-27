package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Role;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IUserRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    User user1;
    User user2;


    @Before
    void createUser(){
        user1 = new User("Aqua","123wqw3","qw@qw.com","AAAA");
        user2 = new User("Aqua","123wqw3","qw@qw.com","AAAA");
    }

    @Test
    void loadUserByRightUsername() {
        assertNotNull(userService.loadUserByUsername("kazah"));
    }

    @Test
    void loadUserByWrongUsername() {
        assertNull(userService.loadUserByUsername("ihor"));
    }

//    @Test
//    void addUser(){
//        user2.setActive(true);
//        user2.setRoles(Collections.singleton(Role.USER));
//        user2.setActivationCode(UUID.randomUUID().toString());
//        user2.setPassword(cryptPasswordEncoder.encode(user2.getPassword()));
//
//
//        assertEquals(user2.hashCode(),userService.addUser(user1));
//    }

}