import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final String name;
    private Player owner;
    private int armyCount;
    private final List<Territory> adjacentTerritories;
    // new additions
    private String assignedContinent;
    private boolean neutrality;
    private Color color;
    private String abbr;
    private boolean firstPanel;
    private boolean secondPanel;

    public Territory(String name, String assignedContinent, String abbr, Color color) {
        this.name = name;
        this.owner = null;
        this.armyCount = 0;
        this.adjacentTerritories = new ArrayList<>();
        // new additions
        this.neutrality = getNeutrality();
        this.assignedContinent = assignedContinent;
        this.color = color;
        this.abbr = abbr;
        this.firstPanel = true;
        this.secondPanel = false;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }

    // new additions
    public boolean getNeutrality(){
        return this.owner == null;
    }

    public String getAbbr(){
        return this.abbr;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFirstPanel() {
        return firstPanel;
    }

    public boolean isSecondPanel() {
        return secondPanel;
    }

    public void setFirstPanel() {
        this.firstPanel = !firstPanel;
    }

    public void setSecondPanel() {
        this.secondPanel = !secondPanel;
    }

    public void removeOwner(Player owner) {
        if (armyCount == 0){
            setOwner(null);
        }
    }

    public void setAssignedContinent(String continent){
        this.assignedContinent = continent;
    }

    public String getAssignedContinent() {
        return assignedContinent;
    }

    // end new additions

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
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
