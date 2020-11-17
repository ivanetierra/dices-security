package dices.service;

import dices.repository.IPlayerRepository;
import dices.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IPlayerRepository iPlayerRepository;

	// Devuelve User para que lo pueda autenticar Spring
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Player player = iPlayerRepository.findByName(name);
        if (player == null) {
            throw new UsernameNotFoundException(name);
        }
        return new User(player.getName(), player.getPassword(), emptyList());
	}

}
