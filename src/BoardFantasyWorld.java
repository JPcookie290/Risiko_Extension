import java.awt.*;

public class BoardFantasyWorld extends Board{

    public BoardFantasyWorld() {
        super();

        this.setWorldName("Eteadrodia");
        String[] list = new String[]{"Framish Empire", "Commonwealth of Poruntas", "Dominion of Watchhaim", "Diru"};
        createContinents(list);
        createTerritories();
        setAllAdjacentTerritories();
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

    // maybe change 3 arrays into one
    // creates and adds territories to the continents
    public void createTerritories() {
        // Continent Framish Empire
        String[] continentOneList = new String[]{"Vad", "Meuland", "Tihul", "Tutland", "Noluch", "Golf of Ylvena-Jiku"};
        String[] continentOneAbbr = new String[] {"V", "ME", "TI", "TU", "N", "GYJ"};
        Color[] continentOneColor = new Color[]{new Color(100, 166, 101), new Color(161, 186, 92), new Color(107, 128, 44), new Color(84, 174, 20), new Color(76, 128, 44), new Color(110, 165, 73)};
        for (int i = 0; i < continentOneList.length; i++) {
            Territory newTerritory = new Territory(continentOneList[i], "Framish Empire", continentOneAbbr[i], continentOneColor[i]);
            getContinent("Framish Empire").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // old version => rewritten for territory color
        /*for ( String territoryName : continentOneList ){
            Territory newTerritory = new Territory(territoryName, "Framish Empire");
            getContinent("Framish Empire").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }*/
        // Continent Commonwealth of Poruntas
        String[] continentTwoList = new String[]{"Kingdom of Cleolesbia", "Brampland", "Principality of Bunteria", "Kingdom of Modh", "Principality of Wickwarn", "Seagela"};
        String[] continentTwoAbbr = new String[]{"KC", "BRA", "PB", "KM", "PW", "S"};
        Color[] continentTwoColor = new Color[]{new Color(234, 149, 89), new Color(195, 115, 19), new Color(255, 169, 53), new Color(225, 120, 16), new Color(216, 99, 35), new Color(209, 105, 58)};
        for (int i = 0; i < continentTwoList.length; i++){
            Territory newTerritory = new Territory(continentTwoList[i], "Commonwealth of Poruntas", continentTwoAbbr[i], continentTwoColor[i]);
            getContinent("Commonwealth of Poruntas").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Dominion of Watchhaim
        String[] continentThreeList = new String[]{"Grantria", "Harland", "Selfan Districts", "Alcesbruian Mountains", "Miama", "Lonanese Theocracy" };
        String[] continentThreeAbbr = new String[]{"G", "H", "SD", "AM", "MI", "LT"};
        Color[] continentThreeColor = new Color[]{new Color(108, 74, 149), new Color(98, 19, 195), new Color(154, 101, 216), new Color(86, 36, 154), new Color(100, 79, 131), new Color(144, 66, 241)};
        for (int i = 0; i < continentThreeList.length; i++){
            Territory newTerritory = new Territory(continentThreeList[i], "Dominion of Watchhaim", continentThreeAbbr[i], continentThreeColor[i]);
            getContinent("Dominion of Watchhaim").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Diru
        String[] continentFourList = new String[]{"Republic of Nird", "Midbury Empire", "Kingdom of Geumguk", "Berja", "Duchy of Axbria", "Bridford"};
        String[] continentFourAbbr = new String[]{"RN", "EM", "KG", "BE", "DA", "BRI"};
        Color[] continentFourColor = new Color[]{new Color(251, 255, 90), new Color(197, 198, 69), new Color(252, 255, 5), new Color(255, 242, 104), new Color(255, 227, 42), new Color(255, 221, 0)};
        for (int i = 0; i < continentFourList.length; i++){
            Territory newTerritory = new Territory(continentFourList[i], "Diru", continentFourAbbr [i], continentFourColor[i]);
            getContinent("Diru").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
    }

    // Function to add the adjacent territories
    private void setAllAdjacentTerritories(){

        /* --- Framish Empire --- */
        // Vad
        setAdjacentTerritories("Vad", new String[]{"Kingdom of Cleolesbia", "Meuland", "Golf of Ylvena-Jiku"});
        // Meuland
        setAdjacentTerritories("Meuland", new String[]{"Vad", "Golf of Ylvena-Jiku", "Kingdom of Cleolesbia", "Brampland", "Tihul"});
        // Tihul
        setAdjacentTerritories("Tihul", new String[]{"Meuland", "Golf of Ylvena-Jiku", "Noluch", "Tutland", "Miama", "Principality of Wickwarn"});
        // Tutland
        setAdjacentTerritories("Tutland", new String[]{"Tihul", "Noluch", "Miama"});
        // Noluch
        setAdjacentTerritories("Noluch", new String[]{"Golf of Ylvena-Jiku", "Tihul", "Tutland"});
        // Golf of Ylvena-Jiku
        setAdjacentTerritories("Golf of Ylvena-Jiku", new String[]{"Vad", "Meuland", "Noluch", "Tihul"});

        /* --- Commonwealth of Poruntas --- */
        // Kingdom of Cleolesbia
        setAdjacentTerritories("Kingdom of Cleolesbia", new String[]{"Vad", "Meuland", "Brampland", "Seagela"});
        // Brampland
        setAdjacentTerritories("Brampland", new String[]{"Brampland", "Kingdom of Cleolesbia", "Seagela", "Principality of Bunteria", "Principality of Wickwarn"});
        // Principality of Bunteria
        setAdjacentTerritories("Principality of Bunteria", new String[]{"Seagela", "Brampland", "Kingdom of Modh", "Principality of Wickwarn", "Duchy of Axbria"});
        // Kingdom of Modh
        setAdjacentTerritories("Kingdom of Modh", new String[]{"Principality of Bunteria", "Principality of Wickwarn", "Republic of Nird", "Bridford", "Duchy of Axbria"});
        // Principality of Wickwarn
        setAdjacentTerritories("Principality of Wickwarn", new String[]{"Kingdom of Modh", "Principality of Bunteria", "Brampland", "Meuland", "Tihul", "Miama", "Republic of Nird"});
        // Seagela
        setAdjacentTerritories("Seagela", new String[]{"Kingdom of Cleolesbia", "Brampland", "Principality of Bunteria"});

        /* --- Dominion of Watchhaim --- */
        // Grantria
        setAdjacentTerritories("Grantria", new String[]{"Alcesbruian Mountains", "Lonanese Theocracy"});
        // Harland
        setAdjacentTerritories("Harland", new String[]{"Miama", "Selfan Districts", "Alcesbruian Mountains", "Kingdom of Geumguk", "Berja"});
        // Selfan Districts
        setAdjacentTerritories("Selfan Districts", new String[]{"Miama", "Harland", "Alcesbruian Mountains"});
        // Alcesbruian Mountains
        setAdjacentTerritories("Alcesbruian Mountains", new String[]{"Grantria", "Selfan Districts", "Harland", "Lonanese Theocracy", "Kingdom of Geumguk"});
        // Miama
        setAdjacentTerritories("Miama", new String[]{"Selfan Districts", "Harland", "Tutland", "Tihul", "Principality of Wickwarn", "Republic of Nird", "Berja"});
        // Lonanese Theocracy
        setAdjacentTerritories("Lonanese Theocracy", new String[]{"Grantria", "Alcesbruian Mountains", "Kingdom of Geumguk"});

        /* --- Diru --- */
        // Republic of Nird
        setAdjacentTerritories("Republic of Nird", new String[]{"Kingdom of Modh", "Principality of Wickwarn", "Miama", "Berja", "Bridford"});
        // Midbury Empire
        setAdjacentTerritories("Midbury Empire", new String[]{"Duchy of Axbria", "Bridford", "Kingdom of Geumguk"});
        // Kingdom of Geumguk
        setAdjacentTerritories("Kingdom of Geumguk", new String[]{"Midbury Empire", "Bridford", "Berja", "Harland", "Alcesbruian Mountains", "Lonanese Theocracy"});
        // Berja
        setAdjacentTerritories("Berja", new String[]{"Kingdom of Geumguk", "Republic of Nird", "Miama", "Harland", "Bridford"});
        // Duchy of Axbria
        setAdjacentTerritories("Duchy of Axbria", new String[]{"Midbury Empire", "Bridford", "Kingdom of Modh", "Principality of Bunteria"});
        // Bridford
        setAdjacentTerritories("Bridford", new String[]{"Midbury Empire", "Duchy of Axbria", "Kingdom of Modh","Republic of Nird", "Berja", "Kingdom of Geumguk"});
    }
}
