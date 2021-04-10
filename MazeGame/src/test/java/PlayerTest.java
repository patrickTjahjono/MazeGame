import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void spawnPlayer() {
        Player player = Player.getInstance();
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
    }

    // failed test
    @Test
    void failTest() {
        assertEquals(0, 1);
    }
}
