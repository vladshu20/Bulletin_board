package by.bntu.fitr.poisit.shumchyk.Bulletin_board.services;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories.IAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {

    private IAdvertRepository advertRepository;

    @Autowired
    public void setAdvertRepository(IAdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }
}
