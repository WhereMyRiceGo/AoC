public class TestDay1Part2 {
    public static void main(String[] args) {
        // Test the example from the problem
        int startingVal = 50;
        int numberOfZeros = 0;

        System.out.println("Starting at: " + startingVal);

        // L68: Should go to 82, cross 0 once
        int turnDial = 68;
        int totalZerosCrossed = 0;
        if (turnDial > startingVal) {
            totalZerosCrossed = (turnDial - startingVal - 1) / 100 + 1;
        }
        int endPosition = (startingVal - turnDial) % 100;
        if (endPosition < 0) {
            endPosition += 100;
        }
        numberOfZeros += totalZerosCrossed;
        startingVal = endPosition;
        System.out.println("L68: End at " + startingVal + ", crossed 0 " + totalZerosCrossed + " times. Total zeros: "
                + numberOfZeros);

        // L30: Should go to 52, cross 0 zero times
        turnDial = 30;
        totalZerosCrossed = 0;
        if (turnDial > startingVal) {
            totalZerosCrossed = (turnDial - startingVal - 1) / 100 + 1;
        }
        endPosition = (startingVal - turnDial) % 100;
        if (endPosition < 0) {
            endPosition += 100;
        }
        numberOfZeros += totalZerosCrossed;
        startingVal = endPosition;
        System.out.println("L30: End at " + startingVal + ", crossed 0 " + totalZerosCrossed + " times. Total zeros: "
                + numberOfZeros);

        // R48: Should go to 0 (100), cross 0 once (or end at 0?)
        turnDial = 48;
        totalZerosCrossed = (startingVal + turnDial) / 100;
        endPosition = (startingVal + turnDial) % 100;
        numberOfZeros += totalZerosCrossed;
        startingVal = endPosition;
        System.out.println("R48: End at " + startingVal + ", crossed 0 " + totalZerosCrossed + " times. Total zeros: "
                + numberOfZeros);

        // Expected total so far: 1 (from L68) + 1 (from R48) = 2
        // But problem says R48 should end at 0, so that's counted

        System.out.println("\nExpected: 6 total");
        System.out.println("Got: " + numberOfZeros);
    }
}
