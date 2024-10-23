import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel mostPublicationsLabel;
    private JLabel leastPublicationsLabel;
    private JLabel mostInSingleYearLabel;

    public StatsPanel(List<DevelopmentIndicator> data) {
        setLayout(new GridLayout(3, 1));

        // Calculate statistics
        String mostPublicationsCountry = getCountryWithMostPublications(data);
        String leastPublicationsCountry = getCountryWithLeastPublications(data);
        String mostInSingleYearCountry = getCountryWithMostInSingleYear(data);

        mostPublicationsLabel = new JLabel("Country with Most Publications: " + mostPublicationsCountry);
        leastPublicationsLabel = new JLabel("Country with Least Publications: " + leastPublicationsCountry);
        mostInSingleYearLabel = new JLabel("Country with Most Publications in a Single Year: " + mostInSingleYearCountry);

        add(mostPublicationsLabel);
        add(leastPublicationsLabel);
        add(mostInSingleYearLabel);
    }

    private String getCountryWithMostPublications(List<DevelopmentIndicator> data) {
        String country = "";
        int maxPublications = 0;

        for (DevelopmentIndicator indicator : data) {
            int totalPublications = indicator.getTotalPublications();
            if (totalPublications > maxPublications) {
                maxPublications = totalPublications;
                country = indicator.getCountry();
            }
        }
        return country + " (" + maxPublications + ")";
    }

    private String getCountryWithLeastPublications(List<DevelopmentIndicator> data) {
        String country = "";
        int minPublications = Integer.MAX_VALUE;

        for (DevelopmentIndicator indicator : data) {
            int totalPublications = indicator.getTotalPublications();
            if (totalPublications < minPublications) {
                minPublications = totalPublications;
                country = indicator.getCountry();
            }
        }
        return country + " (" + (minPublications == Integer.MAX_VALUE ? 0 : minPublications) + ")";
    }

    private String getCountryWithMostInSingleYear(List<DevelopmentIndicator> data) {
        String country = "";
        int maxPublicationsInYear = 0;

        for (DevelopmentIndicator indicator : data) {
            for (int publications : indicator.getPublicationsPerYear()) {
                if (publications > maxPublicationsInYear) {
                    maxPublicationsInYear = publications;
                    country = indicator.getCountry();
                }
            }
        }
        return country + " (" + maxPublicationsInYear + ")";
    }
}
