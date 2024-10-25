// Maddock Davis
// Lab 3: Data loader

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Loads the data from the CSV file
public class DataLoader {
    public static List<DevelopmentIndicator> loadData(String filename) {
        List<DevelopmentIndicator> indicators = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String country = values[0];
                List<Integer> publications = new ArrayList<>();

                // Start from index 2 for the years
                for (int i = 2; i < values.length; i++) {
                    publications.add(Integer.parseInt(values[i]));
                }
                indicators.add(new DevelopmentIndicator(country, publications));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indicators;
    }
}
