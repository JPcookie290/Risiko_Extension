public class BoardTamriel extends Board{

    public BoardTamriel() {
        super();
        String[] list = new String[]{"Aldmeri Dominion", "Ebonheart Pact", "Daggerfall Covenent"};
        createContinents(list);
        createTerritories();
    }

    public void createTerritories() {
        // Continent Aldmeri Dominion
        String[] continentOneList = new String[]{"Khenathi's Roost", "Grathwood", "Greenshade", "Malabal Tor", "Reaper's March", "West Weals", "Auridon", "Summerset", "Elsweyr"};
        for ( String territoryName : continentOneList ){
            createTerritoriesForContinent(territoryName, "Aldmeri Dominion");
            getContinent("Aldmeri Dominion").addTerritory(getTerritory(territoryName));
        }
        // Continent Ebonheart Pact
        String[] continentTwoList = new String[]{"Shadowfen", "Deshaan", "Stonefalls", "The Rift", "Esstmarch", "Vardenfell", "Telvanni Peninsula"};
        for ( String territoryName : continentTwoList ){
            createTerritoriesForContinent(territoryName, "Ebonheart Pact");
            getContinent("Ebonheart Pact").addTerritory(getTerritory(territoryName));
        }
        // Continent Daggerfall Covenent
        String[] continentThreeList = new String[]{"Glenumbra", "Rivenspire", "Stormhaven", "Wrothgar", "Alk'r Desert", "Bankorai", "High Isle and Galen", "Craglorn"};
        for ( String territoryName : continentThreeList ){
            createTerritoriesForContinent(territoryName, "Daggerfall Covenent");
            getContinent("Daggerfall Covenent").addTerritory(getTerritory(territoryName));
        }

    }
}
