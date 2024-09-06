public class BoardFantasyWorld extends Board{
    private String[][] layout;

    public BoardFantasyWorld() {
        super();

        this.setWorldName("Eteadrodia");
        String[] list = new String[]{"Framish Empire", "Commonwealth of Poruntas", "Dominion of Watchhaim", "Diru"};
        createContinents(list);
        createTerritories();
        setAllAdjacentTerritories();
        this.layout = new String[][]{
                {"", "", "", "GYJ", "N", "N", "N", "", "", "", "", "", "SD", "SD", "SD", "AM", "G", "G", "G", "G"},
                {"", "", "", "GYJ", "N", "N", "N", "TU", "", "", "SD", "SD", "SD", "SD", "SD", "AM", "AM", "G", "G", "G"},
                {"GYJ", "", "GYJ", "GYJ", "GYJ", "N", "N", "TU", "TU", "MI", "MI", "SD", "SD", "H", "H", "AM", "AM", "G", "G", "G"},
                {"GYJ", "GYJ", "GYJ", "GYJ", "GYJ", "TI", "TU", "TU", "TU", "MI", "MI", "MI", "H", "H", "H", "AM", "AM", "AM", "LT", "LT"},
                {"V", "GYJ", "ME", "ME", "NE", "TI", "TI", "TU", "TI", "TI", "MI", "MI", "H", "H", "H", "AM", "AM", "AM", "LT", "LT"},

        };
    }

    // creates and adds territories to the continents
    public void createTerritories() {
        // Continent Framish Empire
        String[] continentOneList = new String[]{"Vad", "Meuland", "Tihul", "Tutland", "Noluch", "Golf of Ylvena-Jiku"};
        for ( String territoryName : continentOneList ){
            Territory newTerritory = new Territory(territoryName, "Framish Empire");
            getContinent("Framish Empire").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Commonwealth of Poruntas
        String[] continentTwoList = new String[]{"Kingdom of Cleolesbia", "Brampland", "Principality of Bunteria", "Kingdom of Modh", "Principality of Wickwarn", "Seagela"};
        for ( String territoryName : continentTwoList ){
            Territory newTerritory = new Territory(territoryName, "Commonwealth of Poruntas");
            getContinent("Commonwealth of Poruntas").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Dominion of Watchhaim
        String[] continentThreeList = new String[]{"Grantria", "Harland", "Selfan Districts", "Alcesbruian Mountains", "Miama", "Lonanese Theocracy" };
        for ( String territoryName : continentThreeList ){
            Territory newTerritory = new Territory(territoryName, "Dominion of Watchhaim");
            getContinent("Dominion of Watchhaim").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Diru
        String[] continentFourList = new String[]{"Republic of Nird", "Midbury Empire", "Kingdom of Geumguk", "Berja", "Duchy of Axbria", "Bridford"};
        for ( String territoryName : continentFourList ){
            Territory newTerritory = new Territory(territoryName, "Diru");
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
