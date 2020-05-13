package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
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

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/adverts")
    public String adverts(@RequestParam(required = false, defaultValue = "") String filter,
                          Model model) {
        Iterable<Advert> adverts = advertRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            adverts = advertRepository.findByTag(filter);
        } else {
            adverts = advertRepository.findAll();
        }

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
        Advert advert = new Advert(text, user, tag);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            advert.setFilename(resultFilename);
        }

        advertRepository.save(advert);

        Iterable<Advert> adverts = advertRepository.findAll();

        model.put("adverts", adverts);

        return "main";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
