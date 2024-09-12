import java.awt.*;
import java.util.List;

public class TerritoryInfo {
    private String name;
    private String abbreviation;
    private Color color;
    private List<String> adjacentTerritories;
    private String continent;

    public TerritoryInfo(String name, String abbreviation, Color color, List<String> adjacentTerritories, String continent) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.color = color;
        this.adjacentTerritories = adjacentTerritories;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Color getTerritoryColor() {
        return color;
    }

    public List<String> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    public String getContinent() {
        return continent;
    }
}
