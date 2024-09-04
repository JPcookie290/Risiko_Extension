import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// TODO: add change if player has the whole continent
public class Player {
    private final String name;
    private final List<Territory> territories;
    private int armyCount;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.territories = new ArrayList<>();
        this.armyCount = 8; //TODO: change later for player amount
        this.cards = new ArrayList<>();
    }

    //new addition
    public void decreaseArmyCount(int armies) {
        armyCount -= armies;
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

    public void useCard() {
        if (!this.cards.isEmpty()) {
            this.cards.remove(0);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean controlsContinent(Continent continent) {
        return new HashSet<>(territories).containsAll(continent.getTerritories());
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
