import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part2 {

    public static int countDigits(long number) {
        String numberString = String.valueOf(number);
        if (numberString.startsWith("-")) {
            return numberString.length() - 1;
        } else {
            return numberString.length();
        }
    }

    public static int getLastTwoDigits(int number) {
        return number % 100;
    }

    public static void main(String[] args) {
        String fileName = "Day1InputWhereMyRiceGo";
        try {
            Path filePath = Paths.get(fileName);
            List<String> linesList = Files.readAllLines(filePath);
            String[] linesArray = linesList.toArray(new String[0]);
            int startingVal = 50;
            int numberOfZeros = 0;
            System.out.println("The dial starts by pointing at: " + startingVal);

            for (String line : linesArray) {

                String leftOrRight = String.valueOf(line.charAt(0));
                int turnDial = Integer.parseInt(line.substring(1));

                if (leftOrRight.equals("L")) {
                    // Left rotation: Count zeros from X down to X-N
                    // Using floor division to count boundary crossings
                    int totalZerosCrossed = (startingVal + turnDial) / 100;

                    // Calculate final position
                    int endPosition = (startingVal - turnDial) % 100;
                    if (endPosition < 0) {
                        endPosition += 100;
                    }

                    numberOfZeros += totalZerosCrossed;
                    startingVal = endPosition;
                } else {
                    // Right rotation: Count zeros from X to X+N
                    int totalZerosCrossed = (startingVal + turnDial) / 100;

                    // Calculate final position
                    int endPosition = (startingVal + turnDial) % 100;

                    numberOfZeros += totalZerosCrossed;
                    startingVal = endPosition;
                }
            }
            System.out.println("numberOfZeros = " + numberOfZeros);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}