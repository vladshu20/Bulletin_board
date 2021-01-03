package by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories;


import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAdvertRepository extends JpaRepository<Advert, Long> {

    //TODO: add normal query which will select adverts with given tags
    //@NatiQuery("SELECT adv.id, adv.filename, adv.text, adv.user_id from advert adv INNER JOIN advert_tag at ON at.advert_id where at.tag = ?#{[0]}")
    List<Advert> findByTags(String tag);

    List<Advert> findByAuthorId(Long userId);

}
