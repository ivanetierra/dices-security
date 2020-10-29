package dices.service;

import dices.model.Game;

import java.util.List;

public interface IGameService {

    //Metodos del CRUD game
    List<Game> listGames(); //List All

    List<Game> getGamesByPlayer(Long player_id);

    void deleteGamesByPlayer(Long player_id);
}


