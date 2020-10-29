package dices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dices.model.Player;

import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByName(String name);

}
