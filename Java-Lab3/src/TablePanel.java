import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> columnSelector;
    private JButton sortAscButton;
    private JButton sortDescButton;

    public TablePanel(List<DevelopmentIndicator> data) {
        setLayout(new BorderLayout());

        // Define the number of years for columns dynamically based on the data
        int numYears = data.isEmpty() ? 0 : data.get(0).getPublicationsPerYear().size();
        String[] columnNames = new String[numYears + 1]; // +1 for the country name
        columnNames[0] = "Country";

        // Initialize column names for years
        for (int i = 1; i <= numYears; i++) {
            columnNames[i] = "" + (i + 1989); // Adjust according to your year logic
        }

        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Populate table with data
        for (DevelopmentIndicator indicator : data) {
            Object[] row = new Object[numYears + 1];
            row[0] = indicator.getCountry(); // Country name
            List<Integer> publications = indicator.getPublicationsPerYear();
            for (int i = 0; i < publications.size(); i++) {
                row[i + 1] = publications.get(i); // Publications for each year
            }
            tableModel.addRow(row);
        }

        // Create sorting panel
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new FlowLayout());

        // Create a column selector for sorting
        columnSelector = new JComboBox<>(columnNames);
        sortPanel.add(columnSelector);

        // Create sorting buttons
        sortAscButton = new JButton("Sort Ascending");
        sortDescButton = new JButton("Sort Descending");

        sortAscButton.addActionListener(new SortActionListener(true));
        sortDescButton.addActionListener(new SortActionListener(false));

        sortPanel.add(sortAscButton);
        sortPanel.add(sortDescButton);

        // Add sort panel and table to the main panel
        add(sortPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class SortActionListener implements ActionListener {
        private final boolean ascending;

        public SortActionListener(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int columnIndex = columnSelector.getSelectedIndex();
            Vector<Vector> dataVector = tableModel.getDataVector();

            dataVector.sort((o1, o2) -> {
                if (columnIndex == 0) { // Sort by Country
                    String country1 = (String) o1.get(0);
                    String country2 = (String) o2.get(0);
                    return ascending ? country1.compareTo(country2) : country2.compareTo(country1);
                } else { // Sort by Publications for each year
                    int pubCount1 = (int) o1.get(columnIndex);
                    int pubCount2 = (int) o2.get(columnIndex);
                    return ascending ? Integer.compare(pubCount1, pubCount2) : Integer.compare(pubCount2, pubCount1);
                }
            });

            // Notify table that data has changed
            tableModel.fireTableDataChanged();
        }
    }
}
