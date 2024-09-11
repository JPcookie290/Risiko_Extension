import java.awt.*;
import java.util.List;

public class BoardTest extends Board{

    public BoardTest() {
        super();

        this.setWorldName("Test");
        String[] list = new String[]{"Continent 1", "Continent 2", "Continent 3", "Continent 4"};
        createContinents(list);
        createTerritories();
        String[][] layoutFantansy = new String[][]{
                {"A", "A", "A", "B", "B", "B"},
                {"C", "C", "D", "D", "D", "F"},
                {"G", "G", "G", "E", "E", "F"},
                {"H", "H", "H", "E", "F", "F"},

        };
        setLayout(layoutFantansy);
    }

    public void createTerritories() {
        // Continent 1
        List<TerritoryInfo> continentOneTerritories = List.of(
                new TerritoryInfo("Territory A", "A", new Color(179, 118, 20), List.of("Territory B", "Territory C", "Territory D")),
                new TerritoryInfo("Territory C", "C", new Color(227, 160, 59), List.of("Territory A", "Territory D", "Territory G"))
        );
        addTerritoriesToContinent("Continent 1", continentOneTerritories);

        // Continent 2
        List<TerritoryInfo> continentTwoTerritories = List.of(
                new TerritoryInfo("Territory B", "B", new Color(0, 76, 123), List.of("Territory A", "Territory D", "Territory F")),
                new TerritoryInfo("Territory D", "D", new Color(78, 129, 193), List.of("Territory A", "Territory C", "Territory B", "Territory F", "Territory E", "Territory G"))
        );
        addTerritoriesToContinent("Continent 2", continentTwoTerritories);

        // Continent 3
        List<TerritoryInfo> continentThreeTerritories = List.of(
                new TerritoryInfo("Territory G", "G", new Color(166, 100, 130), List.of("Territory C", "Territory D", "Territory E", "Territory H")),
                new TerritoryInfo("Territory H", "H", new Color(186, 14, 146), List.of("Territory G", "Territory E"))
        );
        addTerritoriesToContinent("Continent 3", continentThreeTerritories);

        // Continent 4
        List<TerritoryInfo> continentFourTerritories = List.of(
                new TerritoryInfo("Territory E", "E", new Color(61, 120, 7), List.of("Territory H", "Territory G", "Territory D", "Territory F")),
                new TerritoryInfo("Territory F", "F", new Color(115, 175, 58), List.of("Territory B", "Territory D", "Territory E"))
        );
        addTerritoriesToContinent("Continent 4", continentFourTerritories);
    }

    // Helper method to add territories to a continent
    private void addTerritoriesToContinent(String continentName, List<TerritoryInfo> territoryInfos) {
        for (TerritoryInfo info : territoryInfos) {
            Territory newTerritory = new Territory(info.getName(), continentName, info.getAbbreviation(), info.getTerritoryColor());
            getContinent(continentName).addTerritory(newTerritory);
            addTerritory(newTerritory);

            // Set adjacent territories
            setAdjacentTerritories(info.getName(), info.getAdjacentTerritories().toArray(new String[0]));
        }
    }


}