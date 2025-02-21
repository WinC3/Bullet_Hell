import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndlessScores {
    private final int MAX_SCORES = 5;
    private final String filePath = "Endless_HighScores.csv";

    private String[] dates;
    private String[] times;
    private int[] scores;

    public EndlessScores() {
        dates = new String[MAX_SCORES];
        times = new String[MAX_SCORES];
        scores = new int[MAX_SCORES];

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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // try with resources
            writer.write("dates,times,scores\n"); // header line
            for (int i = 0; i < MAX_SCORES; i++) {
                writer.write(dates[i] + "," + times[i] + "," + scores[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new EndlessScores();
    }

}
