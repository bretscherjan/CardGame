package ch.bbw.cardgame;

/**
 * City
 * Fachklasse für ein Auto
 * @author Peter Rutschmann
 * @date 26.08.2021
 */
public class City {
    private String imageUrl;
    private String tradeName;
    private double inhabitant;
    private double area;
    private int highestBuilding;
    private int populationDensity;

    // Konstruktor
    public City(String imageUrl, String tradeName, double inhabitant, double area, int highestBuilding, int populationDensity) {
        this.imageUrl = imageUrl;
        this.tradeName = tradeName;
        this.inhabitant = inhabitant;
        this.area = area;
        this.highestBuilding = highestBuilding;
        this.populationDensity = populationDensity;
    }

    // Getter und Setter Methoden
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public double getInhabitant() {
        return inhabitant;
    }

    public void setInhabitant(int inhabitant) {
        this.inhabitant = inhabitant;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getHighestBuilding() {
        return highestBuilding;
    }

    public void setHighestBuilding(int highestBuilding) {
        this.highestBuilding = highestBuilding;
    }

    public int getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(int populationDensity) {
        this.populationDensity = populationDensity;
    }
}
