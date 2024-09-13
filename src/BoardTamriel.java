import java.awt.*;
import java.util.List;

public class BoardTamriel extends Board{
    private boolean cyrodillAttackable;

    public BoardTamriel() {
        super();
        this.cyrodillAttackable = false;
        this.setWorldName("Tamriel");
        String[] list = new String[]{"Aldmeri Dominion", "Ebonheart Pact", "Daggerfall Covenant", "Cyrodill"};
        createContinents(list);
        createTerritories();
        String[][] layoutTamriel = new String[][]{
                {"", "", "", "", "", "", "", "", "", "WR", "WR", "", "WS", "WS", "", "", "", "", "", "", "", "", "BI", "BI", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "RS", "RS", "", "", "WR", "WR", "WS", "WS", "WS", "WS", "WS", "", "", "", "", "", "", "BI", "", "", "VF", "VF", "", "", "", ""},
                {"", "", "", "", "RS", "RS", "RS", "", "WR", "WR", "WR", "TR", "WS", "WS", "WS", "WS", "", "", "", "", "EM", "EM", "", "", "VF", "VF", "VF", "VF", "", "", ""},
                {"", "", "", "GB", "GB", "ST", "WR", "WR", "WR", "BK", "BK", "TR", "TR", "", "", "", "", "", "", "EM", "EM", "EM", "", "", "VF", "VF", "VF", "VF", "TP", "TP", ""},
                {"", "", "GB", "GB", "GB", "ST", "ST", "ST", "ST", "", "BK", "TR", "TR", "TR", "", "", "", "", "", "EM", "EM", "EM", "EM", "", "VF", "VF", "VF", "VF", "", "TP", ""},
                {"", "GB", "GB", "GB", "", "", "ST", "", "BK", "BK", "BK", "CL", "CL", "CL", "CL", "", "", "", "RF", "RF", "RF", "RF", "", "", "VF", "VF", "VF", "VF", "", "TP", "TP"},
                {"", "GB", "GB", "", "", "", "", "AD", "BK", "BK", "BK", "CL", "CL", "CL", "CL", "CL", "CL", "CL", "RF", "RF", "RF", "RF", "RF", "", "", "VF", "VF", "", "", "TP", "TP"},
                {"", "GB", "", "", "AD", "AD", "AD", "AD", "BK", "BK", "BK", "CL", "CL", "CL", "CL", "CL", "CL", "CY", "CY", "CY", "CY", "RF", "SF", "SF", "", "", "", "", "TP", "TP", "TP"},
                {"", "", "BT", "", "AD", "AD", "AD", "AD", "AD", "AD", "BK", "CL", "CL", "CL", "CL", "CY", "CY", "CY", "CY", "CY", "CY", "CY", "SF", "SF", "", "SF", "BF", "", "TP", "TP", ""},
                {"GY", "", "BT", "BT", "", "", "", "", "AD", "AD", "AD", "AD", "AD", "CL", "WW", "CY", "CY", "CY", "CY", "CY", "CY", "SF", "SF", "SF", "SF", "SF", "BF", "BF", "TP", "", ""},
                {"GY", "GY", "", "", "", "", "", "", "", "AD", "AD", "AD", "AD", "WW", "WW", "CY", "CY", "CY", "CY", "CY", "CY", "DS", "DS", "DS", "DS", "DS", "DS", "DS", "TP", "", ""},
                {"GY", "", "", "", "", "", "", "SM", "", "", "", "WW", "WW", "WW", "WW", "CY", "CY", "CY", "CY", "CY", "CY", "DS", "DS", "DS", "DS", "DS", "DS", "DS", "", "", ""},
                {"", "HI", "HI", "", "", "SM", "", "SM", "", "", "", "", "", "WW", "WW", "WW", "CY", "CY", "CY", "CY", "DS", "DS", "DS", "SH", "SH", "SH", "SH", "", "", "", ""},
                {"", "HI", "HI", "", "", "", "", "", "", "", "", "", "", "MT", "RM", "RM", "RM", "CY", "NE", "NE", "DS", "SH", "SH", "SH", "SH", "SH", "SH", "", "", "", ""},
                {"", "", "", "", "", "AU", "AU", "", "", "", "MT", "MT", "MT", "MT", "RM", "RM", "RM", "NE", "NE", "NE", "", "", "SH", "SH", "SH", "SH", "SH", "", "", "", ""},
                {"", "", "", "SS", "", "AU", "AU", "AU", "", "", "MT", "MT", "MT", "MT", "RM", "RM", "NE", "NE", "NE", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "SS", "SS", "SS", "", "", "AU", "", "", "GS", "GS", "MT", "MT", "RM", "NE", "NE", "NE", "NE", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "SS", "SS", "SS", "SS", "", "AU", "AU", "", "GS", "GS", "GS", "GW", "GW", "GW", "NE", "SE", "SE", "SE", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"SS", "", "", "SS", "SS", "", "", "AU", "AU", "", "GS", "GS", "GW", "GW", "GW", "GW", "SE", "SE", "", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "SS", "SS", "SS", "", "", "", "", "", "", "", "", "GW", "GW", "GW", "SE", "SE", "SE", "", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "SS", "SS", "SS", "SS", "SS", "", "", "", "", "", "", "", "", "GW", "", "", "", "SE", "SE", "", "", "", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "SS", "SS", "", "", "", "", "", "", "", "", "", "KR", "KR", "KR", "", "", "", "", "", "", "", "", "", "", "", "", ""}
        };

        setLayout(layoutTamriel);
    }

    // creates and adds territories to the continents
    public void createTerritories() {
        List<TerritoryInfo> territories = List.of(
                // Continent Daggerfall Covenant
                new TerritoryInfo("Rivenspire", "RS", new Color(179, 128, 255), List.of("Wrothgar", "Western Skyrim", "Stormhaven", "Glenumbra", "Alik'r Desert", "Betnikh", "Galen and Y'ffelon"), "Daggerfall Covenant"),
                new TerritoryInfo("Wrothgar", "WR", new Color(179, 128, 255), List.of("Rivenspire", "Western Skyrim", "Stormhaven", "Glenumbra", "Alik'r Desert", "Betnikh", "Galen and Y'ffelon", "Bangkorai", "The Reach"), "Daggerfall Covenant"),
                new TerritoryInfo("Glenumbra", "GB", new Color(179, 128, 255), List.of("Rivenspire", "Stormhaven", "Wrothgar", "Alik'r Desert", "Betnikh", "Galen and Y'ffelon"), "Daggerfall Covenant"),
                new TerritoryInfo("Stormhaven", "ST", new Color(179, 128, 255), List.of("Rivenspire", "Glenumbra", "Wrothgar", "Alik'r Desert", "Betnikh", "Bangkorai"), "Daggerfall Covenant"),
                new TerritoryInfo("Western Skyrim", "WS", new Color(179, 128, 255), List.of("The Reach", "Craglorn", "Wrothgar", "The Rift", "Eastmarch", "Bleakrock Isle"), "Daggerfall Covenant"),
                new TerritoryInfo("The Reach", "TR", new Color(179, 128, 255), List.of("Western Skyrim", "Craglorn", "Wrothgar", "The Rift", "Eastmarch", "Bleakrock Isle", "Bangkorai"), "Daggerfall Covenant"),
                new TerritoryInfo("Craglorn", "CL", new Color(179, 128, 255), List.of("Western Skyrim", "Craglorn", "Wrothgar", "The Rift", "Eastmarch", "Bleakrock Isle", "Bangkorai", "Alik'r Desert", "West Weald", "Cyrodill"), "Daggerfall Covenant"),
                new TerritoryInfo("Alik'r Desert", "AD", new Color(179, 128, 255), List.of("Rivenspire", "Stormhaven", "Galen and Y'ffelon", "Glenumbra", "Betnikh", "Craglorn", "Wrothgar", "Bangkorai", "West Weald", "High Isle", "Stros M'Kai", "Summerset", "Auridon", "Malabal Tor", "Greenshade"), "Daggerfall Covenant"),
                new TerritoryInfo("Betnikh", "BT", new Color(179, 128, 255), List.of("Rivenspire", "Stormhaven", "Galen and Y'ffelon", "Glenumbra", "Alik'r Desert", "Craglorn", "Wrothgar", "Bangkorai", "West Weald", "High Isle", "Stros M'Kai", "Summerset", "Auridon", "Malabal Tor", "Greenshade"), "Daggerfall Covenant"),
                new TerritoryInfo("Galen and Y'ffelon", "GY", new Color(179, 128, 255), List.of("Rivenspire", "Betnikh", "Glenumbra", "Alik'r Desert", "Craglorn", "Wrothgar", "Bangkorai", "West Weald", "High Isle", "Stros M'Kai", "Summerset", "Auridon", "Malabal Tor", "Greenshade"), "Daggerfall Covenant"),
                new TerritoryInfo("High Isle", "HI", new Color(179, 128, 255), List.of("Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "West Weald", "Stros M'Kai", "Summerset", "Auridon", "Malabal Tor", "Greenshade"), "Daggerfall Covenant"),
                new TerritoryInfo("Stros M'Kai", "SM", new Color(179, 128, 255), List.of("Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "West Weald", "High Isle", "Summerset", "Auridon", "Malabal Tor", "Greenshade"), "Daggerfall Covenant"),
                new TerritoryInfo("Bangkorai", "BK", new Color(151, 120, 202), List.of("Alik'r Desert", "Stormhaven", "Wrothgar", "The Reach", "Craglorn"), "Daggerfall Covenant"),

                // Continent Ebonheart Pact
                new TerritoryInfo("Bleakrock Isle", "BI", new Color(255, 102, 102), List.of("The Reach", "Western Skyrim", "Craglorn", "Stonefalls", "The Rift", "Eastmarch", "Vardenfell", "Telvanni Peninsula", "Bal Foyen"), "Ebonheart Pact"),
                new TerritoryInfo("The Rift", "RF", new Color(255, 102, 102), List.of("The Reach", "Western Skyrim", "Craglorn", "Stonefalls", "Bleakrock Isle", "Eastmarch", "Vardenfell", "Telvanni Peninsula", "Bal Foyen", "Cyrodill"), "Ebonheart Pact"),
                new TerritoryInfo("Eastmarch", "EM", new Color(255, 102, 102), List.of("The Reach", "Western Skyrim", "Craglorn", "Stonefalls", "The Rift", "Bleakrock Isle", "Vardenfell", "Telvanni Peninsula", "Bal Foyen"), "Ebonheart Pact"),
                new TerritoryInfo("Stonefalls", "SF", new Color(255, 102, 102), List.of("Eastmarch", "The Rift", "Bleakrock Isle", "Vardenfell", "Telvanni Peninsula", "Bal Foyen", "Cyrodill", "Deshaan"), "Ebonheart Pact"),
                new TerritoryInfo("Bal Foyen", "BF", new Color(255, 102, 102), List.of("Eastmarch", "The Rift", "Bleakrock Isle", "Vardenfell", "Telvanni Peninsula", "Deshaan"), "Ebonheart Pact"),
                new TerritoryInfo("Vardenfell", "VF", new Color(255, 102, 102), List.of("Eastmarch", "The Rift", "Bleakrock Isle", "Bal Foyen", "Telvanni Peninsula", "Bal Foyen"), "Ebonheart Pact"),
                new TerritoryInfo("Telvanni Peninsula", "TP", new Color(255, 102, 102), List.of("Eastmarch", "The Rift", "Bleakrock Isle", "Vardenfell", "Shadowfen", "Bal Foyen", "Deshaan", "Northern Elsweyr", "Southern Elsweyr", "Khenarthi's Roost"), "Ebonheart Pact"),
                new TerritoryInfo("Deshaan", "DS", new Color(255, 102, 102), List.of("Shadowfen", "Bal Foyen", "Telvanni Peninsula", "Northern Elsweyr", "Southern Elsweyr", "Khenarthi's Roost"), "Ebonheart Pact"),
                new TerritoryInfo("Shadowfen", "SH", new Color(255, 102, 102), List.of("Deshaan", "Bal Foyen", "Telvanni Peninsula", "Northern Elsweyr", "Southern Elsweyr", "Khenarthi's Roost"), "Ebonheart Pact"),

                // Continent Aldmeri Dominion
                new TerritoryInfo("Northern Elsweyr", "NE", new Color(255, 255, 128), List.of("Deshaan", "Bal Foyen", "Telvanni Peninsula", "Shadowfen", "Southern Elsweyr", "Khenarthi's Roost", "Cyrodill", "Grahtwood", "Reaper's March"), "Aldmeri Dominion"),
                new TerritoryInfo("Southern Elsweyr", "SE", new Color(255, 255, 128), List.of("Deshaan", "Bal Foyen", "Telvanni Peninsula", "Northern Elsweyr", "Shadowfen", "Khenarthi's Roost", "Cyrodill", "Grahtwood"), "Aldmeri Dominion"),
                new TerritoryInfo("Khenarthi's Roost", "KR", new Color(255, 255, 128), List.of("Deshaan", "Bal Foyen", "Telvanni Peninsula", "Northern Elsweyr", "Shadowfen", "Southern Elsweyr", "Greenshade", "Grahtwood", "Auridon", "Summerset"), "Aldmeri Dominion"),
                new TerritoryInfo("Grahtwood", "GW", new Color(255, 255, 128), List.of("Northern Elsweyr", "Khenarthi's Roost", "Southern Elsweyr", "Greenshade", "Reaper's March", "Auridon", "Summerset", "Malabal Tor"), "Aldmeri Dominion"),
                new TerritoryInfo("Greenshade", "GS", new Color(255, 255, 128), List.of("Khenarthi's Roost", "Grahtwood", "Auridon", "Summerset", "Malabal Tor", "West Weald", "Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "High Isle", "Stros M'Kai"), "Aldmeri Dominion"),
                new TerritoryInfo("Auridon", "AU", new Color(255, 255, 128), List.of("Khenarthi's Roost", "Grahtwood", "Greenshade", "Summerset", "Malabal Tor", "West Weald", "Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "High Isle", "Stros M'Kai"), "Aldmeri Dominion"),
                new TerritoryInfo("Malabal Tor", "MT", new Color(255, 255, 128), List.of("Grahtwood", "Auridon", "Greenshade", "Summerset", "West Weald", "Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "High Isle", "Stros M'Kai", "Reaper's March"), "Aldmeri Dominion"),
                new TerritoryInfo("West Weald", "WW", new Color(255, 255, 128), List.of("Grahtwood", "Auridon", "Greenshade", "Summerset", "Malabal Tor", "Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "High Isle", "Stros M'Kai", "Reaper's March", "Cyrodill", "Craglorn"), "Aldmeri Dominion"),
                new TerritoryInfo("Reaper's March", "RM", new Color(255, 255, 128), List.of("Grahtwood", "Northern Elsweyr", "Malabal Tor", "West Weald", "Cyrodill"), "Aldmeri Dominion"),
                new TerritoryInfo("Summerset", "SS", new Color(237, 237, 136), List.of("Khenarthi's Roost", "Grahtwood", "Greenshade", "Auridon", "Malabal Tor", "West Weald", "Betnikh", "Alik'r Desert", "Galen and Y'ffelon", "High Isle", "Stros M'Kai"), "Aldmeri Dominion"),

                // Continent Cyrodill
                new TerritoryInfo("Cyrodill", "CY", new Color(120, 152, 120), List.of("Craglorn", "The Rift", "Stonefalls", "Deshaan", "Reaper's March", "West Weald", "Northern Elsweyr"), "Cyrodill")
        );

        addTerritoriesToContinent(territories);
        setAllAdjacentTerritories(territories);
    }


}
