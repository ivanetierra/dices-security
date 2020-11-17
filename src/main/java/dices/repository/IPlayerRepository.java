package dices.repository;

import dices.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByName(String name);

    Player findByName(String Name);
}
