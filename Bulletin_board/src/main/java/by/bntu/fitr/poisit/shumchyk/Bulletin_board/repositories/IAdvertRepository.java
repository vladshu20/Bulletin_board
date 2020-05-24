package by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories;


import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Tag;
import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IAdvertRepository extends JpaRepository<Advert, Long> {
//    List<Advert> findByTags(Set<Tag> tags);
    List<Advert> findByTags(String tag);
    List<Advert> findByAuthorId(Long userId);

}
