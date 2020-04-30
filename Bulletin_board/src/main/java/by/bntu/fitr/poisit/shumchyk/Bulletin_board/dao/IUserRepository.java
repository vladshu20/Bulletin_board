package by.bntu.fitr.poisit.shumchyk.Bulletin_board.dao;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository {
    User findByUsername(String username);
}
