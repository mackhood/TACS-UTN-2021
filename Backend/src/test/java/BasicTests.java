import com.example.TACS2021UTN.entities.Deck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicTests {

    private List<Deck> cardRepository;

    @BeforeAll
    void initialLoad()
    {
        // Initialize cards, decks, users
    }

    @Test
    void startGame()
    {
        /* Validate main flow game.startGame*/
    }

    @Test
    void playGame()
    {
        /* Validate main flow game.play  generating a duel. Should calculate the winner o draw and give prizes*/
    }
}
