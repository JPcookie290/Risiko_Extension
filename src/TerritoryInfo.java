import java.awt.*;
import java.util.List;

public class TerritoryInfo {
    private String name;
    private String abbreviation;
    private Color color;
    private List<String> adjacentTerritories;

    public TerritoryInfo(String name, String abbreviation, Color color, List<String> adjacentTerritories) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.color = color;
        this.adjacentTerritories = adjacentTerritories;
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
}
