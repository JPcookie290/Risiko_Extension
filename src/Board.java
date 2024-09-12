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

    public Board() {
        this.worldName = null;
        this.territories = new ArrayList<>();
        this.continents = new ArrayList<>();
        this.layout = null;

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
}
