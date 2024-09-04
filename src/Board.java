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

    public Board() {
        this.worldName = null;
        this.territories = new ArrayList<>();
        this.continents = new ArrayList<>();

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


    //TODO rework to include different map choices
    /*private void setAdjacentTerritories() {
        // Territory 1
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 7"));

        // Territory 2
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 8"));

        // Territory 3
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 9"));

        // Territory 4
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 10"));

        // Territory 5
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 11"));

        // Territory 6
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 7
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 8
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 14"));

        // Territory 9
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 15"));

        // Territory 10
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 16"));

        // Territory 11
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 17"));

        // Territory 12
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 18"));

        // Territory 13
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 19"));

        // Territory 14
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 15
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 16
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 17
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 18
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 19
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 20
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 21
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 22
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 23
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 24
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 23"));
    }
    */

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
}
