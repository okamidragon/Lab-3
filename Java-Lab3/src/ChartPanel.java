// Maddock Davis
// Lab 3: CharPanel

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<DevelopmentIndicator> data;

    // Creates the panel to display the chart
    public ChartPanel(List<DevelopmentIndicator> data) {
        this.data = data;
        setPreferredSize(new Dimension(800, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }

    // Draws the chart
    private void drawChart(Graphics g) {
        if (data.isEmpty()) return;

        // Dimensions of the graph
        int numYears = data.get(0).getPublicationsPerYear().size();
        int barWidth = 20;
        int spacing = 10;
        int xOffset = 50;
        int yBase = getHeight() - 30;

        // Draw axes
        g.drawLine(40, yBase, 40, 30); // Y-axis
        g.drawLine(40, yBase, getWidth() - 10, yBase); // X-axis

        // Draw x-axis labels
        int x = xOffset;
        for (DevelopmentIndicator indicator : data) {
            g.drawString(indicator.getCountry(), x, yBase + 15);
            x += barWidth + spacing;
        }

        // Draw bars for each year
        int yearOffset = 0;
        for (int yearIndex = 0; yearIndex < numYears; yearIndex++) {
            x = xOffset;
            for (DevelopmentIndicator indicator : data) {
                int publications = indicator.getPublicationsPerYear().get(yearIndex);
                int barHeight = publications / 10;
                g.setColor(Color.BLUE);
                g.fillRect(x, yBase - barHeight, barWidth, barHeight);
                x += barWidth + spacing;
            }
            yearOffset += 30;
        }
    }
}
