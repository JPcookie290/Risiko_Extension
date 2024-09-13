import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUI {
    private final Game game;
    private final JFrame frame;
    private final JPanel boardPanel;
    //private final JLabel statusLabel;
    private Territory selectedFrom;
    private Territory selectedTo;
    private boolean isFortifying;
    private boolean isDistributing;
    // new addition
    private boolean isAttacking;
    private boolean isInitialRound;
    private JPanel infoPanel;
    private JLabel controlRound;
    private int armiesSelected;
    private Territory currentSelectedTerritory;
    // moved buttons
    private JButton fortifyButton;
    private JButton attackButton;
    private JButton useCardButton;

    public GUI(Game game) {
        this.game = game;
        this.frame = new JFrame("Risiko: " + game.getBoard().getWorldName() + " Map");
        this.boardPanel = new JPanel();
        //this.statusLabel = new JLabel("Current Player: ");
        this.selectedFrom = null;
        this.selectedTo = null;
        this.isFortifying = false;
        this.isDistributing = false;
        // additions
        this.isAttacking = false;
        this.isInitialRound = true;
        this.armiesSelected = 0;
        this.infoPanel = new JPanel();
        this.controlRound = new JLabel();
        this.currentSelectedTerritory = null;
    }

    public void start() {
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 950);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(15, 20));

        frame.add(boardPanel, BorderLayout.CENTER);
        //frame.add(statusLabel, BorderLayout.SOUTH);

        JPanel controlPanel = new JPanel();
        controlPanel.add(controlRound);

        //Create Buttons
        JButton nextTurnButton = getNextTurnButton();
        getFortifyButton();
        getAttackButton();
        getUseCardButton();

        //Adds Button to the controlPanel
        controlPanel.add(nextTurnButton);
        controlPanel.add(fortifyButton);
        controlPanel.add(attackButton);
        controlPanel.add(useCardButton);
        useCardButton.setEnabled(false);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.SOUTH);
        updateInfoPanel();
        updateBoard();
        frame.setVisible(true);
    }

    // addition infoPanel
    private void updateInfoPanel(){
        Player player = game.getCurrentPlayer();

        infoPanel.removeAll();
        infoPanel.setLayout(new GridLayout(4,2));

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
        // Win condition
        JLabel mission = new JLabel("Your mission:");
        JLabel missionText = new JLabel(game.getCurrentPlayer().getWinCondition().getText());

        // add to infoPanel
        infoPanel.add(labelTerritories);
        infoPanel.add(territoriesList);
        infoPanel.add(labelArmies);
        infoPanel.add(armyAmount);
        infoPanel.add(labelCards);
        infoPanel.add(cardList);
        infoPanel.add(mission);
        infoPanel.add(missionText);
    }

    // removes Buttons from createAndShowGUI() and create their own functions

    // TODO rework Buttons
    private void getUseCardButton() {
        useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCards();
            }
        });
    }

    // changed distributeButton to attackButton
    private void getAttackButton() {
        attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDistributing) {
                    JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                    return;
                }
                if (!isAttacking) {
                    attackButton.setText("Done");
                    if (isFortifying){
                        isFortifying = false;
                        selectedFrom = null;
                        selectedTo = null;
                        fortifyButton.setText("Fortify");
                    }
                    isAttacking = true;
                    updateBoard();
                } else {
                    isAttacking = false;
                    selectedTo = null;
                    selectedFrom = null;
                    attackButton.setText("Attack");
                    updateBoard();
                }
            }
        });
    }

    private void getFortifyButton() {
        fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDistributing) {
                    JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                    return;
                }
                if (!isFortifying) {
                    fortifyButton.setText("Done");
                    if (isAttacking){
                        isAttacking = false;
                        selectedFrom = null;
                        selectedTo = null;
                        attackButton.setText("Attack");
                    }
                    isFortifying = true;
                    updateBoard();
                } else {
                    isFortifying = false;
                    selectedFrom = null;
                    selectedTo = null;
                    fortifyButton.setText("Fortify");
                    updateBoard();
                }
            }
        });
    }

    private JButton getNextTurnButton() {
        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isDistributing() || isInitialRound) {
                    JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                } else {
                    // TODO check
                    fortifyButton.setEnabled(true);
                    game.nextTurn();
                    if (game.getCurrentPlayer().getArmyCount() > 0){
                        isDistributing = true;
                    }
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
        Player player = game.getCurrentPlayer();
        if (player.getArmyCount() == 0){
            game.setCurrentPlayer();
        } else {
            if (currentSelectedTerritory != null && currentSelectedTerritory.getOwner().equals(player)) {
                int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog(
                        frame,
                        "Enter number of armies to place (1-" + player.getArmyCount() + "):"
                ));

                if (armiesToPlace >= 1 && armiesToPlace <= player.getArmyCount()) {
                    //System.out.println(player.getName() + currentSelectedTerritory + armiesToPlace);
                    game.startDistributingArmies();
                    boolean success = game.distributeArmy(currentSelectedTerritory, armiesToPlace);
                    if (success) {
                        game.decreaseArmiesToDistribute(armiesToPlace);
                        JOptionPane.showMessageDialog(frame, "You placed " + armiesToPlace + " armies at " + currentSelectedTerritory.getName());
                        setCurrentSelectedTerritory(null);
                        if(game.getCurrentPlayer().getArmyCount() == 0){
                            isDistributing = false;
                        }
                        if (isInitialRound) {
                            isDistributing = true;
                            game.setCurrentPlayer();
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place armies. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and " + player.getArmyCount() + ".");
                }
            }

        }

        if (game.getArmiesToDistribute() == 0 && isInitialRound){
            game.setRound();
            isInitialRound = false;
            isDistributing = false;
            JOptionPane.showMessageDialog(frame, "All armies are distributed. The game begins now!");
        }
    }

    private void setCurrentSelectedTerritory(Territory selectedTerritory) {
        this.currentSelectedTerritory = selectedTerritory;
    }

    private void updateBoard() {
        boardPanel.removeAll();

        boardPanel.setLayout(new GridLayout(game.getBoard().getLayout().length,game.getBoard().getLayout()[1].length));
        boardPanel.setBackground(new Color(14, 68, 108)); // change color for better view

        // create visual signal in which modus the player is currently in
        Color boardColor = Color.WHITE;
        String boardModus = "you can either fortify or attack";
        if (isInitialRound){
            boardModus = game.getCurrentPlayer().getName() + " choose a territory";
        }
        if (game.getCurrentPlayer().getCards().size() >= 3){
            useCardButton.setEnabled(true);
            boardModus = "you can either fortify, attack or exchange cards";
        }
        if (isFortifying){
            boardColor = new Color(128, 227, 153);
            boardModus = "Modus: Fortifying";
            if (selectedFrom != null){
                boardModus += " | Starting territory: " + selectedFrom.getName();
            }
        } else if (isDistributing){
            boardColor = new Color(223, 216, 133);
            boardModus = "Modus: Distributing";
        } else if (isAttacking){
            boardColor = new Color(227, 128, 128);
            boardModus = "Modus: Attacking";
            if (selectedFrom != null){
                boardModus += " | Starting territory: " + selectedFrom.getName();
            }
        }

        Border lineBorder = BorderFactory.createLineBorder(boardColor,2);
        TitledBorder border = BorderFactory.createTitledBorder(lineBorder,boardModus);
        border.setTitleColor( boardColor );
        boardPanel.setBorder(border);

        controlRound.setText("Round: " + game.getRound());

        String[][] boardLayout = game.getBoard().getLayout();
        Color territoryColor = null;

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++){
                JLabel territoryLabel = new JLabel("", SwingConstants.CENTER);
                String abbr = boardLayout[i][j];

                if(!boardLayout[i][j].isEmpty()){
                    Territory currentTerritory = game.getTerritoryByAbbr(boardLayout[i][j]);
                    territoryLabel.setOpaque(true);

                    // set first and second panel
                    if (currentTerritory.getFirstPanel() == null){
                        currentTerritory.setFirstPanel(i, j);
                    } else if (currentTerritory.getSecondPanel() == null && currentTerritory.getFirstPanel() != null){
                        currentTerritory.setSecondPanel(i, j);
                    }

                    // set text of the first and second panel
                    if (currentTerritory.getFirstPanel().checkCoordinate(i, j)){
                        territoryLabel.setText(currentTerritory.getAbbr());
                    }
                    // check the second panel coordinates
                    if (currentTerritory.getSecondPanel() != null && currentTerritory.getSecondPanel().checkCoordinate(i, j)) {
                        territoryLabel.setText(String.valueOf(currentTerritory.getArmyCount()));
                    }

                    // check to get color if territory is owned for the first and second panel
                    if ((currentTerritory.getFirstPanel().checkCoordinate(i, j) && !currentTerritory.getNeutrality()) ||
                            (currentTerritory.getSecondPanel() != null && currentTerritory.getSecondPanel().checkCoordinate(i, j) && !currentTerritory.getNeutrality())){
                        territoryColor = currentTerritory.getOwner().getColor();
                        territoryLabel.setForeground(Color.white);
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
                    }
                });

                // add territoryLabel to the boardPanel
                boardPanel.add(territoryLabel);

            }
        }

        //removed previous code because of the new maps

        updateInfoPanel();

        boardPanel.revalidate();
        boardPanel.repaint();

    }

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


    // addition function to display territory information
    private ArrayList<String> showTerritoryInformation(Territory territory){
        ArrayList<String> displayInfo = new ArrayList<>();

        displayInfo.add("Name: " + territory.getName());
        displayInfo.add("Continent: " + territory.getAssignedContinent());

        // Territory owner check
        if (territory.getOwner() == null){
            displayInfo.add("Currently a neutal territory");
        } else {
            displayInfo.add("Owner: " + territory.getOwner().getName());
            // Current player owns the territory
            if (territory.getOwner() != null && territory.getOwner().equals(game.getCurrentPlayer())) {
                displayInfo.add("Current armies present: " + territory.getArmyCount());

                // Available for attack
                displayInfo.add("\nTerritories available to attack:");
                territory.getAdjacentTerritories().forEach((adjacent) -> {
                    if (!territory.getOwner().equals(adjacent.getOwner())) {
                        displayInfo.add("- " + adjacent.getName());
                    }
                });

                // Available to fortify
                displayInfo.add("\nTerritories available to fortify:");
                territory.getAdjacentTerritories().forEach((adjacent) -> {
                    if (territory.getOwner().equals(adjacent.getOwner())) {
                        displayInfo.add("- " + adjacent.getName());
                    }
                });
            }

        }

        return displayInfo;
    }

    //TODO: rework the events
    private void handleTerritoryClick(String territoryAbbr) {
        setCurrentSelectedTerritory(game.getTerritoryByAbbr(territoryAbbr));

        if (isInitialRound && !isDistributing){
            // Hard coded for Tamriel Board
            if (currentSelectedTerritory.getAbbr().equals("CY")){
                JOptionPane.showMessageDialog(frame, currentSelectedTerritory.getName() + " can not be selected at the beginning. Choose another territory.");
                return;
            }
            if (game.territorySelection(currentSelectedTerritory)){
                armiesSelected++;
                // set next player
                game.setCurrentPlayer();
            } else {
                JOptionPane.showMessageDialog(frame, currentSelectedTerritory.getName() + " has already been selected. Choose another territory.");
            }
            if (armiesSelected == game.getTerritoriesToSelectAmount() * game.getPlayers().length){
                setCurrentSelectedTerritory(null);
                isDistributing = true;
            }
            updateBoard();
        }

        if (isDistributing) { //TODO something is not working
           if (currentSelectedTerritory != null && currentSelectedTerritory.getOwner() == game.getCurrentPlayer()){
               distributeArmies();
               updateBoard();
           }
            if (currentSelectedTerritory != null && !currentSelectedTerritory.getOwner().equals(game.getCurrentPlayer())) {
                JOptionPane.showMessageDialog(frame, "You do not own this territory.");
            }
        }

        if (isFortifying) {
            if (selectedFrom == null) {
                if (currentSelectedTerritory != null && currentSelectedTerritory.getOwner().equals(game.getCurrentPlayer())) {
                    selectedFrom = currentSelectedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for fortification: " + selectedFrom.getName() + ". Now select the target territory.");
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = currentSelectedTerritory;
                if (!selectedTo.getOwner().equals(game.getCurrentPlayer())){
                    JOptionPane.showMessageDialog(frame, "You can only fortify your own territories.");
                    return;
                }
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only fortify adjacent territories.");
                    return;
                }

                int armiesToMove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to move (1-3):"));
                if (armiesToMove >= 1 && armiesToMove <= selectedFrom.getArmyCount() - 1) {
                    game.fortifyTerritory(selectedFrom, selectedTo, armiesToMove);
                    selectedFrom = null;
                    selectedTo = null;
                    // TODO: create a function
                    isFortifying = false;
                    fortifyButton.setText("Fortify");
                    fortifyButton.setEnabled(false);
                    currentSelectedTerritory = null;

                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }

        if (isAttacking){
            if (selectedFrom == null) {
                if (currentSelectedTerritory != null && currentSelectedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = currentSelectedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for attack: " + selectedFrom.getName() + ". Now select the target territory.");
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = currentSelectedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only attack adjacent territories.");
                    return;
                }
                if (selectedTo.getOwner() == game.getCurrentPlayer()) {
                    JOptionPane.showMessageDialog(frame, "You cannot attack your own territory.");
                    return;
                }
                // For Tamriel Board
                if (game.getBoard().getWorldName().equals("Tamriel") && !game.getBoard().isCyrodillAttackable(game.getCurrentPlayer())){
                    JOptionPane.showMessageDialog(frame, "You are not yet qualified to attack Cyrodill.");
                    return;
                }

                //System.out.println(selectedFrom.getName() + selectedTo.getName());
                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount() - 1) {
                    int defendArmies = Math.min(2, selectedTo.getArmyCount());
                    game.attackTerritory(selectedFrom, selectedTo, attackArmies, defendArmies);

                    handleAttackPhase();

                    if (selectedTo.getArmyCount() == 0) {
                        // TODO Cards
                        JOptionPane.showMessageDialog(frame, "Territory conquered! You receive a card.");
                        game.getCurrentPlayer().addTerritory(selectedTo);
                        //Card card = game.getRandomCard();
                        //game.getCurrentPlayer().addCard(card);
                    }

                    selectedFrom = null;
                    selectedTo = null;
                    // TODO: create a function
                    isAttacking = false;
                    attackButton.setText("Attack");
                    currentSelectedTerritory = null;

                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }

        if (!isDistributing && !isAttacking && !isFortifying && !isInitialRound && currentSelectedTerritory != null){
            //System.out.println("Test GUI, 488: " + currentSelectedTerritory + ": " + currentSelectedTerritory.getAdjacentTerritories());
            JOptionPane.showMessageDialog(frame, showTerritoryInformation(currentSelectedTerritory));
        }

    }

    private void useCards() {
        Player currentPlayer = game.getCurrentPlayer();

        int response = JOptionPane.showConfirmDialog(frame, "Do you want to trade 3 cards for an additional amount of armies?");
        if (response == JOptionPane.YES_OPTION) {
            String type = currentPlayer.useCard();
            String notification = game.addCardsBack(type);

            JOptionPane.showMessageDialog(frame, notification);

            isDistributing = true;
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
                //game.nextTurn();
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

    // removed distributeBonusArmies as it was not needed

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
