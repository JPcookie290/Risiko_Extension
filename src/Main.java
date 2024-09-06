import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            StartUI startUI = new StartUI(new StartUIListener() {
                @Override
                public void onGameStart(String[] playerNames, Color[] playerColors, String selectedMap, int numPlayers) {

                    Game game = new Game(playerNames, playerColors, selectedMap, numPlayers);
                    GUI gui = new GUI(game);
                    javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);  // Start Game
                }
            });
            startUI.setVisible(true);
        });
    }
}

