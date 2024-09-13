import java.awt.*;
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
        createTerritories();
        String[][] layoutZamonien = new String[][]{
                {"", "KB", "KB", "KB", "", "", "", "", "", "", "", "", "", "", "", "", "BS", "", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "KB", "KB", "KB", "", "", "", "", "", "", "", "", "", "", "", "BS", "BS", "", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "BS", "FG", "FG", "FG", "FG", "FG", "", "", "", "", "", "", "", "NN", ""},
                {"", "", "KI", "KI", "", "", "", "", "", "", "", "GW", "GW", "GW", "GW", "BS", "BS", "FG", "FG", "FG", "FG", "", "", "", "NN", "", "", "", "", ""},
                {"", "", "KI", "", "", "", "", "", "", "", "GW", "GW", "GW", "GW", "RB", "RB", "BS", "BS", "FG", "FG", "", "NN", "", "NN", "NN", "", "NN", "", "", ""},
                {"", "", "", "", "", "", "", "FB", "FB", "FB", "GW", "GW", "GW", "GW", "QT", "RB", "RB", "BS", "BS", "BS", "", "", "NN", "", "", "NN", "", "NN", "", ""},
                {"", "", "", "", "", "", "", "FB", "FB", "FB", "GW", "GW", "GW", "QT", "QT", "QT", "RB", "RB", "BS", "BS", "BS", "", "", "", "", "NN", "", "", "NN", ""},
                {"TI", "TI", "TI", "TI", "", "", "", "FB", "FB", "FB", "GW", "GW", "GW", "GW", "QT", "QT", "QT", "RB", "RB", "BS", "BS", "", "", "NN", "", "", "NN", "", "", "NN"},
                {"", "TI", "TI", "TI", "", "FS", "FS", "FS", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "RB", "RB", "VW", "", "", "", "", "", "", "", "NN", ""},
                {"TI", "TI", "TI", "TI", "FS", "FS", "FS", "FS", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "RB", "VW", "VW", "VW", "VW", "VW", "VW", "VW", "", "", "NN"},
                {"", "TI", "TI", "TI", "", "FS", "FS", "FS", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "RB", "VW", "VW", "VW", "VW", "VW", "VW", "VW", "VW", "VW", ""},
                {"TI", "TI", "TI", "TI", "", "", "", "", "FN", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "DGK", "DGK", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "AT", ""},
                {"", "", "TI", "", "", "", "", "", "FN", "LL", "LL", "LL", "SW", "SW", "SW", "SW", "SW", "SW", "DGK", "DGK", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "AT"},
                {"", "", "", "", "", "", "FN", "FN", "FN", "FN", "UB", "UB", "UB", "UB", "GZ", "GZ", "GZ", "SW", "SW", "MG", "MG", "AT", "AT", "AT", "AT", "AT", "AT", "AT", "", ""},
                {"", "", "", "", "", "", "", "FN", "FN", "FN", "UB", "UB", "UB", "UB", "GZ", "GZ", "", "", "MG", "MG", "MG", "", "", "", "AT", "AT", "AT", "", "", ""},
                {"", "", "", "", "", "", "", "", "FN", "FN", "FN", "UB", "UB", "KK", "KK", "", "", "", "", "MG", "MG", "MG", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", "WP", "WP", "WP", "KK", "KK", "", "", "", "", "", "MG", "MG", "", "", "ON", "", "ON", "", "", ""},
                {"", "", "", "", "", "", "", "", "", "", "", "", "HB", "HB", "HB", "HB", "", "", "", "", "", "MG", "", "ON", "", "", "", "ON", "", ""},
                {"", "", "", "", "", "", "", "", "", "", "", "", "HB", "HB", "HB", "HB", "", "", "", "", "", "", "ON", "", "", "", "ON", "", "", ""},
                {"", "WT", "", "", "", "", "", "", "", "", "", "", "", "HB", "HB", "HB", "", "", "", "", "", "", "", "ON", "", "", "", "", "", ""},
                {"WT", "WT", "WT", "", "", "", "", "", "", "", "", "", "", "HB", "HB", "HB", "", "", "", "", "", "", "", "", "ON", "", "ON", "", "", ""},
                {"WT", "WT", "", "", "", "", "", "", "", "", "", "", "", "", "", "HB", "", "", "", "", "", "", "", "", "", "ON", "", "", "", ""},

        };

        setLayout(layoutZamonien);
    }

    // creates and adds territories to the continents
    public void createTerritories() {
        List<TerritoryInfo> territories = List.of(
                // Continent Westzamonien
                new TerritoryInfo("Klabauter-Insel", "KB", new Color(204, 153, 255), List.of("Kulinarische Insel", "Tatzeninsel", "Friedhofsümpfe", "Finsterberge", "Großer Wald", "Blutschinken"), "Westzamonien"),
                new TerritoryInfo("Kulinarische Insel", "KI", new Color(178, 102, 255), List.of("Klabauter-Insel", "Tatzeninsel", "Friedhofsümpfe", "Finsterberge", "Großer Wald", "Blutschinken"), "Westzamonien"),
                new TerritoryInfo("Tatzeninsel", "TI", new Color(229, 178, 255), List.of("Klabauter-Insel", "Kulinarische Insel", "Friedhofsümpfe", "Finsterberge", "Großer Wald", "Blutschinken", "Wandernde Teufelsfelsen", "Fhenhachigen", "Wolperting", "Hutzengebirge"), "Westzamonien"),
                new TerritoryInfo("Friedhofsümpfe", "FS", new Color(191, 128, 255), List.of("Klabauter-Insel", "Kulinarische Insel", "Tatzeninsel", "Finsterberge", "Großer Wald", "Blutschinken", "Wandernde Teufelsfelsen", "Fhenhachigen", "Wolperting", "Hutzengebirge", "Süße Wüste"), "Westzamonien"),
                new TerritoryInfo("Wandernde Teufelsfelsen", "WT", new Color(217, 153, 255), List.of("Tatzeninsel", "Friedhofsümpfe", "Fhenhachigen", "Wolperting", "Hutzengebirge"), "Westzamonien"),

                // Continent Nordzamonien
                new TerritoryInfo("Finsterberge", "FB", new Color(153, 204, 255), List.of("Kulinarische Insel", "Tatzeninsel", "Friedhofsümpfe", "Klabauter-Insel", "Großer Wald", "Blutschinken", "Süße Wüste"), "Nordzamonien"),
                new TerritoryInfo("Großer Wald", "GW", new Color(102, 178, 255), List.of("Kulinarische Insel", "Tatzeninsel", "Friedhofsümpfe", "Klabauter-Insel", "Finsterberge", "Blutschinken", "Süße Wüste", "Quelltal", "Riesenberge"), "Nordzamonien"),
                new TerritoryInfo("Blutschinken", "BS", new Color(178, 229, 255), List.of("Kulinarische Insel", "Tatzeninsel", "Friedhofsümpfe", "Klabauter-Insel", "Finsterberge", "Großer Wald", "Riesenberge", "Fröstelgrund", "Nordliche Nattifftoffen", "Vielwasser"), "Nordzamonien"),
                new TerritoryInfo("Fröstelgrund", "FG", new Color(191, 222, 255), List.of("Blutschinken", "Nordliche Nattifftoffen"), "Nordzamonien"),
                new TerritoryInfo("Nordliche Nattifftoffen", "NN", new Color(217, 235, 255), List.of("Blutschinken","Fröstelgrund", "Vielwasser"), "Nordzamonien"),

                // Continent Ostzamonien
                new TerritoryInfo("Vielwasser", "VW", new Color(255, 128, 128), List.of("Blutschinken", "Atlantis", "Nordliche Nattifftoffen", "Riesenberge"), "Ostzamonien"),
                new TerritoryInfo("Atlantis", "AT", new Color(255, 102, 102), List.of("Vielwasser", "Midgrad", "Östliche Nattifftoffen", "Der grosse Kopf"), "Ostzamonien"),
                new TerritoryInfo("Midgrad", "MG", new Color(255, 178, 178), List.of("Atlantis", "Süße Wüste", "Östliche Nattifftoffen", "Der grosse Kopf", "Golf von Zamonien", "Kornheim", "Hutzengebirge"), "Ostzamonien"),
                new TerritoryInfo("Östliche Nattifftoffen", "ON", new Color(255, 153, 153), List.of("Atlantis", "Süße Wüste", "Midgrad", "Golf von Zamonien", "Kornheim", "Hutzengebirge"), "Ostzamonien"),

                // Continent Südzamonien
                new TerritoryInfo("Golf von Zamonien", "GZ", new Color(153, 255, 153), List.of("Midgrad", "Süße Wüste", "Östliche Nattifftoffen", "Unbiskant", "Kornheim", "Hutzengebirge"), "Südzamonien"),
                new TerritoryInfo("Kornheim", "KK", new Color(102, 255, 102), List.of("Midgrad", "Wolperting", "Östliche Nattifftoffen", "Unbiskant", "Golf von Zamonien", "Hutzengebirge", "Süße Wüste"), "Südzamonien"),
                new TerritoryInfo("Wolperting", "WP", new Color(178, 255, 178), List.of("Unbiskant", "Hutzengebirge", "Kornheim", "Friedhofsümpfe", "Fhenhachigen", "Tatzeninsel", "Wandernde Teufelsfelsen"), "Südzamonien"),
                new TerritoryInfo("Fhenhachigen", "FN", new Color(128, 255, 128), List.of("Unbiskant", "Hutzengebirge", "Süße Wüste", "Friedhofsümpfe", "Wolperting", "Tatzeninsel", "Wandernde Teufelsfelsen", "Loch Lach"), "Südzamonien"),
                new TerritoryInfo("Hutzengebirge", "HB", new Color(204, 255, 204), List.of("Unbiskant", "Wolperting", "Kornheim", "Friedhofsümpfe", "Fhenhachigen", "Tatzeninsel", "Wandernde Teufelsfelsen", "Golf von Zamonien", "Süße Wüste", "Östliche Nattifftoffen", "Midgrad"), "Südzamonien"),

                // Continent Zentralzamonien
                new TerritoryInfo("Riesenberge", "RB", new Color(227, 160, 59), List.of("Blutschinken", "Süße Wüste", "Quelltal", "Vielwasser", "Der grosse Kopf", "Großer Wald"), "Zentralzamonien"),
                new TerritoryInfo("Quelltal", "QT", new Color(255, 179, 102), List.of("Süße Wüste", "Großer Wald", "Riesenberge"), "Zentralzamonien"),
                new TerritoryInfo("Unbiskant", "UB", new Color(255, 153, 51), List.of("Loch Lach", "Süße Wüste", "Fhenhachigen", "Golf von Zamonien", "Kornheim", "Wolperting"), "Zentralzamonien"),
                new TerritoryInfo("Der grosse Kopf", "DGK", new Color(255, 204, 153), List.of("Midgrad", "Süße Wüste", "Atlantis", "Riesenberge"), "Zentralzamonien"),
                new TerritoryInfo("Loch Lach", "LL", new Color(255, 179, 128), List.of("Fhenhachigen", "Süße Wüste", "Unbiskant"), "Zentralzamonien"),
                new TerritoryInfo("Süße Wüste", "SW", new Color(255, 204, 102), List.of("Loch Lach", "Fhenhachigen", "Finsterberge", "Großer Wald", "Quelltal",  "Riesenberge", "Midgrad", "Östliche Nattifftoffen", "Der grosse Kopf", "Golf von Zamonien", "Kornheim", "Hutzengebirge", "Unbiskant"), "Zentralzamonien")
        );

        addTerritoriesToContinent(territories);
        setAllAdjacentTerritories(territories);
    }

}
