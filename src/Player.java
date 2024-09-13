import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// TODO: add change if player has the whole continent
public class Player {
    private final String name;
    private final List<Territory> territories;
    private int armyCount;
    private List<Card> cards;
    // new addition
    private Color color;
    private int index;
    private boolean threeCardsType;
    private WinCondition winCondition;

    public Player(String name, Color color, int armyCount) {
        this.name = name;
        this.territories = new ArrayList<>();
        this.armyCount = armyCount;
        this.cards = new ArrayList<>();
        this.color = color;
        this.index = 0;
        this.threeCardsType = false;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        this.territories.add(territory);
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }


    // reworked
    public String useCard() {
        int infantry = 0, cavalry = 0, artillery = 0;

        // Count the number of each card type
        for (Card card : cards) {
            switch (card.getType()) {
                case "Infantry" -> infantry++;
                case "Cavalry" -> cavalry++;
                case "Artillery" -> artillery++;
            }
        }

        // Check for "One of Each"
        if (infantry >= 1 && cavalry >= 1 && artillery >= 1) {
            addArmies(5);
            // Remove one instance of each card type
            removeCard("Infantry", 1);
            removeCard("Cavalry", 1);
            removeCard("Artillery", 1);
            return "One of Each";
        }

        // Check for three Infantry cards
        if (infantry >= 3) {
            addArmies(4);
            // Remove three Infantry cards
            removeCard("Infantry", 3);
            return "Infantry";
        }

        // Check for three Cavalry cards
        if (cavalry >= 3) {
            addArmies(5);
            // Remove three Cavalry cards
            removeCard("Cavalry", 3);
            return "Cavalry";
        }

        // Check for three Artillery cards
        if (artillery >= 3) {
            addArmies(6);
            // Remove three Artillery cards
            removeCard("Artillery", 3);
            return "Artillery";
        }

        return "There are no cards to trade";
    }


    // new addition
    private void removeCard(String cardType, int count) {
        for (int i = 0; i < count; i++) {
            Card cardToRemove = getCard(cardType);
            if (cardToRemove != null) {
                cards.remove(cardToRemove);  // Remove the first instance of the card
            } else {
                System.out.println("Warning: No more " + cardType + " cards available to remove.");
                break;
            }
        }
    }


    private Card getCard(String type){
        for (Card card : cards) {
            if (card.getType().contains(type)){
                return card;
            }
        }
        return null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean controlsContinent(Continent continent) {
        return new HashSet<>(territories).containsAll(continent.getTerritories());
    }

    // new addition
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setWinCondition(WinCondition winCondition) {
        this.winCondition = winCondition;
    }

    public WinCondition getWinCondition() {
        return winCondition;
    }

    public boolean checkWin(){
        return winCondition.getConditionMet();
    }

    // testing output

    public void printPlayerInfo(){
        int num = 1;
        System.out.println("Player name: " + name);
        System.out.println("Current armies: " + armyCount);
        System.out.println("Currently owned territories: ");
        for (Territory territory : territories){
            System.out.print(num + ". " + territory.getName() + "(Continent: " + territory.getAssignedContinent() + ")");
        }
        System.out.println();
        System.out.println("Current Cards");
        for (Card card : cards){
            System.out.print(card.getType());
        }
    }
}
