import java.awt.*;

public interface StartUIListener {
    void onGameStart(String[] playerNames, Color[] playerColors, String selectedMap, int numPlayers);
}
