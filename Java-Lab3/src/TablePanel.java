// Maddock Davis
// Lab 3: Table panel

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

// The table panel for the GUI
public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> columnSelector;
    private JButton sortAscButton;
    private JButton sortDescButton;
    private JButton removeFilterButton;
    private List<DevelopmentIndicator> originalData;

    // Constructor for Table Panel
    public TablePanel(List<DevelopmentIndicator> data, DetailsPanel detailsPanel) {
        setLayout(new BorderLayout());

        // Store the original data for resetting the table
        originalData = data;

        // Define the number of years for columns dynamically based on the data
        int numYears = data.isEmpty() ? 0 : data.get(0).getPublicationsPerYear().size();
        String[] columnNames = new String[numYears + 1];
        columnNames[0] = "Country";

        // Initialize column names for years
        for (int i = 1; i <= numYears; i++) {
            columnNames[i] = "" + (i + 1989);
        }

        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Populate table with data
        populateTable(data);

        // Create sorting panel
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new FlowLayout());

        // Create a column selector for sorting
        columnSelector = new JComboBox<>(columnNames);
        sortPanel.add(columnSelector);

        // Create sorting buttons
        sortAscButton = new JButton("Sort Ascending");
        sortDescButton = new JButton("Sort Descending");
        removeFilterButton = new JButton("Remove Filter");

        sortAscButton.addActionListener(new SortActionListener(true));
        sortDescButton.addActionListener(new SortActionListener(false));
        removeFilterButton.addActionListener(new RemoveFilterActionListener());

        sortPanel.add(sortAscButton);
        sortPanel.add(sortDescButton);
        sortPanel.add(removeFilterButton);

        // Selection listener to update details panel
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String country = (String) tableModel.getValueAt(selectedRow, 0);
                        for (DevelopmentIndicator indicator : data) {
                            if (indicator.getCountry().equals(country)) {
                                detailsPanel.updateDetails(indicator);
                                break;
                            }
                        }
                    } else {
                        detailsPanel.updateDetails(null);
                    }
                }
            }
        });

        // Add sort panel and table to the main panel
        add(sortPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Populate the table with data
    private void populateTable(List<DevelopmentIndicator> data) {
        tableModel.setRowCount(0);
        for (DevelopmentIndicator indicator : data) {
            Object[] row = new Object[indicator.getPublicationsPerYear().size() + 1];
            row[0] = indicator.getCountry();
            List<Integer> publications = indicator.getPublicationsPerYear();
            for (int i = 0; i < publications.size(); i++) {
                row[i + 1] = publications.get(i);
            }
            tableModel.addRow(row);
        }
    }

    // Filters to sort through the table
    private class SortActionListener implements ActionListener {
        private final boolean ascending;

        public SortActionListener(boolean ascending) {
            this.ascending = ascending;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int columnIndex = columnSelector.getSelectedIndex();
            Vector<Vector> dataVector = tableModel.getDataVector();

            // Manages the sorting of the table
            dataVector.sort((o1, o2) -> {
                if (columnIndex == 0) {
                    String country1 = (String) o1.get(0);
                    String country2 = (String) o2.get(0);
                    return ascending ? country1.compareTo(country2) : country2.compareTo(country1);
                } else {
                    int pubCount1 = (int) o1.get(columnIndex);
                    int pubCount2 = (int) o2.get(columnIndex);
                    return ascending ? Integer.compare(pubCount1, pubCount2) : Integer.compare(pubCount2, pubCount1);
                }
            });

            // Notify table that data has changed
            tableModel.fireTableDataChanged();
        }
    }

    // Action listener for removing the filter
    private class RemoveFilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reset the table to the original data
            populateTable(originalData);
        }
    }
}
