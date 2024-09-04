public class BoardTamriel extends Board{

    public BoardTamriel() {
        super();
        this.setWorldName("Tamriel");
        String[] list = new String[]{"Aldmeri Dominion", "Ebonheart Pact", "Daggerfall Covenent"};
        createContinents(list);
        createTerritories();
    }

    // creates and adds territories to the continents
    public void createTerritories() {
        // Continent Aldmeri Dominion
        String[] continentOneList = new String[]{"Khenathi's Roost", "Grathwood", "Greenshade", "Malabal Tor", "Reaper's March", "West Weals", "Auridon", "Summerset", "Elsweyr"};
        for ( String territoryName : continentOneList ){
            Territory newTerritory = new Territory(territoryName, "Aldmeri Dominion");
            getContinent("Aldmeri Dominion").addTerritory(newTerritory);
        }
        // Continent Ebonheart Pact
        String[] continentTwoList = new String[]{"Shadowfen", "Deshaan", "Stonefalls", "The Rift", "Esstmarch", "Vardenfell", "Telvanni Peninsula"};
        for ( String territoryName : continentTwoList ){
            Territory newTerritory = new Territory(territoryName, "Ebonheart Pact");
            getContinent("Ebonheart Pact").addTerritory(newTerritory);
        }
        // Continent Daggerfall Covenent
        String[] continentThreeList = new String[]{"Glenumbra", "Rivenspire", "Stormhaven", "Wrothgar", "Alk'r Desert", "Bankorai", "High Isle and Galen", "Craglorn"};
        for ( String territoryName : continentThreeList ){
            Territory newTerritory = new Territory(territoryName, "Daggerfall Covenent");
            getContinent("Daggerfall Covenent").addTerritory(newTerritory);
        }

    }
}
