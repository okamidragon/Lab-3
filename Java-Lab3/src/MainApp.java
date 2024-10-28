// Maddock Davis
// Lab 3: Main file

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Loads the data from the CSV file
        SwingUtilities.invokeLater(() -> {
            DataLoader dataLoader = new DataLoader();
            List<DevelopmentIndicator> data = dataLoader.loadData("RenewableEnergy_Publications.csv");

            // Check if data is loaded correctly
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No data loaded. Please check the CSV file.");
                return;
            }

            // Creates the GUI
            JFrame frame = new JFrame("Renewable Energy Publications Per Year");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());


            // Creates the details panel
            DetailsPanel detailsPanel = new DetailsPanel();

            // Creates the table and statistics panels
            TablePanel tablePanel = new TablePanel(data, detailsPanel);
            StatsPanel statsPanel = new StatsPanel(data);
            ChartPanel chartPanel = new ChartPanel(data);

            // Formats the whole GUI
            frame.add(statsPanel, BorderLayout.NORTH);
            frame.add(tablePanel, BorderLayout.CENTER);
            frame.add(detailsPanel, BorderLayout.EAST);
            frame.add(chartPanel, BorderLayout.SOUTH);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
