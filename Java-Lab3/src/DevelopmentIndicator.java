import java.util.ArrayList;
import java.util.List;

public class DevelopmentIndicator {
    private String country;
    private List<Integer> publicationsPerYear;

    public DevelopmentIndicator(String country, List<Integer> publicationsPerYear) {
        this.country = country;
        this.publicationsPerYear = publicationsPerYear;
    }

    public String getCountry() {
        return country;
    }

    public List<Integer> getPublicationsPerYear() {
        return publicationsPerYear;
    }

    public int getTotalPublications() {
        return publicationsPerYear.stream().mapToInt(Integer::intValue).sum();
    }
}
