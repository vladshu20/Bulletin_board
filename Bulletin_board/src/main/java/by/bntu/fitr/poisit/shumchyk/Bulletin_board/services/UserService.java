package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Role;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private MailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    private static Logger logger= LogManager.getLogger(UserService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        //user.setActive(false);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s. \n" +
                            "Welcome to Bullba! Please, visit next " +
                            "link: http://localhost:8080/activate/%s", user.getUsername(),
                    user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return userRepository.findByUsername(user.getUsername()) == null;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            logger.info("activation failed");
            return false;
        }
//        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);
        return true;

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String userName, Map<String, String> form) {
        {
            user.setUsername(userName);

            Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

            user.getRoles().clear();

            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
            userRepository.save(user);

        }
    }

    public void editProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isChanged = (email != null && !email.equals(userEmail)) ||
                (email != null && !userEmail.equals(email));
        if (isChanged) {

            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
            sendMessage(user);
        }
    }
}