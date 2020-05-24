package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.services.AdvertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private AdvertService advertService;


    private static Logger logger = LogManager.getLogger(MainController.class.getName());


    @GetMapping("/")
    public String home(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {

        logger.info("getting main page");

        Iterable<Advert> adverts;

        logger.info("checking if there are some filters to be applied to adverts");
        if (filter != null && !filter.isEmpty()) {
            logger.info("filter has been applied");
            adverts = advertService.getAdvertsByTags(filter);

        } else {
            logger.info("no filters have been applied");
            adverts = advertService.getAllAdverts();
        }

        logger.info("adding to frontend");
        model.addAttribute("adverts", adverts);
        model.addAttribute("filter", filter);
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}

