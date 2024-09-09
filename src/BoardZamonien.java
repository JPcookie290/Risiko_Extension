import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardZamonien extends Board {

    public BoardZamonien() {
        super();
        this.setWorldName("Zamonien");
        String[] list = new String[]{"Zentralzamonien", "Westzamonien", "Ostzamonien", "Südzamonien", "Nordzamonien"};
        createContinents(list);
        //createTerritories();
        //setAllAdjacentTerritories();
    }

    // creates and adds territories to the continents
    /*
    public void createTerritories() {

        // Continent Zentralzamonien
        String[] continentOneList = new String[]{"Suesse Wueste", "Loch Lach", "Daemonenklamm", "Der grosse Kopf"};
        for ( String territoryName : continentOneList ){
            Territory newTerritory = new Territory(territoryName, "Zentralzamonien");
            getContinent("Zentralzamonien").addTerritory(newTerritory);
            addTerritory( newTerritory);
        }
        // Continent Westzamonien
        String[] continentTwoList = new String[]{"Klabauter-Insel", "Kulinarische Insel", "Tatzeninsel", "Friedhofsuempfe", "Irrlichterbucht", "Wandernde Teufelsfelsen"};
        for ( String territoryName : continentTwoList ){
            Territory newTerritory = new Territory(territoryName, "Westzamonien");
            getContinent("Westzamonien").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Ostzamonien
        String[] continentThreeList = new String[]{"Blutschinken", "Vielwasser", "Saeulen der Herkules", "Atlantis", "Oestliche Nattifftoffen"};
        for ( String territoryName : continentThreeList ){
            Territory newTerritory = new Territory(territoryName, "Ostzamonien");
            getContinent("Ostzamonien").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Südzamonien
        String[] continentFourList = new String[]{"Wolperting", "Unbiskant", "Kornheim", "Hutzengebirge", "Midgrad"};
        for ( String territoryName : continentFourList ){
            Territory newTerritory = new Territory(territoryName, "Südzamonien");
            getContinent("Südzamonien").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
        // Continent Nordzamonien
        String[] continentFiveList = new String[]{"Froestelgrund", "Nordliche Nattifftoffen", "Grosser Wald", "Finsterberge"};
        for ( String territoryName : continentFiveList ){
            Territory newTerritory = new Territory(territoryName, "Nordzamonien");
            getContinent("Nordzamonien").addTerritory(newTerritory);
            addTerritory(newTerritory);
        }
    }

     */

    // Function to add the adjacent territories
    private void setAllAdjacentTerritories(){
        /* --- Continent Zentralzamonien --- */
        // Suesse Wueste
        // setAdjacentTerritories("Suesse Wueste", new String[]{"Loch Lach", "Daemonenklamm", "Der Große Kopf", "Finsterberge", "Grosser Wald", "Friedhofsuempfe", "Irrlichterbucht", "Vielwasser"});
        // Loch Lach
        // setAdjacentTerritories("Suesse Wueste", new String[]{});
        // Daemonenklamm
        // setAdjacentTerritories("Suesse Wueste", new String[]{});
        // Der grosse Kopf
        // setAdjacentTerritories("Suesse Wueste", new String[]{});
        /* --- Continent Westzamonien --- */
        // Klabauter-Insel
        // Kulinarische Insel
        // Tatzeninsel
        // Friedhofsuempfe
        // Irrlichterbucht
        // Wandernde Teufelsfelsen

        /* --- Continent Ostzamonien --- */
        // Blutschinken
        // Vielwasser
        // Saeulen der Herkules
        // Atlantis
        // Oestliche Nattifftoffen

        /* --- Continent Südzamonien --- */
        // Wolperting
        // Unbiskant
        // Kornheim
        // Hutzengebirge
        // Midgrad

        /* --- Continent Nordzamonien --- */
        // Froestelgrund
        // Nordliche Nattifftoffen
        // Grosser Wald
        // Finsterberge

    }

}
