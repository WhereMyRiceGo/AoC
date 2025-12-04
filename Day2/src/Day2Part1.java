import java.util.ArrayList;
import java.util.List;

public class Day2Part1 {

    // Parse input ranges:
    // Split the comma-separated string into individual ranges (e.g., "11-22",
    // "95-115")
    // Iterate through each range: For each range, check every number from min to
    // max
    // For each number:
    // Convert the number to a string to work with its digits
    // Calculate the number of digits
    // Find all divisors of the digit
    // Check each divisor:
    // Use the divisor as the chunk size to split the number's digits
    // Check if all chunks are identical using stream matching
    // Collect matches: If any divisor creates matching chunks, add the number to
    // the results list and move to the next number
    // Calculate sum: Sum all numbers that had matching chunks

    public static void main(String[] args) {
        // String stringID =
        // "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
        // "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
        // "824824821-824824827,2121212118-2121212124";
        String stringID = "67562556-67743658,62064792-62301480,4394592-4512674,3308-4582,69552998-69828126,9123-12332,1095-1358,23-48,294-400,3511416-3689352,1007333-1150296,2929221721-2929361280,309711-443410,2131524-2335082,81867-97148,9574291560-9574498524,648635477-648670391,1-18,5735-8423,58-72,538-812,698652479-698760276,727833-843820,15609927-15646018,1491-1766,53435-76187,196475-300384,852101-903928,73-97,1894-2622,58406664-58466933,6767640219-6767697605,523453-569572,7979723815-7979848548,149-216";
        String[] stringIDs = stringID.split(",");
        List<Long> numbersWithMatches = new ArrayList<>();
        long sumInvalidIds = 0;

        System.out.println(java.util.Arrays.toString(stringIDs));
        for (String idRange : stringIDs) {
            String[] parts = idRange.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);

            while (min <= max) {
                long numberToCheck = min;

                int numDigits = numberOfDigits(numberToCheck);
                String numberString = String.valueOf(numberToCheck);
                List<Integer> divisors = getDivisors(numDigits);

                for (int divisor : divisors) {
                    if (divisor == numDigits) {
                        continue;
                    }
                    int chunkSize = divisor;
                    List<String> chunks = splitByChunkSize(numberString, chunkSize);
                    if (checkChunksForMatch(chunks)) {
                        System.out.println("Number " + numberToCheck + " has matching chunks of size " + chunkSize);
                        numbersWithMatches.add(numberToCheck);
                        break; // no need to check other divisors
                    }
                }
                min++;
            }
        }
        for (long number : numbersWithMatches) {
            sumInvalidIds += number;
        }
        System.out.println("Sum of numbers with matching halves: " + sumInvalidIds);

    }

    public static boolean checkChunksForMatch(List<String> chunks) {
        if (chunks.isEmpty()) {
            return false;
        }
        String firstChunk = chunks.get(0);
        return chunks.stream().allMatch(chunk -> chunk.equals(firstChunk));
    }

    public static List<String> splitByChunkSize(String str, int chunkSize) {
        List<String> chunks = new ArrayList<>();
        for (int i = 0; i < str.length(); i += chunkSize) {
            chunks.add(str.substring(i, Math.min(i + chunkSize, str.length())));
        }
        return chunks;
    }

    public static List<Integer> getDivisors(int number) {
        List<Integer> divisors = new ArrayList<>();
        divisors.add(1);
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    public static boolean hasEvenNumberOfDigits(long number) {
        String numberString = String.valueOf(number);
        return numberString.length() % 2 == 0;
    }

    public static long getTotalNumbersInRange(long min, long max) {
        long count = 0;
        while (min <= max) {
            count++;
            min++;
        }
        return count;
    }

    public static int numberOfDigits(long number) {
        return String.valueOf(Math.abs(number)).length();
    }

}