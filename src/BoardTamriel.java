public class BoardTamriel extends Board{

    public BoardTamriel() {
        super();
        this.setWorldName("Tamriel");
        String[] list = new String[]{"Aldmeri Dominion", "Ebonheart Pact", "Daggerfall Covenent"};
        createContinents(list);
        //createTerritories();
    }

    // creates and adds territories to the continents
    /*public void createTerritories() {
        // Continent Aldmeri Dominion
        String[] continentOneList = new String[]{"Khenathi's Roost", "Grathwood", "Greenshade", "Malabal Tor", "Reaper's March", "West Weals", "Auridon", "Summerset", "Northen Elsweyr", "Southern Elsweyr"};
  		String[] continentOneAbbr = new String[]{"KR", "GRA", "GRE", "MT", "RM", "WW", "A", "SI", "NE", "SE"};
		Color[] continentOneColors = new Color[]{};      
		for ( String territoryName : continentOneList ){
            Territory newTerritory = new Territory(territoryName, "Aldmeri Dominion");
            getContinent("Aldmeri Dominion").addTerritory(newTerritory);
        }
        // Continent Ebonheart Pact
        String[] continentTwoList = new String[]{"Shadowfen", "Deshaan", "Stonefalls", "The Rift", "Eastmarch", "Vardenfell", "Telvanni Peninsula", "Bal Foyen", "Bleakrock Isle"};
  		String[] continentTwoAbbr = new String[]{"SH", "D", "SF", "TR", "E", "V", "TP", "BF", "BI"};      
		Color[] continentTwoColors = new Color[]{};
		for ( String territoryName : continentTwoList ){
            Territory newTerritory = new Territory(territoryName, "Ebonheart Pact");
            getContinent("Ebonheart Pact").addTerritory(newTerritory);
        }
        // Continent Daggerfall Covenent
        String[] continentThreeList = new String[]{"Glenumbra", "Rivenspire", "Stormhaven", "Wrothgar", "Alk'r Desert", "Bankorai", "High Isle", "Galen", "Craglorn", "Betnikh", "The Reach", "Western Skyrim"};
  		String[] continentThreeAbbr = new String[]{"GL", "R", "SH", "W", "AD", "HI", "GA", "C", "B", "RA", "WS"};      
		Color[] continentThreeColors = new Color[]{};
		for ( String territoryName : continentThreeList ){
            Territory newTerritory = new Territory(territoryName, "Daggerfall Covenent");
            getContinent("Daggerfall Covenent").addTerritory(newTerritory);
        }

    }*/
}
