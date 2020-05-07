package by.bntu.fitr.poisit.shumchyk.Bulletin_board.dao;


import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdvertRepository extends JpaRepository<Advert, Long> {
}
