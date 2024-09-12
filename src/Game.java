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
            //this.board = new BoardTamriel();
            this.board = new BoardTest();
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
        System.out.println(getTerritoriesToSelectAmount());
        initializeGame();
    }

    // new addition
    private int calculateArmiesToDistribute(){
        int totalArmies = 0;
        for (Player player : players) {
            System.out.println(player.getName() + " army amount: " + player.getArmyCount());
            totalArmies += player.getArmyCount();
        }
        return totalArmies;
    }

    // function to get the amount of territories each player can choose
    public int getTerritoriesToSelectAmount() {
        return board.territoriesLength() / players.length;
    }

    private void setArmiesToDistribute(int armies) {
        this.armiesToDistribute -= armies;
        if(armiesToDistribute <= 0){
            isDistributing = false;
        }
    }

    public void setRound(){ this.round++; }

    public int getArmiesToDistribute() { return armiesToDistribute; }

    public void decreaseArmiesToDistribute(int armies) {
        getCurrentPlayer().removeArmies(armies);
        setArmiesToDistribute(armies);
    }

    private void initializeGame() {

        for (Player player : players) {
            playerCards.put(player, new ArrayList<>());
        }

        // removed code because of player selection of the territories

        // add different cards
        allCards = getCards();
    }

    // addition for players to select territories
    public boolean territorySelection(Territory territory){

        Player currentPlayer = getCurrentPlayer();

        if (territory.getOwner() == null){
            currentPlayer.addTerritory(territory);
            territory.setOwner(currentPlayer);
            territory.addArmies(1);
            currentPlayer.removeArmies(1);
            return true;
        }
        return false;

    }

    // moved from GUI
    public int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
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

    // new addition
    public Card getRandomCard(){
        int randomIndex = random.nextInt(allCards.size());
        Card randomCard = allCards.get(randomIndex);
        allCards.remove(randomIndex);
        return randomCard;
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isDistributing() {
        return isDistributing;
    }

    public void startDistributingArmies() {
        isDistributing = true;
        armiesToDistribute = calculateArmiesToDistribute();
    }

    public boolean distributeArmy(Territory territory, int armies) {
        if (!isDistributing || armies <= 0 || armies > armiesToDistribute) {
            return false;
        }
        // removed code that became redundant
        territory.addArmies(armies);

        armiesToDistribute = calculateArmiesToDistribute();

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    // new addition => delete

    public void nextTurn() {
        if (!isDistributing) {
            //TODO check
            setCurrentPlayer();
            reinforcePhase();
            cardReinforcementPhase();
            attackPhase();
            fortifyPhase();
            checkGameOver();
        }
        // new addition
        if (getCurrentPlayer().getIndex() == (players.length - 1)) {
            setRound();
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
            playerCards.get(currentPlayer).add(getRandomCard());

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

    //TODO check
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

    public Color getTerritoryColor(String abbr){
        List<Territory> territories = board.getTerritories();
        for ( Territory territory : territories){
            if( territory.getAbbr().equals(abbr)){
                return territory.getColor();
            }
        }
        return new Color(103, 133, 163);
    }

    public Territory getTerritoryByAbbr(String abbr){
        List<Territory> territories = board.getTerritories();
        for (Territory territory : territories){
            if (territory.getAbbr().equals(abbr)){
                return territory;
            }
        }
        return null;
    }

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

    public String addCardsBack(String type) {
        if (type.equals("One of Each")) {
            allCards.add(new Card("Infantry"));
            allCards.add(new Card("Cavalry"));
            allCards.add(new Card("Artillery"));
        } else {
            for (int i = 0; i < 3; i++) {
                allCards.add(new Card(type));
            }
        }

        Collections.shuffle(allCards);

        return switch (type) {
            case "Infantry" -> "You have received 4 additional armies.";
            case "Artillery" -> "You have received 6 additional armies.";
            default -> "You have received 5 additional armies."; // for "Cavalry" and "One of Each"
        };
    }


}
