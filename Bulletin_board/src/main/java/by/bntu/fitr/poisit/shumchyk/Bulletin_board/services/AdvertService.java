package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AdvertService {

    private IAdvertRepository advertRepository;

    @Autowired
    public void setAdvertRepository(IAdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;


    public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }
    public List<Advert> getAdvertsByTag(String tag) {
        return advertRepository.findByTag(tag);
    }
    public List<Advert> getAdvertsByUserId(Long id) {
        return advertRepository.findByAuthorId(id);
    }

    public void deleteAdvert(Advert advert){
        advertRepository.delete(advert);
    }

//    public String addFile(MultipartFile file) throws IOException {
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + resultFileName));
//            return resultFileName;
//        }
//        return "";
//    }

}
