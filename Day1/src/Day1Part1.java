import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day1Part1 {

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
        String fileName = "Day1TestValues.txt";
        try {
            Path filePath = Paths.get(fileName);
            List<String> linesList = Files.readAllLines(filePath);
            String[] linesArray = linesList.toArray(new String[0]);
            int startingVal = 50;
            int calculatedVal;
            int numberOfZeros = 0;

            for (String line: linesArray){
                String leftOrRight = String.valueOf(line.charAt(0));
                int valueToAddOrSubtract = Integer.parseInt(line.substring(1));
                int digits = countDigits(valueToAddOrSubtract);
                if (digits > 2) {
                    valueToAddOrSubtract = getLastTwoDigits(valueToAddOrSubtract);
                }
                if (leftOrRight.equals("L")){
                    calculatedVal = startingVal - valueToAddOrSubtract;
                    if (calculatedVal < 0){
                        startingVal = 100 - Math.abs(calculatedVal);
                        numberOfZeros++;
                    } else {
                        startingVal = calculatedVal;
                    }
                } else {
                    calculatedVal = startingVal + valueToAddOrSubtract;
                    if (calculatedVal > 99){
                        valueToAddOrSubtract = calculatedVal - 100;
                        startingVal = Math.abs(valueToAddOrSubtract);
                        numberOfZeros++;
                    } else {
                        startingVal = calculatedVal;
                    }
                }
                if (startingVal > 100){
                    System.out.println("valueToAddOrSubtract was: " + valueToAddOrSubtract);
                    System.out.println("line was " + line);
                    throw new Exception("startVal is 100 or greater: " + startingVal + " calculatedVal = " + calculatedVal);
                }
                if (startingVal < 0){
                    System.out.println("line was: " + line);
                    System.out.println("valueToAddOrSubtract was: " + valueToAddOrSubtract);
                    throw new Exception("startVal is negative: " + startingVal + " calculatedVal = " + calculatedVal);
                }

                if (startingVal == 100){
                    startingVal = 0;
                }
                System.out.println("New startingVal = " + startingVal);
                if (startingVal == 0){
                    numberOfZeros++;
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