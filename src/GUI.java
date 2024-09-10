import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GUI {
    private final Game game;
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JLabel statusLabel;
    private Territory selectedFrom;
    private Territory selectedTo;
    private boolean isFortifying;
    private boolean isDistributing;
    // new addition
    private JPanel infoPanel;
    private JLabel controlRound;
    private int armiesSelected;

    public GUI(Game game) {
        this.game = game;
        this.frame = new JFrame("Risiko: " + game.getBoard().getWorldName() + " Map");
        this.boardPanel = new JPanel();
        this.statusLabel = new JLabel("Current Player: ");
        this.selectedFrom = null;
        this.selectedTo = null;
        this.isFortifying = false;
        this.isDistributing = false;
        // additions
        this.armiesSelected = 0;
        this.infoPanel = new JPanel();
        this.controlRound = new JLabel();
    }

    public void start() {
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 950);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(15, 20));
        updateBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        JPanel controlPanel = new JPanel();
        controlPanel.add(controlRound);

        //Create Buttons
        JButton nextTurnButton = getNextTurnButton();
        JButton fortifyButton = getFortifyButton();
        JButton distributeButton = getDistributeButton();
        JButton useCardButton = getUseCardButton();

        //Adds Button to the controlPanel
        controlPanel.add(nextTurnButton);
        controlPanel.add(fortifyButton);
        controlPanel.add(distributeButton);
        controlPanel.add(useCardButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.SOUTH);
        updateInfoPanel();

        frame.setVisible(true);
    }

    // addition infoPanel
    private void updateInfoPanel(){
        Player player = game.getCurrentPlayer();

        infoPanel.removeAll();
        infoPanel.setLayout(new GridLayout(3,2));

        Border lineBorder = BorderFactory.createLineBorder(player.getColor(),2);
        TitledBorder border = BorderFactory.createTitledBorder(lineBorder, player.getName());
        border.setTitleColor( player.getColor() );
        infoPanel.setBorder(border);
        // Territories
        JLabel labelTerritories = new JLabel("Territories:");
        infoPanel.add(labelTerritories);
        StringBuilder territories = new StringBuilder();
        for (Territory playerTerritory : player.getTerritories()) {
            territories.append(playerTerritory.getName()).append(", ");
        }
        JLabel territoriesList = new JLabel("<html>"+ territories + "</html>");
        // Armies
        JLabel labelArmies = new JLabel("Armies:");
        JLabel armyAmount = new JLabel(String.valueOf(player.getArmyCount()));
        // Cards
        JLabel labelCards = new JLabel("Cards:");
        String cards = "";
        for (Card card : player.getCards()){
            territories.append(card.getType()).append(", ");
        }
        JLabel cardList = new JLabel(String.valueOf(cards));
        // add to infoPanel
        infoPanel.add(labelTerritories);
        infoPanel.add(territoriesList);
        infoPanel.add(labelArmies);
        infoPanel.add(armyAmount);
        infoPanel.add(labelCards);
        infoPanel.add(cardList);
    }

    // removes Buttons from createAndShowGUI() and create their own functions

    private JButton getUseCardButton() {
        JButton useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCards();
            }
        });
        return useCardButton;
    }

    private JButton getDistributeButton() {
        JButton distributeButton = new JButton("Distribute Armies");
        distributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isDistributing()) {
                    game.startDistributingArmies();
                    isDistributing = true;
                    distributeButton.setText("Done");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Distribute Mode: " + isDistributing + ")");
                    distributeArmies();
                } else {
                    isDistributing = false;
                    distributeButton.setText("Distribute Armies");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName());
                    updateBoard();
                }
            }
        });
        return distributeButton;
    }

    private JButton getFortifyButton() {
        JButton fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFortifying = !isFortifying;
                fortifyButton.setText(isFortifying ? "Attack" : "Fortify");
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Fortify Mode: " + isFortifying + ")");
                isDistributing = false;
            }
        });
        return fortifyButton;
    }

    private JButton getNextTurnButton() {
        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isDistributing()) {
                    JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                } else {
                    game.nextTurn();
                    System.out.println("Current Player after turn: " + game.getCurrentPlayer().getName());
                    if (game.checkWinCondition()) {
                        JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                        System.exit(0);
                    }
                    updateBoard();
                }
            }
        });
        return nextTurnButton;
    }

    // eliminated distribution bug
    private void distributeArmies() {

        while (game.getArmiesToDistribute() > 0) {
            System.out.println(game.getArmiesToDistribute());
            Player player = game.getCurrentPlayer();
            Territory selectedTerritory = (Territory) JOptionPane.showInputDialog(
                    frame,
                     player.getName() + ", select a territory to place armies:",
                    "Distribute Armies",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    player.getTerritories().toArray(),
                    player.getTerritories().get(0)
            );

            if (selectedTerritory != null) {
                int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog(
                        frame,
                        "Enter number of armies to place (1-" + player.getArmyCount() + "):"
                ));

                if (armiesToPlace >= 1 && armiesToPlace <= player.getArmyCount()) {
                    //System.out.println(player.getName() + currentPlayerIndex);
                    boolean success = game.distributeArmy(selectedTerritory, armiesToPlace);
                    if (success) {
                        // TODO change when player is moved
                        game.decreaseArmiesToDistribute(armiesToPlace);

                        JOptionPane.showMessageDialog(frame, "You placed " + armiesToPlace + " armies at " + selectedTerritory.getName());
                        updateBoard();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place armies. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and " + player.getArmyCount() + ".");
                }
            }
            //currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

            // addition skip player if army count = 0
            if (game.getNextPlayer(player.getIndex()).getArmyCount() == 0){
                game.setNextDistributingPlayer(game.getNextPlayer(player.getIndex()));
            } else {
                game.setCurrentPlayer();
            }

        }

        JOptionPane.showMessageDialog(frame, "All armies are distributed. The game begins now!");
        updateBoard();
        isFortifying = false;
    }

    private void updateBoard() {
        boardPanel.removeAll();

        boardPanel.setLayout(new GridLayout(15,20));
        boardPanel.setBackground(new Color(175, 222, 234)); // change color for better view
        controlRound.setText("Round: " + game.getRound());

        //TODO rework layout, add abbreviation and army display
        Player currentPlayer = game.getCurrentPlayer();
        //List<Territory> territories = game.getBoard().getTerritories(); // addition ad territory list
        //Board currentBoard = game.getBoard();
        String[][] boardLayout = game.getBoard().getLayout();
        Color territoryColor = null;

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++){
                JLabel territoryLabel = new JLabel("", SwingConstants.CENTER);
                String abbr = boardLayout[i][j];

                if(!boardLayout[i][j].isEmpty()){
                    Territory currentTerritory = game.getTerritoryByAbbr(boardLayout[i][j]);
                    territoryLabel.setOpaque(true);
                    //TODO not working create class coordinate wit two integers, if null set current coordinate
                    if (currentTerritory.isFirstPanel() && !currentTerritory.getNeutrality()){
                        territoryColor = currentTerritory.getOwner().getColor();
                        territoryLabel.setText(currentTerritory.getAbbr());
                        currentTerritory.setFirstPanel();
                        currentTerritory.setSecondPanel();
                    } else if (currentTerritory.isSecondPanel() && !currentTerritory.getNeutrality()) {
                        territoryColor = currentTerritory.getOwner().getColor();
                        territoryLabel.setText(String.valueOf(currentTerritory.getArmyCount()));
                        currentTerritory.setSecondPanel();
                    } else {
                        territoryColor = game.getTerritoryColor(abbr);
                    }

                    territoryLabel.setBackground(territoryColor);
                }

                setBorders(i,j,abbr,territoryLabel,boardLayout);


                territoryLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1){
                            handleTerritoryClick(abbr);
                        }
                        // for testing
                        // add right mouse click to display adjacent territories
                        if (e.getButton() == MouseEvent.BUTTON3 && game.getTerritoryByAbbr(abbr).getOwner() == currentPlayer){
                            JOptionPane.showMessageDialog(frame, showTerritoryInformation(abbr));
                        }
                        if (e.getButton() == MouseEvent.BUTTON3 && game.getTerritoryByAbbr(abbr).getOwner() != currentPlayer) {
                            JOptionPane.showMessageDialog(frame, "This is not your territory!");
                        }
                    }
                });

                // add territoryLabel to the boardPanel
                boardPanel.add(territoryLabel);

            }
        }

        //removed previous code because of the new maps

        updateInfoPanel();

        statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() +
                " | Territories: " + currentPlayer.getTerritories().size() + " | Armies: " + currentPlayer.getArmyCount() +
                " | Cards: " + currentPlayer.getCards().size());

        boardPanel.revalidate();
        boardPanel.repaint();

        //TODO: rework later for more Players
        //if (game.getRound() == 1 && game.getCurrentPlayer().getIndex() == 0){
        //    distributeArmies();
        //}
    }

    // TODO rework
    // Carina
    public void setBorders(int i, int j, String territoryAbbr, JLabel territory, String[][] allMapTiles) {
        // remove any existing border
        territory.setBorder(BorderFactory.createEmptyBorder());

        // get border color
        Territory currentTerritory = game.getTerritoryByAbbr(territoryAbbr);
        Color borderColor = null;
        if (currentTerritory != null && !currentTerritory.getNeutrality()){
            borderColor = currentTerritory.getOwner().getColor();
        }
        if (currentTerritory != null && currentTerritory.getNeutrality()){
            borderColor = Color.gray;
        }
        //System.out.println(borderColor);

        // create a new border
        Border border = BorderFactory.createEmptyBorder();

        // check neighbor in the north
        if (i > 0) {
            //JLabel northNeighbor = allMapTiles[i - 1][j];
            if (!territoryAbbr.equals(allMapTiles[i - 1][j])) {
                border = BorderFactory.createMatteBorder(3, 0, 0, 0, borderColor);
            }
        }

        // check neighbor in the south
        if (i < allMapTiles.length - 1) {
            //JLabel southNeighbor = allMapTiles[i + 1][j];
            //if (!territoryAbbr.equals(southNeighbor.getClientProperty("territory"))) {
            if (!territoryAbbr.equals(allMapTiles[i + 1][j])) {
                border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 3, 0, borderColor));
            }
        }

        // check neighbor in the west
        if (j > 0) {
            //JLabel westNeighbor = allMapTiles[i][j - 1];
            if (!territoryAbbr.equals(allMapTiles[i][j - 1])) {
                border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 3, 0, 0, borderColor));
            }
        }

        // check neighbor in the east
        if (j < allMapTiles[i].length - 1) {
            //JLabel eastNeighbor = allMapTiles[i][j + 1];
            if (!territoryAbbr.equals(allMapTiles[i][j + 1])) {
                border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 3, borderColor));
            }
        }

        // add the border on the tile
        territory.setBorder(border);
    }


    // addition function to display adjacent territories => for testing purpose
    private ArrayList<String> showTerritoryInformation(String abbr){
        Territory territory = game.getTerritoryByAbbr(abbr);
        ArrayList<String> display = new ArrayList<>();
        display.add("Name: " + territory.getName() + "\nContinent: " + territory.getAssignedContinent() + "\nOwner: " + territory.getOwner().getName());
        display.add("\nTerritories available to attack\n");
        territory.getAdjacentTerritories().forEach((adjacent) -> {
            if (territory.getOwner() != adjacent.getOwner()){
                display.add(adjacent.getName());
            }
        });
        display.add("\nTerritories available to fortify\n");
        territory.getAdjacentTerritories().forEach((adjacent) -> {
            if (territory.getOwner() == adjacent.getOwner()){
                display.add(adjacent.getName());
            }
        });
        return display;
    }

    //TODO: rework the events
    private void handleTerritoryClick(String territoryAbbr) {
        Territory clickedTerritory = game.getTerritoryByAbbr(territoryAbbr);

        if (game.getRound() == 0){
            if (game.territorySelection(clickedTerritory)){
                armiesSelected++;
                // set next player
                game.setCurrentPlayer();
            } else {
                JOptionPane.showMessageDialog(frame, clickedTerritory.getName() + " has already been selected. Choose another territory.");
            }
            if (armiesSelected == game.getBoard().getTerritories().size()){
                game.setRound();
            }
            updateBoard();
        }

        /*
        if (isDistributing) {
            return;
        }

        // TODO rework
        if (isFortifying && game.getRound() > 0) {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for fortification: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only fortify adjacent territories.");
                    return;
                }

                int armiesToMove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to move (1-3):"));
                if (armiesToMove >= 1 && armiesToMove <= selectedFrom.getArmyCount() - 1) {
                    game.fortifyTerritory(selectedFrom, selectedTo, armiesToMove);
                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        } else {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for attack: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only attack adjacent territories.");
                    return;
                }
                if (selectedTo.getOwner() == game.getCurrentPlayer()) {
                    JOptionPane.showMessageDialog(frame, "You cannot attack your own territory.");
                    return;
                }
                    System.out.println(selectedFrom.getName() + selectedTo.getName());
                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount() - 1) {
                    int defendArmies = Math.min(2, selectedTo.getArmyCount());
                    game.attackTerritory(selectedFrom, selectedTo, attackArmies, defendArmies);

                    handleAttackPhase();

                    if (selectedTo.getArmyCount() == 0) {
                        JOptionPane.showMessageDialog(frame, "Territory conquered! You receive a card.");
                        game.getCurrentPlayer().addTerritory(selectedTo);
                        game.getCurrentPlayer().addCard(new Card("Infantry"));
                    }

                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }*/
    }

    private void useCards() {
        Player currentPlayer = game.getCurrentPlayer();
        List<Card> cards = currentPlayer.getCards();

        if (cards.size() < 3) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 cards to trade for armies.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(frame, "Do you want to trade 3 cards for 5 armies?");
        if (response == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 3; i++) {
                currentPlayer.useCard();
            }
            currentPlayer.addArmies(5);
            JOptionPane.showMessageDialog(frame, "You have received 5 additional armies.");

            distributeBonusArmies(currentPlayer, 5);
            updateBoard();
        }
    }

    private void handleAttackPhase() {
        String diceResult = rollDiceResult();

        JFrame diceFrame = new JFrame("Roll Dice");
        diceFrame.setSize(300, 200);
        diceFrame.setLayout(new BorderLayout());

        JTextArea diceResultArea = new JTextArea(diceResult);
        diceResultArea.setEditable(false);
        diceFrame.add(new JScrollPane(diceResultArea), BorderLayout.CENTER);

        JButton okButton = getOkButton(diceFrame);
        diceFrame.add(okButton, BorderLayout.SOUTH);
        diceFrame.setVisible(true);
    }

    private JButton getOkButton(JFrame diceFrame) {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceFrame.dispose();
                game.nextTurn();
                if (game.checkWinCondition()) {
                    JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                    System.exit(0);
                }
                updateBoard();
            }
        });
        return okButton;
    }


    private String rollDiceResult() {
        if (selectedFrom == null || selectedTo == null) {
            System.out.println("Fehler: Kein Angriff ausgewählt.");
        }
        int attackDiceCount = Math.min(3, selectedFrom.getArmyCount());
        int defendDiceCount = Math.min(2, selectedTo.getArmyCount());

        int[] attackDice = game.rollDice(attackDiceCount);
        int[] defendDice = game.rollDice(defendDiceCount);
        return compareDiceResults(attackDice, defendDice);
    }

    private String compareDiceResults (int[] attackDice, int[] defendDice){
        if (attackDice == null || defendDice == null) {
            System.out.println("Fehler: Keine Würfelzahl vorhanden.");
        }
        assert attackDice != null;
        Arrays.sort(attackDice);
        assert defendDice != null;
        Arrays.sort(defendDice);

        StringBuilder result = new StringBuilder();
        result.append("Attacker's dice: ").append(Arrays.toString(game.reverseArray(attackDice))).append("\n");
        result.append("Defender's dice: ").append(Arrays.toString(game.reverseArray(defendDice))).append("\n");

        int minComparisons = Math.min(attackDice.length, defendDice.length);
        int attackerLosses = 0;
        int defenderLosses = 0;

        for (int i = 0; i < minComparisons; i++) {
            if (attackDice[i] > defendDice[i]) {
                defenderLosses++;
            } else {
                attackerLosses++;
            }
        }

        result.append("Attacker loses ").append(attackerLosses).append(" army/armies.\n");
        result.append("Defender loses ").append(defenderLosses).append(" army/armies.\n");

        selectedFrom.removeArmies(attackerLosses);
        selectedTo.removeArmies(defenderLosses);

        if (selectedTo.getArmyCount() == 0) {
            game.getCurrentPlayer().addTerritory(selectedTo);
            game.getCurrentPlayer().addCard(new Card("Infantry"));
            result.append("Territory conquered! ").append(selectedTo.getName()).append(" is now owned by ").append(game.getCurrentPlayer().getName()).append(".");
        }

        return result.toString();
    }

    private void distributeBonusArmies(Player player, int armiesToDistribute) {
        while (armiesToDistribute > 0) {
            Territory selectedTerritory = (Territory) JOptionPane.showInputDialog(
                    frame,
                    player.getName() + ", select a territory to place armies:",
                    "Distribute Armies",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    player.getTerritories().toArray(),
                    player.getTerritories().get(0)
            );

            if (selectedTerritory != null) {
                int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog(
                        frame,
                        "Enter number of armies to place (1-" + armiesToDistribute + "):"
                ));

                if (armiesToPlace >= 1 && armiesToPlace <= armiesToDistribute) {
                    boolean success = game.distributeArmy(selectedTerritory, armiesToPlace);
                    if (success) {
                        armiesToDistribute -= armiesToPlace;
                        JOptionPane.showMessageDialog(frame, "You placed " + armiesToPlace + " armies at " + selectedTerritory.getName());
                        updateBoard();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place armies. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and " + armiesToDistribute + ".");
                }
            }
        }

        JOptionPane.showMessageDialog(frame, "All bonus armies are distributed.");
    }

    // for testing
    public void printInfo(Player owner, Territory territory){
        System.out.println("Player");
        System.out.println("Name: " + owner.getName() + ", Color: " + owner.getColor() + ", Index: " + owner.getIndex());
        System.out.println("Territory: " + territory.getName());
        for (Territory territory1 : owner.getTerritories()){
            if (territory1.getName().equals(territory.getName())){
                System.out.println("Player owns the territory");
            }
        }
    }

}
