// Maddock Davis
// Lab 3: Data loader

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Loads the data from the CSV file
public class DataLoader {
    public static List<DevelopmentIndicator> loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            // Skip the header line and process each line as a DevelopmentIndicator
            return br.lines()
                    .skip(1)
                    .map(line -> {
                        String[] values = line.split(";");
                        String country = values[0];
                        List<Integer> publications = Stream.of(values)
                                .skip(2)
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        return new DevelopmentIndicator(country, publications);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
