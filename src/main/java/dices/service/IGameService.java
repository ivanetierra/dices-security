package dices.service;

import dices.model.Game;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameService {

    //Metodos del CRUD game
    List<Game> listGames(); //List All

    ResponseEntity<Object> getGamesByPlayer(Long player_id);

    List<Game> getGamesByPlayer2(Long player_id);

    void deleteGamesByPlayer(Long player_id);
}


