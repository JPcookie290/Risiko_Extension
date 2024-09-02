import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final String name;
    private Player owner;
    private int armyCount;
    private final List<Territory> adjacentTerritories;
    private boolean neutrality; // new addition

    public Territory(String name) {
        this.name = name;
        this.owner = null;
        this.armyCount = 0;
        this.adjacentTerritories = new ArrayList<>();
        this.neutrality = getNeutrality(); // new addition
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }

    // new addition
    public boolean getNeutrality(){
        return this.owner == null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // new addition
    public void removeOwner(Player owner) {
        if (armyCount == 0){
            setOwner(null);
        }
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;;
    }

    public List<Territory> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    public void addAdjacentTerritory(Territory territory) {
        if (!adjacentTerritories.contains(territory)) {
            adjacentTerritories.add(territory);
        }
    }

}
