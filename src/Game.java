import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private final Random random;
    private final Map<Player, List<Card>> playerCards;
    private boolean isDistributing;
    private int armiesToDistribute;
    // new addition
    private List<Card> allCards;
    private int round;
    private Color[] playerColors;

    public Game(String[] playerNames, Color[] playerColor, String selectedMap, int numPlayers) {
        // create players
        players = createPlayers(playerNames, playerColor, numPlayers);

        if (selectedMap.equals("Fantasy World")) {
            this.board = new BoardFantasyWorld();
        } else if (selectedMap.equals("Zamonien")) {
            this.board = new BoardZamonien();
        } else {
            this.board = new BoardTamriel();
        }

        this.currentPlayerIndex = 0;
        this.random = new Random();
        this.playerCards = new HashMap<>();
       // Scanner scanner = new Scanner(System.in);
        this.isDistributing = false;
        // new addition
        this.armiesToDistribute = calculateArmiesToDistribute();
        this.round = 0;


        //this.board.outputControl(); // -> to delete
        initializeGame();
    }

    // new addition
    private int calculateArmiesToDistribute(){
        int totalArmies = 0;
        for (Player player : players) {
            System.out.println(player.getName() + " army amount: " + player.getArmyCount());
            totalArmies += player.getArmyCount();
        }
        System.out.println(totalArmies);
        return totalArmies;
    }

    public int getArmiesToDistribute() { return armiesToDistribute; }

    public void decreaseArmiesToDistribute(int armies) {
        getCurrentPlayer().decreaseArmyCount(armies);
        calculateArmiesToDistribute();
    }

    private void initializeGame() {
        round++;

        for (Player player : players) {
            playerCards.put(player, new ArrayList<>());
        }
        List<Territory> allTerritories = new ArrayList<>(board.getTerritories());
        Collections.shuffle(allTerritories);

        // TODO add player choose their own territory
        for (int i = 0; i < allTerritories.size(); i++) {
            Player player = players[i % players.length];
            Territory territory = allTerritories.get(i);
            territory.setOwner(player);
            territory.addArmies(1);
            player.addTerritory(territory);
            System.out.println("Territory " + territory.getName() + " assigned to " + player.getName());
        }

        for (Player each : players){
            each.setArmyCount();
        }

        // add different cards
        allCards = getCards();

    }

    // new addition add different cards
    public List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            cards.add(new Card("Infantry"));
        }
        for (int i = 0; i < 12; i++) {
            cards.add(new Card("Cavalry"));
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Card("Artillery"));
        }
        Collections.shuffle(cards);

        return cards;
    }


    public Player[] getPlayers() {
        return players;
    }

    public boolean isDistributing() {
        return isDistributing;
    }

    public void startDistributingArmies() {
        isDistributing = true;
        //armiesToDistribute = 16;
    }

    public boolean distributeArmy(Territory territory, int armies) {
        if (!isDistributing || armies <= 0 || armies > armiesToDistribute) {
            System.out.println("is not distributing" + armies);
            return false;
        }

        Player currentPlayer = getCurrentPlayer();
        if (territory.getOwner() != currentPlayer) {
            System.out.println("gehört ihm nicht" + territory.getOwner().getName() + currentPlayer.getName());
            return false;
        }
        territory.addArmies(armies);
        armiesToDistribute -= armies;

        if (armiesToDistribute <= 0) {
            isDistributing = false;
            return true;
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    // new addition
    public Player getNextPlayer(int currentPlayerIndex) {
        if( players[players.length - 1].getIndex() == currentPlayerIndex){
            return players[0];
        } else {
            return players[currentPlayerIndex + 1];
        }
    }

    public void nextTurn() {
        if (!isDistributing) {
            reinforcePhase();
            cardReinforcementPhase();
            attackPhase();
            fortifyPhase();
            setCurrentPlayer();
            checkGameOver();
        }
        // new addition
        //TODO: rework when names are edited for index based
        if (getCurrentPlayer().getIndex() == (players.length - 1)) {
            round++;
            System.out.println("round test: " + round);
        }
    }

    //TODO rework
    public void setNextDistributingPlayer(Player nextPlayer){
        int index = nextPlayer.getIndex();
        if (index == players.length - 1){
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = index + 1;
        }
    }

    public void setCurrentPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    public boolean checkWinCondition() {
        for (Territory territory : board.getTerritories()) {
            if (territory.getOwner() != getCurrentPlayer()) {
                return false;
            }
        }
        return true;
    }

    private void reinforcePhase() {
        Player currentPlayer = getCurrentPlayer();
        int reinforcements = Math.max(3, currentPlayer.getTerritories().size() / 3);

        for (Continent continent : board.getContinents()) {
            if (currentPlayer.controlsContinent(continent)) {
                reinforcements += 3;
            }
        }

        currentPlayer.addArmies(reinforcements);
        System.out.println(currentPlayer.getName() + " receives " + reinforcements + " reinforcement armies.");
    }

    // todo rework for different card types
    private void cardReinforcementPhase() {
        Player currentPlayer = getCurrentPlayer();
        List<Card> cards = playerCards.get(currentPlayer);

        if (cards.size() >= 3) {
            int bonus = (cards.size() / 3) * 5;
            currentPlayer.addArmies(bonus);
            System.out.println(currentPlayer.getName() + " exchanges cards for " + bonus + " armies.");
            cards.subList(0, 3).clear();
        }
    }

    private void attackPhase() {
        Player currentPlayer = getCurrentPlayer();

        boolean territoryConquered = false;
        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() != currentPlayer) {
                    int attackArmies = Math.min(3, from.getArmyCount() - 1);
                    int defendArmies = Math.min(2, to.getArmyCount());
                    attackTerritory(from, to, attackArmies, defendArmies);
                    territoryConquered = true;
                    break;
                }
            }
            if (territoryConquered) break;
        }
        if (territoryConquered) {
            // addition choose a random card & remove it from the list
            int cardIndex = random.nextInt(allCards.size());
            Card newPlayerCard = allCards.get(cardIndex);
            allCards.remove(cardIndex);

            playerCards.get(currentPlayer).add(newPlayerCard);

            if (playerCards.get(currentPlayer).size() > 5) {
                cardReinforcementPhase();
            }
        }
    }

    private void fortifyPhase() {
        Player currentPlayer = getCurrentPlayer();

        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() == currentPlayer && from != to) {
                    int armiesToMove = Math.min(3, from.getArmyCount() - 1);
                    fortifyTerritory(from, to, armiesToMove);
                    System.out.println(currentPlayer.getName() + " moves " + armiesToMove + " armies from " + from.getName() + " to " + to.getName());
                    return;
                }
            }
        }
    }

    public void attackTerritory(Territory from, Territory to, int attackArmies, int defendArmies) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() != getCurrentPlayer()) {
            int[] attackDice = rollDice(attackArmies);
            int[] defendDice = rollDice(defendArmies);

            Arrays.sort(attackDice);
            Arrays.sort(defendDice);

            int losses = 0;
            for (int i = 0; i < Math.min(attackDice.length, defendDice.length); i++) {
                if (attackDice[attackDice.length - 1 - i] > defendDice[defendDice.length - 1 - i]) {
                    to.removeArmies(1);
                } else {
                    from.removeArmies(1);
                    losses++;
                }
            }

            if (to.getArmyCount() == 0) {
                to.setOwner(from.getOwner());
                to.addArmies(attackArmies - losses);
                from.removeArmies(attackArmies - losses);
                System.out.println(from.getOwner().getName() + " conquered " + to.getName());
            }
        } else {
            System.out.println("Invalid attack!");
        }
    }

    public int[] rollDice(int numDice) {
        int[] dice = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() == getCurrentPlayer()) {
            from.removeArmies(armiesToMove);
            to.addArmies(armiesToMove);
        }
    }

    public boolean isGameOver() {
        Set<Player> activePlayers = new HashSet<>();
        for (Player player : players) {
            if (!player.getTerritories().isEmpty()) {
                activePlayers.add(player);
            }
        }
        return activePlayers.size() == 1;
    }

    public void checkGameOver() {
        if (isGameOver()) {
            Player winner = getCurrentPlayer();
            System.out.println("Game Over! " + winner.getName() + " wins!");
        }
    }

    //new addition
    public int getRound(){ return round; }

    // randomize the players
    public static Player[] createPlayers(String[] playerNames, Color[] playerColors, int numPlayers) {
        List<Player> playerList = Arrays.asList(new Player[numPlayers]);
        int armyCount;
        if (numPlayers == 4){
            armyCount = 30;
        } else if (numPlayers == 3) {
            armyCount = 25;
        } else {
            armyCount = 20;
        }

        for (int i = 0; i < numPlayers; i++) {
            playerList.set(i, new Player(playerNames[i], playerColors[i], armyCount));
        }
        Collections.shuffle(playerList);

        Player[] newOrder = playerList.toArray(new Player[0]);
        for (int i = 0; i < newOrder.length; i++){
            newOrder[i].setIndex(i);
        }
        return newOrder;
    }

}
