
package dices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dices.model.Game;
import dices.service.GameServiceImpl;

import java.util.List;

@RestController
@RequestMapping
public class GameController {

    @Autowired
    GameServiceImpl gameServiceImpl;

    @GetMapping("/games") //show all games
    public List<Game> all() {

        return gameServiceImpl.listGames();
    }



}

