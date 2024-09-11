import java.awt.*;
import java.util.List;

public class BoardFantasyWorld extends Board{

    public BoardFantasyWorld() {
        super();

        this.setWorldName("Eteadrodia");
        String[] list = new String[]{"Framish Empire", "Commonwealth of Poruntas", "Dominion of Watchhaim", "Diru"};
        createContinents(list);
        createTerritories();
        String[][] layoutFantansy = new String[][]{
                {"", "", "", "GYJ", "N", "N", "N", "", "", "", "", "", "SD", "SD", "SD", "AM", "G", "G", "G", "G"},
                {"", "", "", "GYJ", "N", "N", "N", "TU", "", "", "SD", "SD", "SD", "SD", "SD", "AM", "AM", "G", "G", "G"},
                {"GYJ", "", "GYJ", "GYJ", "GYJ", "N", "N", "TU", "TU", "MI", "MI", "SD", "SD", "H", "H", "AM", "AM", "G", "G", "G"},
                {"GYJ", "GYJ", "GYJ", "GYJ", "GYJ", "TI", "TU", "TU", "TU", "MI", "MI", "MI", "H", "H", "H", "AM", "AM", "AM", "LT", "LT"},
                {"V", "GYJ", "ME", "ME", "ME", "TI", "TI", "TU", "TI", "TI", "MI", "MI", "H", "H", "H", "AM", "AM", "AM", "LT", "LT"},
                {"V", "V", "V", "ME", "ME", "TI", "TI", "TI", "TI", "MI", "MI", "MI", "BE", "BE", "H", "AM", "KG", "LT", "LT", "LT"},
                {"V", "V", "V", "ME", "ME", "ME", "TI", "TI", "PW", "PW", "RN", "RN", "BE", "BE", "H", "KG", "KG", "KG", "LT", "LT"},
                {"V", "V", "ME", "ME", "ME", "ME", "PW", "PW", "PW", "PW", "RN", "RN", "BE", "BE", "BE", "BE", "KG", "KG", "LT", "LT"},
                {"V", "KC", "KC", "ME", "BRA", "BRA", "PW", "PW", "PW", "PW", "RN", "RN", "BE", "BE", "BE", "KG", "KG", "KG", "KG", ""},
                {"", "KC", "KC", "BRA", "BRA", "BRA", "PW", "PW", "PW", "PW", "KM", "RN", "BRI", "BE", "BE", "KG", "KG", "KG", "", ""},
                {"", "KC", "KC", "BRA", "BRA", "BRA", "PB", "PB", "KM", "KM", "KM", "BRI", "BRI", "BRI", "BRI", "BRI", "EM", "EM", "", ""},
                {"", "", "KC", "S", "BRA", "BRA", "PB", "PB", "KM", "KM", "KM", "KM", "BRI", "BRI", "BRI", "BRI", "", "EM", "EM", ""},
                {"", "", "", "S", "S", "PB", "PB", "PB", "PB", "KM", "KM", "DA", "DA", "BRI", "", "", "", "EM", "EM", ""},
                {"", "", "", "S", "S", "S", "PB", "PB", "PB", "PB", "PB", "DA", "DA", "DA", "DA", "", "EM", "EM", "EM", "EM"},
                {"", "S", "S", "S", "S", "S", "", "PB", "PB", "PB", "DA", "DA", "DA", "DA", "DA", "DA", "EM", "EM", "EM", "EM"},
        };
        setLayout(layoutFantansy);
    }

    public void createTerritories() {
        // Continent Framish Empire
        List<TerritoryInfo> framishEmpireTerritories = List.of(
                new TerritoryInfo("Vad", "V", new Color(100, 166, 101), List.of("Kingdom of Cleolesbia", "Meuland", "Golf of Ylvena-Jiku")),
                new TerritoryInfo("Meuland", "ME", new Color(161, 186, 92), List.of("Vad", "Golf of Ylvena-Jiku", "Kingdom of Cleolesbia", "Brampland", "Tihul")),
                new TerritoryInfo("Tihul", "TI", new Color(107, 128, 44), List.of("Meuland", "Golf of Ylvena-Jiku", "Noluch", "Tutland", "Miama", "Principality of Wickwarn")),
                new TerritoryInfo("Tutland", "TU", new Color(84, 174, 20), List.of("Tihul", "Noluch", "Miama")),
                new TerritoryInfo("Noluch", "N", new Color(76, 128, 44), List.of("Golf of Ylvena-Jiku", "Tihul", "Tutland")),
                new TerritoryInfo("Golf of Ylvena-Jiku", "GYJ", new Color(110, 165, 73), List.of("Vad", "Meuland", "Noluch", "Tihul"))
        );
        addTerritoriesToContinent("Framish Empire", framishEmpireTerritories);

        // Continent Commonwealth of Poruntas
        List<TerritoryInfo> poruntasTerritories = List.of(
                new TerritoryInfo("Kingdom of Cleolesbia", "KC", new Color(234, 149, 89), List.of("Vad", "Meuland", "Brampland", "Seagela")),
                new TerritoryInfo("Brampland", "BRA", new Color(195, 115, 19), List.of("Kingdom of Cleolesbia", "Seagela", "Principality of Bunteria", "Principality of Wickwarn")),
                new TerritoryInfo("Principality of Bunteria", "PB", new Color(255, 169, 53), List.of("Seagela", "Brampland", "Kingdom of Modh", "Principality of Wickwarn", "Duchy of Axbria")),
                new TerritoryInfo("Kingdom of Modh", "KM", new Color(225, 120, 16), List.of("Principality of Bunteria", "Principality of Wickwarn", "Republic of Nird", "Bridford", "Duchy of Axbria")),
                new TerritoryInfo("Principality of Wickwarn", "PW", new Color(216, 99, 35), List.of("Kingdom of Modh", "Principality of Bunteria", "Brampland", "Meuland", "Tihul", "Miama", "Republic of Nird")),
                new TerritoryInfo("Seagela", "S", new Color(209, 105, 58), List.of("Kingdom of Cleolesbia", "Brampland", "Principality of Bunteria"))
        );
        addTerritoriesToContinent("Commonwealth of Poruntas", poruntasTerritories);

        // Continent Dominion of Watchhaim
        List<TerritoryInfo> watchhaimTerritories = List.of(
                new TerritoryInfo("Grantria", "G", new Color(108, 74, 149), List.of("Alcesbruian Mountains", "Lonanese Theocracy")),
                new TerritoryInfo("Harland", "H", new Color(98, 19, 195), List.of("Miama", "Selfan Districts", "Alcesbruian Mountains", "Kingdom of Geumguk", "Berja")),
                new TerritoryInfo("Selfan Districts", "SD", new Color(154, 101, 216), List.of("Miama", "Harland", "Alcesbruian Mountains")),
                new TerritoryInfo("Alcesbruian Mountains", "AM", new Color(86, 36, 154), List.of("Grantria", "Selfan Districts", "Harland", "Lonanese Theocracy", "Kingdom of Geumguk")),
                new TerritoryInfo("Miama", "MI", new Color(100, 79, 131), List.of("Selfan Districts", "Harland", "Tutland", "Tihul", "Principality of Wickwarn", "Republic of Nird", "Berja")),
                new TerritoryInfo("Lonanese Theocracy", "LT", new Color(144, 66, 241), List.of("Grantria", "Alcesbruian Mountains", "Kingdom of Geumguk"))
        );
        addTerritoriesToContinent("Dominion of Watchhaim", watchhaimTerritories);

        // Continent Diru
        List<TerritoryInfo> diruTerritories = List.of(
                new TerritoryInfo("Republic of Nird", "RN", new Color(251, 255, 90), List.of("Kingdom of Modh", "Principality of Wickwarn", "Miama", "Berja", "Bridford")),
                new TerritoryInfo("Midbury Empire", "EM", new Color(197, 198, 69), List.of("Duchy of Axbria", "Bridford", "Kingdom of Geumguk")),
                new TerritoryInfo("Kingdom of Geumguk", "KG", new Color(252, 255, 5), List.of("Midbury Empire", "Bridford", "Berja", "Harland", "Alcesbruian Mountains", "Lonanese Theocracy")),
                new TerritoryInfo("Berja", "BE", new Color(255, 242, 104), List.of("Kingdom of Geumguk", "Republic of Nird", "Miama", "Harland", "Bridford")),
                new TerritoryInfo("Duchy of Axbria", "DA", new Color(255, 227, 42), List.of("Midbury Empire", "Bridford", "Kingdom of Modh", "Principality of Bunteria")),
                new TerritoryInfo("Bridford", "BRI", new Color(255, 221, 0), List.of("Midbury Empire", "Duchy of Axbria", "Kingdom of Modh", "Republic of Nird", "Berja", "Kingdom of Geumguk"))
        );
        addTerritoriesToContinent("Diru", diruTerritories);
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
