package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.services.AdvertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private IAdvertRepository advertRepository;
    @Autowired
    private AdvertService advertService;

    private static Logger logger = LogManager.getLogger(MainController.class.getName());


    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String home(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {

        logger.info("getting main page");

        Iterable<Advert> adverts = advertRepository.findAll();

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
    public String adverts(@RequestParam(required = false, defaultValue = "") String filter,
                          Model model) {

        logger.info("getting adverts page");

        Iterable<Advert> adverts;

        logger.info("checking if there are some filters to be applied to adverts");
        if (filter != null && !filter.isEmpty()) {
            adverts = advertService.getAdvertsByTag(filter);
        } else {
            adverts = advertService.getAllAdverts();
        }

        logger.info("adding to frontend");
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

        Advert advert = new Advert(text, user, tag);

        logger.info("adding new image to advert");
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }


            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            try {

                advert.setFilename(resultFilename);
                advertRepository.save(advert);
            } catch (Exception e) {
                // Print the wrapper exception:
                System.out.println("Wrapper exception: " + e);

                // Print the 'actual' exception:
                System.out.println("Underlying exception: " + e.getCause());
            }


            Iterable<Advert> adverts = advertService.getAllAdverts();
            logger.info("passing adverts to frontend");
            model.put("adverts", adverts);


        }

        return "adverts";
    }
}
