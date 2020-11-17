package dices.repository;

import dices.model.Game;
import dices.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IGameRepository extends JpaRepository<Game, Long> {

    List<Game> findByPlayer(Player player);

    @Transactional
    void deleteByPlayer(Player player);

}



