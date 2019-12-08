package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{name}")
    public Game getGame(@PathVariable String name) {
        return gameRepository.findByName(name);
    }

    @PostMapping("/games")
    public Game postGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @DeleteMapping("/games/{name}")
    public Game deleteGame(@PathVariable String name) {
        Game game = gameRepository.findByName(name);
        gameRepository.delete(game);
        return game;
    }
}
