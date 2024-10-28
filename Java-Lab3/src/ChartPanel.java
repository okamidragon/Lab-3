// Maddock Davis
// Lab 3: ChartPanel

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<DevelopmentIndicator> data;
    private JButton sortAscButton;
    private JButton sortDescButton;

    // Creates the panel to display the chart
    public ChartPanel(List<DevelopmentIndicator> data) {
        this.data = data;
        setPreferredSize(new Dimension(800, 400));

        // Create a panel for sorting buttons
        JPanel sortPanel = new JPanel();
        sortAscButton = new JButton("Sort Ascending");
        sortDescButton = new JButton("Sort Descending");

        sortAscButton.addActionListener(new SortActionListener(true));
        sortDescButton.addActionListener(new SortActionListener(false));

        sortPanel.add(sortAscButton);
        sortPanel.add(sortDescButton);

        // Add the sorting panel to the main panel
        setLayout(new BorderLayout());
        add(sortPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }

    // Method to update the chart data
    public void updateData(List<DevelopmentIndicator> newData) {
        this.data = newData;
        repaint();
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

        // Draw Y-axis labels for scaling
        int yScaleInterval = maxPublications / 5;
        for (int i = 0; i <= 5; i++) {
            int yLabelHeight = yBase - (i * (yBase - 30) / 5);
            g.drawString(String.valueOf(i * yScaleInterval), 10, yLabelHeight);
        }
    }

    // Inner class for sorting action listener
    private class SortActionListener implements ActionListener {
        private final boolean ascending;

        public SortActionListener(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(data, new Comparator<DevelopmentIndicator>() {
                @Override
                public int compare(DevelopmentIndicator o1, DevelopmentIndicator o2) {
                    return ascending ?
                            Integer.compare(o1.getTotalPublications(), o2.getTotalPublications()) :
                            Integer.compare(o2.getTotalPublications(), o1.getTotalPublications());
                }
            });

            // Repaint the chart to reflect the new order
            repaint();
        }
    }
}
