import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class EndlessScores {
    private final int MAX_SCORES = 7;
    private final String filePath = "src/Endless_HighScores.csv";

    private String[] dates;
    private String[] times;
    private int[] scores;

    public EndlessScores() {
        dates = new String[MAX_SCORES];
        times = new String[MAX_SCORES];
        scores = new int[MAX_SCORES];

        readScores();
        writeScores();
    }

    // public static void main(String[] args) {
    // EndlessScores e = new EndlessScores();
    // e.sortScores();
    // e.addScore(50);
    // }

    private void sortScores() {
        int[] prevInds = IntStream.range(0, scores.length)
                .boxed()
                .sorted((i, j) -> Integer.compare(scores[j], scores[i]))
                .mapToInt(i -> i)
                .toArray(); // previous indices of sorted array
        int[] scoresCpy = scores.clone();
        String[] datesCpy = dates.clone();
        String[] timesCpy = times.clone();
        for (int i = 0; i < MAX_SCORES; i++) {
            scores[i] = scoresCpy[prevInds[i]];
            dates[i] = datesCpy[prevInds[i]];
            times[i] = timesCpy[prevInds[i]];
        }
        writeScores();
    }

    public void addScore(int score) {
        if (score >= scores[MAX_SCORES - 1]) {
            scores[MAX_SCORES - 1] = scores[0];
            dates[MAX_SCORES - 1] = dates[0];
            times[MAX_SCORES - 1] = times[0];

            scores[0] = score;
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            dates[0] = date.toString();
            times[0] = time.format(DateTimeFormatter.ofPattern("HH:mm:ss")); // remove nano time
        }
        sortScores();
        writeScores();
    }

    private void readScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { // try with resources
            String line;
            reader.readLine(); // skip header
            int i = 0;
            while ((line = reader.readLine()) != null) { // shorthand: line and reader.readLine() have same value
                if (i >= MAX_SCORES) {
                    break;
                }
                String[] data = line.split(",");
                dates[i] = data[0];
                times[i] = data[1];
                try {
                    scores[i] = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // try with resources
            writer.write("dates,times,scores\n"); // header line
            for (int i = 0; i < MAX_SCORES; i++) {
                writer.write(dates[i] + "," + times[i] + "," + scores[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteScores() {
        dates = new String[MAX_SCORES];
        times = new String[MAX_SCORES];
        scores = new int[MAX_SCORES];
        writeScores();
    }

    public String[] getDates() {
        return dates;
    }

    public String[] getTimes() {
        return times;
    }

    public int[] getScores() {
        return scores;
    }

}
