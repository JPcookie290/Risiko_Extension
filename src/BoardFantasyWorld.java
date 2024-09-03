public class BoardFantasyWorld extends Board{

    public BoardFantasyWorld() {
        super();
        String[] list = new String[]{"Framish Empire", "Commenwealth of Poruntas", "America", "Asien", "Ozeanien"};
        createContinents(list);
        createTerritories();
    }

    //TODO create third world map
    public void createTerritories() {
        // Continent Europa
        String[] continentOneList = new String[]{"Suesse Wueste", "Loch Lach", "Anagrom Ataf", "Der grosse Kopf"};
        for ( String territoryName : continentOneList ){
            createTerritoriesForContinent(territoryName, "Europa");
            getContinent("Europa").addTerritory(getTerritory(territoryName));
        }
        // Continent Afrika
        String[] continentTwoList = new String[]{"Klabauter-Insel", "Kulinarische Insel", "Tatzeninsel", "Friedhofsuempfe", "Irrlichterbucht", "Wandernde Teufelsfelsen"};
        for ( String territoryName : continentTwoList ){
            createTerritoriesForContinent(territoryName, "Afrika");
            getContinent("Afrika").addTerritory(getTerritory(territoryName));
        }
        // Continent Amerika
        String[] continentThreeList = new String[]{"Blutschinken", "Vielwasser", "Saeulen der Herkules", "Atlantis", "Oestliche Nattifftoffen"};
        for ( String territoryName : continentThreeList ){
            createTerritoriesForContinent(territoryName, "Amerika");
            getContinent("Amerika").addTerritory(getTerritory(territoryName));
        }
        // Continent Asien
        String[] continentFourList = new String[]{"Wolperting", "Unbiskant", "Kornheim", "Hutzengebirge", "Midgrad"};
        for ( String territoryName : continentFourList ){
            createTerritoriesForContinent(territoryName, "Asien");
            getContinent("Asien").addTerritory(getTerritory(territoryName));
        }
        // Continent Ozeanien
        String[] continentFiveList = new String[]{"Froestelgrund", "Nordliche Nattifftoffen", "Grosser Wald", "Finsterberge"};
        for ( String territoryName : continentFiveList ){
            createTerritoriesForContinent(territoryName, "Ozeanien");
            getContinent("Ozeanien").addTerritory(getTerritory(territoryName));
        }
    }
}
