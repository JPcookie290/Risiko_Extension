import java.awt.*;
import java.util.Arrays;
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
        List<TerritoryInfo> territories = Arrays.asList(
                // Framish Empire
                new TerritoryInfo("Vad", "V", new Color(100, 166, 101), Arrays.asList("Kingdom of Cleolesbia", "Meuland", "Golf of Ylvena-Jiku"), "Framish Empire"),
                new TerritoryInfo("Meuland", "ME", new Color(161, 186, 92), Arrays.asList("Vad", "Golf of Ylvena-Jiku", "Kingdom of Cleolesbia", "Brampland", "Tihul"), "Framish Empire"),
                new TerritoryInfo("Tihul", "TI", new Color(107, 128, 44), Arrays.asList("Meuland", "Golf of Ylvena-Jiku", "Noluch", "Tutland", "Miama", "Principality of Wickwarn"), "Framish Empire"),
                new TerritoryInfo("Tutland", "TU", new Color(84, 174, 20), Arrays.asList("Tihul", "Noluch", "Miama"), "Framish Empire"),
                new TerritoryInfo("Noluch", "N", new Color(76, 128, 44), Arrays.asList("Golf of Ylvena-Jiku", "Tihul", "Tutland"), "Framish Empire"),
                new TerritoryInfo("Golf of Ylvena-Jiku", "GYJ", new Color(110, 165, 73), Arrays.asList("Vad", "Meuland", "Noluch", "Tihul"), "Framish Empire"),

                // Commonwealth of Poruntas
                new TerritoryInfo("Kingdom of Cleolesbia", "KC", new Color(234, 149, 89), Arrays.asList("Vad", "Meuland", "Brampland", "Seagela"), "Commonwealth of Poruntas"),
                new TerritoryInfo("Brampland", "BRA", new Color(195, 115, 19), Arrays.asList("Kingdom of Cleolesbia", "Seagela", "Principality of Bunteria", "Principality of Wickwarn"), "Commonwealth of Poruntas"),
                new TerritoryInfo("Principality of Bunteria", "PB", new Color(255, 169, 53), Arrays.asList("Seagela", "Brampland", "Kingdom of Modh", "Principality of Wickwarn", "Duchy of Axbria"), "Commonwealth of Poruntas"),
                new TerritoryInfo("Kingdom of Modh", "KM", new Color(225, 120, 16), Arrays.asList("Principality of Bunteria", "Principality of Wickwarn", "Republic of Nird", "Bridford", "Duchy of Axbria"), "Commonwealth of Poruntas"),
                new TerritoryInfo("Principality of Wickwarn", "PW", new Color(216, 99, 35), Arrays.asList("Kingdom of Modh", "Principality of Bunteria", "Brampland", "Meuland", "Tihul", "Miama", "Republic of Nird"), "Commonwealth of Poruntas"),
                new TerritoryInfo("Seagela", "S", new Color(209, 105, 58), Arrays.asList("Kingdom of Cleolesbia", "Brampland", "Principality of Bunteria"), "Commonwealth of Poruntas"),

                // Dominion of Watchhaim
                new TerritoryInfo("Grantria", "G", new Color(108, 74, 149), Arrays.asList("Alcesbruian Mountains", "Lonanese Theocracy"), "Dominion of Watchhaim"),
                new TerritoryInfo("Harland", "H", new Color(98, 19, 195), Arrays.asList("Miama", "Selfan Districts", "Alcesbruian Mountains", "Kingdom of Geumguk", "Berja"), "Dominion of Watchhaim"),
                new TerritoryInfo("Selfan Districts", "SD", new Color(154, 101, 216), Arrays.asList("Miama", "Harland", "Alcesbruian Mountains"), "Dominion of Watchhaim"),
                new TerritoryInfo("Alcesbruian Mountains", "AM", new Color(86, 36, 154), Arrays.asList("Grantria", "Selfan Districts", "Harland", "Lonanese Theocracy", "Kingdom of Geumguk"), "Dominion of Watchhaim"),
                new TerritoryInfo("Miama", "MI", new Color(100, 79, 131), Arrays.asList("Selfan Districts", "Harland", "Tutland", "Tihul", "Principality of Wickwarn", "Republic of Nird", "Berja"), "Dominion of Watchhaim"),
                new TerritoryInfo("Lonanese Theocracy", "LT", new Color(144, 66, 241), Arrays.asList("Grantria", "Alcesbruian Mountains", "Kingdom of Geumguk"), "Dominion of Watchhaim"),

                // Diru
                new TerritoryInfo("Republic of Nird", "RN", new Color(251, 255, 90), Arrays.asList("Kingdom of Modh", "Principality of Wickwarn", "Miama", "Berja", "Bridford"), "Diru"),
                new TerritoryInfo("Midbury Empire", "EM", new Color(197, 198, 69), Arrays.asList("Duchy of Axbria", "Bridford", "Kingdom of Geumguk"), "Diru"),
                new TerritoryInfo("Kingdom of Geumguk", "KG", new Color(252, 255, 5), Arrays.asList("Midbury Empire", "Bridford", "Berja", "Harland", "Alcesbruian Mountains", "Lonanese Theocracy"), "Diru"),
                new TerritoryInfo("Berja", "BE", new Color(255, 242, 104), Arrays.asList("Kingdom of Geumguk", "Republic of Nird", "Miama", "Harland", "Bridford"), "Diru"),
                new TerritoryInfo("Duchy of Axbria", "DA", new Color(255, 227, 42), Arrays.asList("Midbury Empire", "Bridford", "Kingdom of Modh", "Principality of Bunteria"), "Diru"),
                new TerritoryInfo("Bridford", "BRI", new Color(255, 221, 0), Arrays.asList("Midbury Empire", "Duchy of Axbria", "Kingdom of Modh", "Republic of Nird", "Berja", "Kingdom of Geumguk"), "Diru")
        );

        addTerritoriesToContinent(territories);
        setAllAdjacentTerritories(territories);
    }
}
