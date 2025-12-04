package Day3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3Part1 {

    public static void main(String[] args) {
        String fileContents = readInputFromFile("Day3Part1Input");
        // String fileContents = readInputFromFile("./Day3Part1InputExample");
        List<String> stringList = fileContents.lines().toList();
        List<Integer> results = new ArrayList<>();
        for (String line : stringList) {
            int[] jolts = getLargestJolts(line);
            String num1 = String.valueOf(jolts[0]);
            String num2 = String.valueOf(jolts[1]);
            String num = num1 + num2;
            results.add(Integer.parseInt(num));
        }
        System.out.println("All results: " + results);
        int sumCollector = results.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum of all results: " + sumCollector);

    }

    private static int[] getLargestJolts(String line) {
        int[] numbers = convertStringToIntArrayStream(line);
        int biggestNumberFromTheLeft = getBiggestNumberAndPosFromLeft(numbers)[0];
        int biggestNumberFromTheLeftPosition = getBiggestNumberAndPosFromLeft(numbers)[1];
        int biggestNumberFromTheRight = getBiggestNumberFromTheRight(numbers, biggestNumberFromTheLeft,
                biggestNumberFromTheLeftPosition);
        return new int[] { biggestNumberFromTheLeft, biggestNumberFromTheRight };
    }

    public static int[] convertStringToIntArrayStream(String input) {
        return input.chars().map(Character::getNumericValue).toArray();
    }

    private static int getBiggestNumberFromTheRight(int[] numbers, int biggestNumberFromTheLeft,
            int biggestNumberFromTheLeftPosition) {
        int biggestNumberFromTheRight = numbers[numbers.length - 1];
        for (int i = numbers.length - 1; i > biggestNumberFromTheLeftPosition; i--) {
            int currentNumber = numbers[i];
            if (biggestNumberFromTheRight < currentNumber) {
                biggestNumberFromTheRight = currentNumber;
            }
        }
        return biggestNumberFromTheRight;
    }

    private static int[] getBiggestNumberAndPosFromLeft(int[] numbers) {
        int biggestNumberFromTheLeft = 0;
        int position = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            int currentNumber = numbers[i];
            if (currentNumber > biggestNumberFromTheLeft) {
                biggestNumberFromTheLeft = currentNumber;
                position = i;
            }
        }
        return new int[] { biggestNumberFromTheLeft, position };
    }

    public static String readInputFromFile(String fileName) {
        Path path = Path.of(fileName);
        String content;
        try {
            content = Files.readString(path, StandardCharsets.UTF_8);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
