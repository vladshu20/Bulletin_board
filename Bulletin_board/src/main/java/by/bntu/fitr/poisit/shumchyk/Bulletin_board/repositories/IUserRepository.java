package by.bntu.fitr.poisit.shumchyk.Bulletin_board.repositories;

import by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    User findByActivationCode(String code);

//    User deleteUser(User );
}
