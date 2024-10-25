// Maddock Davis
// Lab 3: Development indicator

import java.util.ArrayList;
import java.util.List;

// Separates the data from the CSV into individual parts
public class DevelopmentIndicator {
    private String country;
    private List<Integer> publicationsPerYear;
    
    public DevelopmentIndicator(String country, List<Integer> publicationsPerYear) {
        this.country = country;
        this.publicationsPerYear = publicationsPerYear;
    }

    // Gets the country for each entry in the CSV
    public String getCountry() {
        return country;
    }

    // Gets the publications for each country per year
    public List<Integer> getPublicationsPerYear() {
        return publicationsPerYear;
    }

    // Calculates the total amount of publications from each country
    public int getTotalPublications() {
        return publicationsPerYear.stream().mapToInt(Integer::intValue).sum();
    }
}
