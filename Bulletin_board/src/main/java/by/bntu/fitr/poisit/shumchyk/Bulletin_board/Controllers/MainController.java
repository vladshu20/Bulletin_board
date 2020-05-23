package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.services.AdvertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private IAdvertRepository advertRepository;
    @Autowired
    private AdvertService advertService;

    private static Logger logger = LogManager.getLogger(MainController.class.getName());





    @GetMapping("/")
    public String home(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {

        logger.info("getting main page");

        Iterable<Advert> adverts ;

        logger.info("checking if there are some filters to be applied to adverts");
        if (filter != null && !filter.isEmpty()) {
            logger.debug("filter has been applied");
            adverts = advertService.getAdvertsByTag(filter);
        } else {
            logger.debug("no filters have been applied");
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

    @GetMapping("/adverts")
    public String adverts(@AuthenticationPrincipal User user,
                          @RequestParam(required = false, defaultValue = "") String filter,
                          Model model) {

        logger.info("getting adverts page");

        Iterable<Advert> adverts;

        logger.info("checking if there are some filters to be applied to adverts");
        if (filter != null && !filter.isEmpty()) {
            logger.info("there are filters to be applied");
            adverts = advertService.getAdvertsByTag(filter);
        } else {
            logger.info("there are no filters to be applied");
            adverts = advertService.getAdvertsByUserId(user.getId());
        }

        logger.info("adding adverts to frontend");
        model.addAttribute("adverts", adverts);
        model.addAttribute("filter", filter);
        return "adverts";
    }

    @PostMapping("/adverts")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        logger.info("creating new advert");
        advertService.addAdvert(user, text, tag, file);


        Iterable<Advert> adverts = advertService.getAdvertsByUserId(user.getId());
        logger.info("passing adverts to frontend");
        model.put("adverts", adverts);

        return "adverts";
    }


}

