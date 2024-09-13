import java.awt.*;
import java.util.List;

public class BoardTest extends Board {

    public BoardTest() {
        super();

        this.setWorldName("Test");
        String[] list = new String[]{"Continent 1", "Continent 2", "Continent 3", "Continent 4"};
        createContinents(list);
        createTerritories();
        String[][] layoutFantansy = new String[][]{
                {"A", "A", "A", "B", "B", "B"},
                {"C", "C", "", "", "", "F"},
                {"G", "G", "G", "E", "E", "F"},
                {"H", "H", "H", "E", "F", "F"},

        };

        setLayout(layoutFantansy);
    }

    public void createTerritories() {
        List<TerritoryInfo> territories = List.of(
                // Continent 1
                new TerritoryInfo("Territory A", "A", new Color(179, 118, 20), List.of("Territory B", "Territory C"), "Continent 1"),
                new TerritoryInfo("Territory C", "C", new Color(227, 160, 59), List.of("Territory A", "Territory G"), "Continent 1"),

                // Continent 2
                new TerritoryInfo("Territory B", "B", new Color(0, 76, 123), List.of("Territory A", "Territory F"), "Continent 2"),

                // Continent 3
                new TerritoryInfo("Territory G", "G", new Color(166, 100, 130), List.of("Territory C", "Territory E", "Territory H"), "Continent 3"),
                new TerritoryInfo("Territory H", "H", new Color(186, 14, 146), List.of("Territory G", "Territory E"), "Continent 3"),

                // Continent 4
                new TerritoryInfo("Territory E", "E", new Color(61, 120, 7), List.of("Territory H", "Territory G", "Territory F"), "Continent 4"),
                new TerritoryInfo("Territory F", "F", new Color(115, 175, 58), List.of("Territory B", "Territory E"), "Continent 4")
        );

        addTerritoriesToContinent(territories);
        setAllAdjacentTerritories(territories);
    }
}

