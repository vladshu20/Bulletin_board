package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    private static Logger logger = LogManager.getLogger(RegistrationController.class.getName());


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, @RequestParam String passwordConfirmation) {


        if (!passwordConfirmation.equals(user.getPassword())) {
            model.put("message", "password and confirmation must be the same");
            return "registration";
        }

        logger.info("adding user");
        if (!userService.addUser(user)) {
            model.put("message", "User exists");
            return "registration";
        }


        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            logger.info("succesed in activation");
            model.addAttribute("message", "User successfully activated");
        } else {
            logger.info("failed in activation");
            model.addAttribute("message", "Activation code is not found");
        }

        return "redirect:/login";

    }

}
