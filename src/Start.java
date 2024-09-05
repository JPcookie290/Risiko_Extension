import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JPanel mainPanel;
    private JRadioButton amount_2;
    private JRadioButton amount_3;
    private JRadioButton amount_4;
    private JTextField player_1;
    private JTextField player_2;
    private JTextField player_3;
    private JTextField player_4;
    private JRadioButton map_1;
    private JRadioButton map_2;
    private JRadioButton map_3;
    private JButton startGameButton;

    private StartListener listener; // Listener to communicate with Main

    public Start() {
        this.listener = listener;

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectFormData(); // Collect the form data and trigger the listener
            }
        });
    }

    private void collectFormData() {
        int numPlayers;
        if (amount_2.isSelected()) {
            numPlayers = 2;
        } else if (amount_3.isSelected()) {
            numPlayers = 3;
        } else {
            numPlayers = 4;
        }

        String[] playerNames = new String[] {
                player_1.getText().isEmpty() ? "Player 1" : player_1.getText(),
                player_2.getText().isEmpty() ? "Player 2" : player_2.getText(),
                player_3.getText().isEmpty() ? "Player 3" : player_3.getText(),
                player_4.getText().isEmpty() ? "Player 4" : player_4.getText()
        };

        String selectedMap;
        if (map_1.isSelected()) {
            selectedMap = "Map 1";
        } else if (map_2.isSelected()) {
            selectedMap = "Map 2";
        } else {
            selectedMap = "Map 3";
        }

        listener.onStartGame(playerNames, selectedMap, numPlayers);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public interface StartListener {
        void onStartGame(String[] playerNames, String selectedMap, int numPlayers);
    }
}
