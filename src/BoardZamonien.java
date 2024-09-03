import java.util.List;

public class BoardZamonien extends Board {

    public BoardZamonien() {
        super();
        String[] list = new String[]{"Zentralzamonien", "Westzamonien", "Ostzamonien", "Südzamonien", "Nordzamonien"};
        createContinents(list);
        createTerritories();
    }

    public void createTerritories() {
        // Continent Zentralzamonien
        String[] continentOneList = new String[]{"Suesse Wueste", "Loch Lach", "Anagrom Ataf", "Der grosse Kopf"};
        for ( String territoryName : continentOneList ){
            createTerritoriesForContinent(territoryName, "Zentralzamonien");
            getContinent("Zentralzamonien").addTerritory(getTerritory(territoryName));
        }
        // Continent Westzamonien
        String[] continentTwoList = new String[]{"Klabauter-Insel", "Kulinarische Insel", "Tatzeninsel", "Friedhofsuempfe", "Irrlichterbucht", "Wandernde Teufelsfelsen"};
        for ( String territoryName : continentTwoList ){
            createTerritoriesForContinent(territoryName, "Westzamonien");
            getContinent("Westzamonien").addTerritory(getTerritory(territoryName));
        }
        // Continent Ostzamonien
        String[] continentThreeList = new String[]{"Blutschinken", "Vielwasser", "Saeulen der Herkules", "Atlantis", "Oestliche Nattifftoffen"};
        for ( String territoryName : continentThreeList ){
            createTerritoriesForContinent(territoryName, "Ostzamonien");
            getContinent("Ostzamonien").addTerritory(getTerritory(territoryName));
        }
        // Continent Südzamonien
        String[] continentFourList = new String[]{"Wolperting", "Unbiskant", "Kornheim", "Hutzengebirge", "Midgrad"};
        for ( String territoryName : continentFourList ){
            createTerritoriesForContinent(territoryName, "Südzamonien");
            getContinent("Südzamonien").addTerritory(getTerritory(territoryName));
        }
        // Continent Nordzamonien
        String[] continentFiveList = new String[]{"Froestelgrund", "Nordliche Nattifftoffen", "Grosser Wald", "Finsterberge"};
        for ( String territoryName : continentFiveList ){
            createTerritoriesForContinent(territoryName, "Nordzamonien");
            getContinent("Nordzamonien").addTerritory(getTerritory(territoryName));
        }
    }

}
