public class MapSetting {
    private String[] map;
    private String[] continents;
    private String[] territories;

    public MapSetting() {
        this.map = new String[]{"World", "Zamonien", "Tamriel"};
    }

    public String[] getContinents(String choice){
        if (choice.contains("Zamonien")) {
            return new String[]{"Zentralzamonien", "Westzamonien", "Ostzamonien", "Südzamonien"};
        } else if (choice.contains("Tamriel")) {
            return new String[]{"Summerset Isle", "Hammerfell", "High Rock", "Skyrim", "Morrowind", "Black Marsh", "Elsweyr", "Valenwood", "Cyrodiil"};
        } else {
            return new String[]{"Europe", "Asia", "Africa", "South America", "North America", "Ozeanien"};
        }
    }

}
