import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUI extends JFrame {

    private JPanel mainPanel;
    private JTextField[] playerNameFields;
    private Color[] selectedColors;
    private JRadioButton twoPlayersButton, threePlayersButton, fourPlayersButton;
    private JRadioButton fantasyWorldButton, zamonienButton, tamrielButton;
    private StartUIListener listener;
    private JLabel[][] colorLabelsForPlayers;
    private boolean[] isColorChosen;
    private Color[] preselectedColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private JPanel playerInputPanel;
    private JPanel mapPanel;
    private int[] previousColorIndex;

    // Initialize StartUI
    public StartUI(StartUIListener listener) {
        this.listener = listener;
        setTitle("Start Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the main panel

        // Player selection panel
        JPanel playerSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerSelectionPanel.setBorder(BorderFactory.createTitledBorder("Select Number of Players"));

        ButtonGroup playerButtonGroup = new ButtonGroup();
        twoPlayersButton = new JRadioButton("2 Players");
        threePlayersButton = new JRadioButton("3 Players");
        fourPlayersButton = new JRadioButton("4 Players");

        playerButtonGroup.add(twoPlayersButton);
        playerButtonGroup.add(threePlayersButton);
        playerButtonGroup.add(fourPlayersButton);

        playerSelectionPanel.add(twoPlayersButton);
        playerSelectionPanel.add(threePlayersButton);
        playerSelectionPanel.add(fourPlayersButton);

        // Default selection
        twoPlayersButton.setSelected(true);

        mainPanel.add(playerSelectionPanel);

        // Players input panel
        playerInputPanel = new JPanel();
        playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.Y_AXIS));
        playerInputPanel.setBorder(BorderFactory.createTitledBorder("Player Names and Colors:"));
        playerInputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(playerInputPanel);

        playerNameFields = new JTextField[4];
        selectedColors = new Color[4];
        colorLabelsForPlayers = new JLabel[4][preselectedColors.length];
        isColorChosen = new boolean[preselectedColors.length];
        previousColorIndex = new int[4];

        for (int i = 0; i < 4; i++) {
            previousColorIndex[i] = -1;
        }

        updatePlayerInputFields(2);  // Start with 2 players selected

        // Add listeners to player selection
        twoPlayersButton.addActionListener(e -> updatePlayerInputFields(2));
        threePlayersButton.addActionListener(e -> updatePlayerInputFields(3));
        fourPlayersButton.addActionListener(e -> updatePlayerInputFields(4));

        // Map selection panel
        mapPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mapPanel.setBorder(BorderFactory.createTitledBorder("Select Map"));

        ButtonGroup mapButtonGroup = new ButtonGroup();
        fantasyWorldButton = new JRadioButton("Fantasy World");
        zamonienButton = new JRadioButton("Zamonien");
        tamrielButton = new JRadioButton("Tamriel");

        mapButtonGroup.add(fantasyWorldButton);
        mapButtonGroup.add(zamonienButton);
        mapButtonGroup.add(tamrielButton);

        mapPanel.add(fantasyWorldButton);
        mapPanel.add(zamonienButton);
        mapPanel.add(tamrielButton);

        fantasyWorldButton.setSelected(true);  // Default selection
        mainPanel.add(mapPanel);  // Add map panel to the end

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> handleStartGame());  // Trigger game start when button is clicked
        mainPanel.add(startButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Method to update player input
    private void updatePlayerInputFields(int numberOfPlayers) {
        playerInputPanel.removeAll();

        for (int i = 0; i < numberOfPlayers; i++) {
            JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));  // Horizontal layout for each player
            playerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            playerNameFields[i] = new JTextField("Player " + (i + 1), 10);  // Set text field size
            playerPanel.add(new JLabel("Name:"));
            playerPanel.add(playerNameFields[i]);

            JPanel colorSelectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            for (int j = 0; j < preselectedColors.length; j++) {
                colorLabelsForPlayers[i][j] = new JLabel();
                colorLabelsForPlayers[i][j].setPreferredSize(new Dimension(30, 30));
                colorLabelsForPlayers[i][j].setOpaque(true);
                colorLabelsForPlayers[i][j].setBackground(preselectedColors[j]);
                colorLabelsForPlayers[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                final int playerIndex = i;
                final int colorIndex = j;

                colorLabelsForPlayers[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        handleColorSelection(playerIndex, colorIndex);
                    }
                });

                colorSelectionPanel.add(colorLabelsForPlayers[i][j]);
            }

            playerPanel.add(colorSelectionPanel);
            playerInputPanel.add(playerPanel);
        }

        playerInputPanel.revalidate();
        playerInputPanel.repaint();
    }

    private void handleColorSelection(int playerIndex, int colorIndex) {
        // Check if the selected color has already been chosen
        if (isColorChosen[colorIndex]) {
            JOptionPane.showMessageDialog(this, "This color has already been chosen. Please select a different color.", "Color Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (previousColorIndex[playerIndex] != -1) {
            int prevColorIdx = previousColorIndex[playerIndex];
            colorLabelsForPlayers[playerIndex][prevColorIdx].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Reset border for previous color
            isColorChosen[prevColorIdx] = false;

            // Re-enable the previous color
            for (int i = 0; i < colorLabelsForPlayers.length; i++) {
                if (i != playerIndex && colorLabelsForPlayers[i][prevColorIdx] != null) {
                    colorLabelsForPlayers[i][prevColorIdx].setEnabled(true);
                }
            }
        }

        // Assign the selected color
        colorLabelsForPlayers[playerIndex][colorIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Thicker border for selected color
        selectedColors[playerIndex] = preselectedColors[colorIndex];
        isColorChosen[colorIndex] = true;
        previousColorIndex[playerIndex] = colorIndex;

        // Disable the selected color for other players
        for (int i = 0; i < colorLabelsForPlayers.length; i++) {
            if (i != playerIndex && colorLabelsForPlayers[i][colorIndex] != null) {  // Disable for all other players and ensure it's not null
                colorLabelsForPlayers[i][colorIndex].setEnabled(false);
            }
        }
    }

    private void handleStartGame() {
        int numPlayers = twoPlayersButton.isSelected() ? 2 : (threePlayersButton.isSelected() ? 3 : 4);
        String[] playerNames = new String[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            playerNames[i] = playerNameFields[i].getText();
        }

        String selectedMap = fantasyWorldButton.isSelected() ? "Fantasy World" :
                (zamonienButton.isSelected() ? "Zamonien" : "Tamriel");

        // Check if color exists
        // TODO: assign colors if none were chosen

        // Print info
        printGameInfo(numPlayers, playerNames, selectedColors, selectedMap);

        // Notify the listener
        listener.onGameStart(playerNames, selectedColors, selectedMap, numPlayers);

        // Destroy the panel
        dispose();
    }

    private void printGameInfo(int numPlayers, String[] playerNames, Color[] playerColors, String selectedMap) {
        System.out.println("Number of Players: " + numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + playerNames[i] + ", Color: " + getColorName(playerColors[i]));
        }
        System.out.println("Selected Map: " + selectedMap);
    }

    private String getColorName(Color color) {
        if (color.equals(Color.RED)) return "Red";
        if (color.equals(Color.BLUE)) return "Blue";
        if (color.equals(Color.GREEN)) return "Green";
        if (color.equals(Color.YELLOW)) return "Yellow";
        if (color.equals(Color.ORANGE)) return "Orange";
        if (color.equals(Color.MAGENTA)) return "Magenta";
        return "Unknown Color";
    }
}
