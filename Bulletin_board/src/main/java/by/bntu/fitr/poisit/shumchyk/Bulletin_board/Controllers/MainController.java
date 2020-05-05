package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.dao.IAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private IAdvertRepository advertRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/adverts")
    public String adverts() {
        return "adverts";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
