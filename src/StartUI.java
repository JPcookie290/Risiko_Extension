import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUI extends JFrame {

    private JPanel mainPanel;
    private ButtonGroup playerButtonGroup;
    private JRadioButton twoPlayersButton, threePlayersButton, fourPlayersButton;
    private JPanel playerInputPanel;
    private JTextField[] playerNameFields;
    private JPanel[] colorSelectionPanels;
    private JLabel[][] colorLabelsForPlayers;
    private Color[] preselectedColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private ButtonGroup mapButtonGroup;
    private JRadioButton fantasyWorldButton, zamonienButton, tamrielButton;
    private JButton startButton;

    public StartUI() {
        setTitle("Start Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        // Player selection panel with horizontal layout
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Horizontal layout
        playerPanel.setBorder(BorderFactory.createTitledBorder("Select Number of Players"));

        playerButtonGroup = new ButtonGroup();
        twoPlayersButton = new JRadioButton("2 Players");
        threePlayersButton = new JRadioButton("3 Players");
        fourPlayersButton = new JRadioButton("4 Players");

        playerButtonGroup.add(twoPlayersButton);
        playerButtonGroup.add(threePlayersButton);
        playerButtonGroup.add(fourPlayersButton);

        playerPanel.add(twoPlayersButton);
        playerPanel.add(threePlayersButton);
        playerPanel.add(fourPlayersButton);

        twoPlayersButton.setSelected(true);  // Default selection

        mainPanel.add(playerPanel);

        // Player name and color input panel
        playerInputPanel = new JPanel();
        playerInputPanel.setLayout(new GridLayout(2, 1));
        playerInputPanel.setBorder(BorderFactory.createTitledBorder("Enter Player Names and Choose Colors"));
        updatePlayerInputFields(2);  // Start with 2 players as default

        mainPanel.add(playerInputPanel);

        // Map selection panel with horizontal layout
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Horizontal layout
        mapPanel.setBorder(BorderFactory.createTitledBorder("Select Map"));

        mapButtonGroup = new ButtonGroup();
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

        mainPanel.add(mapPanel);

        // Start button
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartGame();
            }
        });

        add(mainPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);

        // Add listeners to player selection buttons
        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayerInputFields(2);
            }
        });

        threePlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayerInputFields(3);
            }
        });

        fourPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayerInputFields(4);
            }
        });
    }

    private void updatePlayerInputFields(int numberOfPlayers) {
        playerInputPanel.removeAll();
        playerInputPanel.setLayout(new GridLayout(numberOfPlayers, 2));
        playerNameFields = new JTextField[numberOfPlayers];
        colorSelectionPanels = new JPanel[numberOfPlayers];
        colorLabelsForPlayers = new JLabel[numberOfPlayers][preselectedColors.length];

        for (int i = 0; i < numberOfPlayers; i++) {
            // Player name input field
            playerNameFields[i] = new JTextField("Player " + (i + 1));
            playerInputPanel.add(new JLabel("Name:"));
            playerInputPanel.add(playerNameFields[i]);

            // Color selection panel
            colorSelectionPanels[i] = new JPanel(new FlowLayout());
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

                colorSelectionPanels[i].add(colorLabelsForPlayers[i][j]);
            }

            playerInputPanel.add(colorSelectionPanels[i]);
        }

        playerInputPanel.revalidate();
        playerInputPanel.repaint();
    }

    private void handleColorSelection(int playerIndex, int colorIndex) {
        // Assign the selected color to the player and highlight the selected color with a thicker border
        colorLabelsForPlayers[playerIndex][colorIndex].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Thicker border for selected color

        // Disable the selected color for all other players without changing its appearance
        for (int i = 0; i < colorLabelsForPlayers.length; i++) {
            if (i != playerIndex) {  // Disable for all other players
                colorLabelsForPlayers[i][colorIndex].setEnabled(false);
            }
        }
    }

    private void handleStartGame() {
        int numberOfPlayers = twoPlayersButton.isSelected() ? 2 : (threePlayersButton.isSelected() ? 3 : 4);
        String[] playerNames = new String[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = playerNameFields[i].getText();
        }

        String selectedMap = fantasyWorldButton.isSelected() ? "Fantasy World" :
                (zamonienButton.isSelected() ? "Zamonien" : "Tamriel");

        // You can now pass playerNames to your Game class
        System.out.println("Starting game with " + numberOfPlayers + " players.");
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Name: " + playerNames[i]);
        }
        System.out.println("Selected map: " + selectedMap);

        // Add logic to start the game, such as opening the game window or passing data to another class
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI();
        startUI.setVisible(true);
    }
}
