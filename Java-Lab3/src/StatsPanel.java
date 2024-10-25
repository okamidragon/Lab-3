// Maddock Davis
// Lab 3: Statistics panel

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Creates the statistics table for the GUI
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

        // Create the visuals for the statistics
        mostPublicationsLabel = new JLabel("Country with Most Publications: " + mostPublicationsCountry);
        leastPublicationsLabel = new JLabel("Country with Least Publications: " + leastPublicationsCountry);
        mostInSingleYearLabel = new JLabel("Country with Most Publications in a Single Year: " + mostInSingleYearCountry);

        // Displays the statistics on the GUI
        add(mostPublicationsLabel);
        add(leastPublicationsLabel);
        add(mostInSingleYearLabel);
    }

    // Determines which country has the most total publications
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

    // Determines which country has the least total publications
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

    // Determines which country had the most publicatations in a single year
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
