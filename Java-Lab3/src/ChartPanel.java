// Maddock Davis
// ChartPanel

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
        int barWidth = 10;
        int spacing = 10;
        int xOffset = 50;
        int yBase = getHeight() - 30;

        // Draw axes
        g.drawLine(40, yBase, 40, 30);
        g.drawLine(40, yBase, getWidth() - 10, yBase);

        // Draw x-axis labels
        int x = xOffset;
        for (DevelopmentIndicator indicator : data) {
            g.drawString(indicator.getCountry(), x, yBase + 15);
            x += barWidth + spacing;
        }

        // Calculate max publications for scaling
        int maxPublications = data.stream()
                .mapToInt(DevelopmentIndicator::getTotalPublications)
                .max()
                .orElse(1);

        // Draw bars for total publications
        x = xOffset;
        for (DevelopmentIndicator indicator : data) {
            int totalPublications = indicator.getTotalPublications();
            int barHeight = (int) ((totalPublications / (double) maxPublications) * (yBase - 30));
            g.setColor(Color.BLUE);
            g.fillRect(x, yBase - barHeight, barWidth, barHeight);
            x += barWidth + spacing;
        }

        // Optional: Draw Y-axis labels for scaling
        int yScaleInterval = maxPublications / 5;
        for (int i = 0; i <= 5; i++) {
            int yLabelHeight = yBase - (i * (yBase - 30) / 5);
            g.drawString(String.valueOf(i * yScaleInterval), 10, yLabelHeight);
        }
    }
}
