import java.util.ArrayList;
import java.util.List;

public class Statistics {
    public static double calculateMean(List<DevelopmentIndicator> data) {
        int totalCount = 0;
        int totalPublications = 0;

        for (DevelopmentIndicator indicator : data) {
            totalPublications += indicator.getTotalPublications();
            totalCount++;
        }
        return totalCount == 0 ? 0 : (double) totalPublications / totalCount;
    }

    public static double calculateMedian(List<DevelopmentIndicator> data) {
        List<Integer> allPublications = new ArrayList<>(); // Ensure the import for ArrayList is present
        for (DevelopmentIndicator indicator : data) {
            allPublications.addAll(indicator.getPublicationsPerYear());
        }
        allPublications.sort(Integer::compareTo);
        int size = allPublications.size();
        if (size == 0) return 0;
        if (size % 2 == 0) {
            return (allPublications.get(size / 2 - 1) + allPublications.get(size / 2)) / 2.0;
        } else {
            return allPublications.get(size / 2);
        }
    }

    public static double calculateStdDev(List<DevelopmentIndicator> data) {
        double mean = calculateMean(data);
        double sumSquaredDiff = 0;
        int totalCount = 0;

        for (DevelopmentIndicator indicator : data) {
            for (Integer publications : indicator.getPublicationsPerYear()) {
                sumSquaredDiff += Math.pow(publications - mean, 2);
                totalCount++;
            }
        }
        return totalCount == 0 ? 0 : Math.sqrt(sumSquaredDiff / totalCount);
    }
}
