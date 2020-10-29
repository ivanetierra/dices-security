package dices.controller;

import dices.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dices.model.Game;
import dices.model.Player;
import dices.service.PlayerServiceImpl;

import java.util.List;

@RestController
@RequestMapping
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;
    @Autowired
    GameServiceImpl gameServiceImpl;

    //Show all the players
    @GetMapping("/players")
    public List<Player> getAllPlayers() {

        return playerServiceImpl.listPlayers();
    }

    //Add a new player
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        System.out.println(player.getName());

        return playerServiceImpl.addPlayer(player);
    }

    //Change the name of a player
    @PutMapping("/players/{player_id}")
    Player changePlayer(@RequestBody Player player, @PathVariable Long player_id) {

        return playerServiceImpl.changePlayer(player, player_id);
    }

    //Play one game
    @PostMapping("/players/{player_id}/games")
    public Game playGame(@PathVariable(name="player_id")Long player_id){

        List<Game>listOfGames = gameServiceImpl.getGamesByPlayer(player_id);
        int gamesPlayed = listOfGames.size();

        return playerServiceImpl.playGame(player_id, gamesPlayed);
    }

    // List all games of a player
    @GetMapping("/players/{player_id}/games")
    public List<Game> getGamesByPlayer(@PathVariable(name="player_id")Long player_id) {

        return gameServiceImpl.getGamesByPlayer(player_id);
    }

    //Delete all the games of a player
    @DeleteMapping("/players/{player_id}/games") //delete ALL the collars from a shop
    public void deleteGamesByPlayer(@PathVariable(name="player_id")Long player_id) {

        gameServiceImpl.deleteGamesByPlayer(player_id);
    }

    //Get ranking from best rate to worst
    @GetMapping("/players/ranking")
    public List<Player> getRanking() {

        return playerServiceImpl.getRanking();
    }

    //Get player with lowest rate
    @GetMapping("/players/loser")
    public Player getLoser() {

        return playerServiceImpl.getLoser();
    }

    //Get player with highest rate
    @GetMapping("/players/winner")
    public Player getWinner() {

        return playerServiceImpl.getWinner();
    }
}