package dices.service;

import dices.exceptions.PlayerExistsException;
import dices.exceptions.PlayerNotFoundException;
import dices.model.Game;
import dices.model.Player;
import dices.repository.IGameRepository;
import dices.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class PlayerServiceImpl implements IPlayerService {
	
	//Utilizo los metodos de la interface IPlayerRepository/IGameRepository
	//es como si instanciase
	@Autowired
	IPlayerRepository iPlayerRepository;
	@Autowired
	IGameRepository iGameRepository;


	@Override
	public List<Player> listPlayers() {
		return iPlayerRepository.findAll();
	}

	@Override
	public Player addPlayer(Player player) {

		if (iPlayerRepository.existsByName(player.getName())) {
			throw new PlayerExistsException(player.getName());
		} else {
			player.setDate(new Date());
			return iPlayerRepository.save(player);
		}
	}

	@Override
	public Player changePlayer(Player newPlayer, Long player_id) {

		if (iPlayerRepository.existsById(player_id)) {
		Player oldPlayer = iPlayerRepository.findById(player_id).get();
			if (iPlayerRepository.existsByName(newPlayer.getName())
					&& !newPlayer.getName().equalsIgnoreCase("anonymous")) {
				throw new PlayerExistsException(newPlayer.getName());
			}
			oldPlayer.setName(newPlayer.getName());
			return iPlayerRepository.save(oldPlayer);

		} else {
			throw new PlayerNotFoundException(player_id);
		}

	}

	@Override
	public Game playGame(Long player_id, int gamesPlayed) {
		Player myPlayer = iPlayerRepository.findById(player_id).get(); //.get()!?!?
		if (myPlayer == null) {
			throw new PlayerNotFoundException(player_id);

		} else {
			Game newGame = new Game(myPlayer);
			if (newGame.isWin()){
				myPlayer.setWins(myPlayer.getWins()+1);
			}
														//+1 because it starts with 0 games
		myPlayer.setRate((float)(myPlayer.getWins())/(gamesPlayed+1));
		iPlayerRepository.save(myPlayer);

		System.out.println("Wins: "+myPlayer.getWins());
		System.out.println("Games played: "+(gamesPlayed+1));
		System.out.println("Rate: "+(myPlayer.getRate()));

		return iGameRepository.save(newGame);
		}
	}

	@Override
	public List<Player> getRanking(){
		List<Player> playerList = iPlayerRepository.findAll();
		Comparator<Player> compareByRate = Comparator.comparing(Player::getRate).reversed();
		Collections.sort(playerList, compareByRate);

		return playerList;
	}

	@Override
	public Player getLoser(){
		List<Player> playerList = iPlayerRepository.findAll();
		Comparator<Player> compareByRate = Comparator.comparing(Player::getRate).reversed();
		Collections.sort(playerList, compareByRate);

		return playerList.get(listPlayers().size()-1);
	}
	@Override
	public Player getWinner(){
		List<Player> playerList = iPlayerRepository.findAll();
		Comparator<Player> compareByRate = Comparator.comparing(Player::getRate).reversed();
		Collections.sort(playerList, compareByRate);

		return playerList.get(0);
	}
}
