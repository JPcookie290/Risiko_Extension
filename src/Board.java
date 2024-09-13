import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: rework for different maps, which have different territories and continents
public class Board {
    // addition change territories to a list
    private List<Territory> territories;
    private List<Continent> continents; //changed to List
    private String worldName;
    private String[][] layout;
    private List<WinCondition> winConditions;

    public Board() {
        this.worldName = null;
        this.territories = new ArrayList<>();
        this.continents = new ArrayList<>();
        this.layout = null;
        createWinCondition();
    }

    public String[][] getLayout(){ return this.layout; }

    public void setLayout(String[][] layout) {
        this.layout = layout;
    }

    public void setWorldName(String name) {
        this.worldName = name;
    }

    public String getWorldName() {
        return worldName;
    }

    public void createContinents(String[] list){
        for (String continent : list){
            this.getContinents().add(new Continent(continent));
        }
    }

    //testing
    public void testMapAndContinents(){
        System.out.println(this.continents);
        System.out.println(this.territories);
    }

    public int territoriesLength(){
        return territories.size();
    }


    // removed setAdjacentTerritories() since it is not needed in this version

    public Territory getTerritory(String name) {
        for (Territory territory : territories ){
            if (name.equals(territory.getName())){
                return territory;
            }
        }
        return null;
    }

    public void setAdjacentTerritories(String territoryName, String[] territoryList){
        for (String adjTerritory : territoryList){
            getTerritory(territoryName).addAdjacentTerritory(getTerritory(adjTerritory));
        }
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public List<Continent> getContinents() {
        return continents;
    }

    // new addition
    public void addTerritory(Territory territory){
        territories.add(territory);
    }

    public Continent getContinent(String name) {
        for (Continent continent : continents ){
            if (name.equals(continent.getName())){
                return continent;
            }
        }
        return null;
    }

    // for testing purposes
    public void outputControl() {
        for (Continent continent : continents) {
            int num = 1;
            System.out.println();
            System.out.println("The " + continent.getName() + " region has these territories:");
            for (Territory territory : continent.getTerritories()){
                System.out.println(String.valueOf(num) + ". " + territory);

                if (! territory.getAdjacentTerritories().isEmpty()){
                    System.out.println("The adjacent territories:");
                    for (Territory adjTerritory : territory.getAdjacentTerritories()){
                        System.out.print(adjTerritory + ", ");
                    }
                    System.out.println();
                }
                num++;
            }
        }
    }

    // To add territories to a continent
    public void addTerritoriesToContinent(List<TerritoryInfo> territoryInfos) {
        for (TerritoryInfo info : territoryInfos) {
            Territory newTerritory = new Territory(info.getName(), info.getContinent(), info.getAbbreviation(), info.getTerritoryColor());
            getContinent(info.getContinent()).addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
    }

    // Set adjacent territories
    public void setAllAdjacentTerritories(List<TerritoryInfo> territoryInfos) {
        for (TerritoryInfo info : territoryInfos) {
            setAdjacentTerritories(info.getName(), info.getAdjacentTerritories().toArray(new String[0]));
        }
    }



    public List<WinCondition> getWinConditions(String board){
        List<WinCondition> currentWinConditions = new ArrayList<>();
        for (WinCondition con : winConditions){
            if (con.getBoard().equals(board)){
                currentWinConditions.add(con);
            }
        }
        return currentWinConditions;
    }

    private void createWinCondition(){
        winConditions =
                List.of(
                        // Test
                        new WinCondition("Completely own 2 Continents", 1, "Test"),
                        new WinCondition("Completely own Continent 1 and 3", 1, "Test"),
                        new WinCondition("Own one territory of each continent", 2, "Test"),
                        // Fantasy World => Eteadrodia
                        new WinCondition("Completely own 2 Continents", 1, "Eteadrodia"),
                        new WinCondition("Completely own 3 continents", 2, "Eteadrodia"),
                        new WinCondition("Own all islands", 3, "Eteadrodia"),
                        new WinCondition("Completely own the Farmish Empire and the Dominion of Watchhaim", 4, "Eteadrodia"),
                        new WinCondition("Own 18 Territories", 5, "Eteadrodia"),
                        new WinCondition("Own the Diru continent and each neighbouring territory", 6, "Eteadrodia"),
                        new WinCondition("Own 4 territories on each continent", 7, "Eteadrodia"),
                        // Zamonien
                        new WinCondition("Completely own 2 Continents", 1, "Zamonien"),
                        new WinCondition("Completely own Zentralzamonien and 2 other continents", 2, "Zamonien"),
                        new WinCondition("Own all islands", 3, "Zamonien"),
                        new WinCondition("Completely own two opposite Continents (Südzamonien & Nordzamonien or Ostzamonien & Westzamonien)", 4, "Zamonien"),
                        new WinCondition("Own 18 Territories", 5, "Zamonien"),
                        // Tamriel
                        new WinCondition("Own Cyrodill", 1, "Tamriel"),
                        new WinCondition("Completely own 2 continents", 2, "Tamriel"),
                        new WinCondition("Own Cyrodill and each neighbouring territory", 3, "Tamriel"),
                        new WinCondition("Own a total of 15 territories in the Aldmeri Dominion and Ebonheart Pact", 4, "Tamriel"),
                        new WinCondition("Own Daggerfall Covenant and at least 3 territories in the Aldmeri Dominion and Ebonheart Pact", 4, "Tamriel")

        );
    }

    // Check if a player owns a specific continent
    public boolean ownsContinent(Player player, Continent continent) {
        List<Territory> playerTerritories = player.getTerritories();

        for (Territory territory : continent.getTerritories()) {
            if (!playerTerritories.contains(territory)) {
                return false;
            }
        }

        return true;
    }

    // Check if a player owns all continents on the board
    public boolean ownsAllContinents(Player player) {
        for (Continent continent : this.getContinents()) {
            if (!ownsContinent(player, continent)) {
                return false;
            }
        }
        return true;
    }

    // To use if the Gameboard is Tamriel to check if Cyrodyll is available to attack
    public boolean isCyrodillAttackable(Player player) {
        // Count how many continents the player owns
        int ownedContinents = (int) continents.stream()
                .filter(continent -> ownsContinent(player, continent))
                .count();

        // List of territories surrounding Cyrodill
        List<Territory> surroundingTerritories = List.of(
                getTerritory("Craglorn"),
                getTerritory("West Weald"),
                getTerritory("Northern Elsweyr"),
                getTerritory("Stonefalls"),
                getTerritory("The Rift"),
                getTerritory("Reaper's March"),
                getTerritory("Deshaan")
        );

        // Count how many surrounding territories the player owns
        long ownedSurroundingTerritories = player.getTerritories().stream()
                .filter(surroundingTerritories::contains)
                .count();

        // Return true if the player owns 2 or more continents or 7 surrounding territories
        return ownedContinents >= 2 || ownedSurroundingTerritories >= 7;
    }
}
