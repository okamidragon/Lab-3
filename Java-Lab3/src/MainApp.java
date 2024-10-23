import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataLoader dataLoader = new DataLoader();
            List<DevelopmentIndicator> data = dataLoader.loadData("RenewableEnergy_Publications.csv");

            // Check if data is loaded correctly
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No data loaded. Please check the CSV file.");
                return;
            }

            JFrame frame = new JFrame("Data Visualization Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            TablePanel tablePanel = new TablePanel(data);
            StatsPanel statsPanel = new StatsPanel(data);
            ChartPanel chartPanel = new ChartPanel(data);

            frame.add(statsPanel, BorderLayout.NORTH);
            frame.add(tablePanel, BorderLayout.CENTER);
            frame.add(chartPanel, BorderLayout.SOUTH);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
