import java.util.ArrayList;
import java.util.List;

public class Day2Part1 {

    public static void main(String[] args) {
        // String stringID =
        // "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
        // "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
        // "824824821-824824827,2121212118-2121212124";
        String stringID = "67562556-67743658,62064792-62301480,4394592-4512674,3308-4582,69552998-69828126,9123-12332,1095-1358,23-48,294-400,3511416-3689352,1007333-1150296,2929221721-2929361280,309711-443410,2131524-2335082,81867-97148,9574291560-9574498524,648635477-648670391,1-18,5735-8423,58-72,538-812,698652479-698760276,727833-843820,15609927-15646018,1491-1766,53435-76187,196475-300384,852101-903928,73-97,1894-2622,58406664-58466933,6767640219-6767697605,523453-569572,7979723815-7979848548,149-216";

        String[] stringIDs = stringID.split(",");
        List<Long> numbersWithMatchingHalves = new ArrayList<>();
        long sumInvalidIds = 0;

        System.out.println(java.util.Arrays.toString(stringIDs));
        for (String idRange : stringIDs) {
            String[] parts = idRange.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);

            // System.out.println("For range " + min + "-" + max);
            long totalNumbersInRange = getTotalNumbersInRange(min, max);
            // System.out.println("Total numbers in range: " + totalNumbersInRange);

            // for each number within the range do
            while (min <= max) {
                long numberToCheck = min;
                // System.out.println("Checking number: " + numberToCheck);

                long numDigits = numberOfDigits(numberToCheck);
                String numberString = String.valueOf(numberToCheck);
                // System.out.println("Number " + numberToCheck + " has " + numDigits + "
                // digits.");
                // if numberString has even number of digits, split it in half
                if (hasEvenNumberOfDigits(numberToCheck)) {
                    int halfLength = numberString.length() / 2;
                    String firstHalf = numberString.substring(0, halfLength);
                    String secondHalf = numberString.substring(halfLength);
                    // System.out.println("First half: " + firstHalf + ", Second half: " +
                    // secondHalf);
                    if (firstHalf.equals(secondHalf)) {
                        // System.out.println("Found matching halves: " + firstHalf + " and " +
                        // secondHalf);
                        System.out.println(firstHalf + secondHalf);
                        numbersWithMatchingHalves.add(Long.parseLong(firstHalf + secondHalf));
                    }
                }
                min++;
            }
        }
        for (long number : numbersWithMatchingHalves) {
            sumInvalidIds += number;
        }
        System.out.println("Sum of numbers with matching halves: " + sumInvalidIds);

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

    public static long numberOfDigits(long number) {
        return String.valueOf(Math.abs(number)).length();
    }

}