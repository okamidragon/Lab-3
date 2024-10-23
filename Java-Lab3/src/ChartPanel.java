import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<DevelopmentIndicator> data;

    public ChartPanel(List<DevelopmentIndicator> data) {
        this.data = data;
        setPreferredSize(new Dimension(800, 400)); // Increased height for better visibility
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }

    private void drawChart(Graphics g) {
        if (data.isEmpty()) return;

        int numYears = data.get(0).getPublicationsPerYear().size();
        int barWidth = 20; // Width of each bar
        int spacing = 10; // Space between bars
        int xOffset = 50; // Starting x position
        int yBase = getHeight() - 30; // Base position for the y-axis

        // Draw axes
        g.drawLine(40, yBase, 40, 30); // Y-axis
        g.drawLine(40, yBase, getWidth() - 10, yBase); // X-axis

        // Draw x-axis labels
        int x = xOffset;
        for (DevelopmentIndicator indicator : data) {
            g.drawString(indicator.getCountry(), x, yBase + 15);
            x += barWidth + spacing; // Move x position for next country
        }

        // Draw bars for each year
        int yearOffset = 0; // To space out years
        for (int yearIndex = 0; yearIndex < numYears; yearIndex++) {
            x = xOffset; // Reset x for each year
            for (DevelopmentIndicator indicator : data) {
                int publications = indicator.getPublicationsPerYear().get(yearIndex);
                int barHeight = publications / 10; // Scale down for visibility
                g.setColor(Color.BLUE);
                g.fillRect(x, yBase - barHeight, barWidth, barHeight);
                x += barWidth + spacing; // Move to the next country
            }
            yearOffset += 30; // Increase vertical offset for each year's bars
        }
    }
}
