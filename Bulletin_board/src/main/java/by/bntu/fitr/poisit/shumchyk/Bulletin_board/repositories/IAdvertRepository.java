package by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories;


import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAdvertRepository extends JpaRepository<Advert, Long> {
    List<Advert> findByTag(String tag);
}
