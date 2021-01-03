package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Controllers.MainController;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Tag;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdvertService {

    private IAdvertRepository advertRepository;

    @Autowired
    public void setAdvertRepository(IAdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    private static Logger logger = LogManager.getLogger(MainController.class.getName());

    @Value("${upload.path}")
    private String uploadPath;


    public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }

    public List<Advert> getAdvertsByTags(String tag) {
        List<Advert> advetrs = new ArrayList<>();
        for (Advert adv : advertRepository.findAll()) {
            if (adv.getTags().contains(Tag.valueOf(tag))) {

                advetrs.add(adv);
            }
        }
        return advetrs;
      // return advertRepository.findByTags(tag);
    }


    public List<Advert> getAdvertsByUserId(Long id) {
        return advertRepository.findByAuthorId(id);
    }

    public void deleteAdvert(Advert advert) {
        advertRepository.delete(advert);
    }

    public void addAdvert(User user,
                          String text,
                          Map<String, String> formTags,
                          MultipartFile file) throws IOException {
        Set<String> tags = Arrays.stream(Tag.values()).map(Tag::name).collect(Collectors.toSet());
        Set<Tag> fromModelTags = new HashSet<>();
        for (String tag : formTags.keySet()) {
            if (tags.contains(tag)) {
                fromModelTags.add(Tag.valueOf(tag));
            }

        }
        Advert advert = new Advert(text, user, fromModelTags);


        if (file != null && !file.getOriginalFilename().isEmpty()) {
            logger.info("creating file upload directory");
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

        }

    }
}
