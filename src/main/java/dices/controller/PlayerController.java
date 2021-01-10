package dices.controller;

import dices.model.Game;
import dices.model.Player;
import dices.service.GameServiceImpl;
import dices.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PlayerController {

    @Autowired
    PlayerServiceImpl playerServiceImpl;
    @Autowired
    GameServiceImpl gameServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Show all the players
    @GetMapping("/players")
    public List<Player> getAllPlayers() {

        return playerServiceImpl.listPlayers();
    }

    //Add a new player with password JSON {"playerName":"erik","password":"password"}
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        System.out.println(player.getName());

        player.setPassword(passwordEncoder.encode(player.getPassword()));

        return playerServiceImpl.addPlayer(player);
    }

    //Change the name of a player
    @PutMapping("/players/{player_id}")
    ResponseEntity<Object> changePlayer(@RequestBody Player player, @PathVariable Long player_id) {

        return playerServiceImpl.changePlayer(player, player_id);
    }

    //Play one game
    @PostMapping("/players/{player_id}/games")
    public ResponseEntity<Object> playGame(@PathVariable(name="player_id")Long player_id){
        try {
            List<Game>listOfGames2 = gameServiceImpl.getGamesByPlayer2(player_id);
            int gamesPlayed = listOfGames2.size();
            return ResponseEntity.ok().body(playerServiceImpl.playGame(player_id, gamesPlayed));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data not accepted");
        }


    }

    // List all games of a player
    @GetMapping("/players/{player_id}/games")
    public ResponseEntity<Object> getGamesByPlayer(@PathVariable(name="player_id")Long player_id) {

        return gameServiceImpl.getGamesByPlayer(player_id);
    }

    //Delete all the games of a player
    @DeleteMapping("/players/{player_id}/games") //delete ALL the collars from a shop
    public ResponseEntity<Object> deleteGamesByPlayer(@PathVariable(name="player_id")Long player_id) {
        try {
            gameServiceImpl.deleteGamesByPlayer(player_id);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data not valid, wrong id");
        }
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