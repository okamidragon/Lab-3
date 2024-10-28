// Maddock Davis
// Lab 3: Details Panel

import javax.swing.*;
import java.awt.*;

// The Details Panel for the GUI
public class DetailsPanel extends JPanel {
    private JLabel countryLabel;
    private JLabel totalPublicationsLabel;
    private JLabel publicationsPerYearLabel;

    // Formats the details panel
    public DetailsPanel() {
        setLayout(new GridLayout(3, 1));
        countryLabel = new JLabel("Country: ");
        totalPublicationsLabel = new JLabel("Total Publications: ");
        publicationsPerYearLabel = new JLabel("Publications per Year: ");

        add(countryLabel);
        add(totalPublicationsLabel);
        add(publicationsPerYearLabel);
    }

    // Updates the panel based on what the user picks from the table panel
    public void updateDetails(DevelopmentIndicator indicator) {
        if (indicator != null) {
            countryLabel.setText("Country: " + indicator.getCountry());
            totalPublicationsLabel.setText("Total Publications: " + indicator.getTotalPublications());
            publicationsPerYearLabel.setText("Publications per Year: " + indicator.getPublicationsPerYear());
        } else {
            countryLabel.setText("Country: ");
            totalPublicationsLabel.setText("Total Publications: ");
            publicationsPerYearLabel.setText("Publications per Year: ");
        }
    }
}
