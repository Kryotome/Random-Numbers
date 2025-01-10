
import java.util.Random;
import java.util.Scanner;

/**
 * 1. Prompt the user for the number of random numbers.
 * 2. Parse and validate the input.
 * 3. Generate the random numbers.
 * 4. Classify numbers into even and odd arrays.
 * 5. Sort the arrays (even in ascending, odd in descending order).
 * 6. Display the results.
 * 7. Handle errors for invalid input or out-of-memory cases.
 * @author Tauhid Mahmud
 * @version 1.0
 */

public class Main {


    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0 - 999 are desired?";
    static final String RANDOM_NUMBERS_LIST_MESSAGE = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED_MESSAGE = "Here are the random numbers arranged:";
    static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    static final int MAX_RANDOM_NUMBER = 1000;

    /**
     * Pseudocode:
     * 1. Prompt the user for the number of random numbers.
     * 2. Parse and validate the input.
     * 3. Generate the random numbers.
     * 4. Classify numbers into even and odd arrays.
     * 5. Sort the arrays (even in ascending, odd in descending order).
     * 6. Display the results.
     * 7. Handle errors for invalid input or out-of-memory cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for input
            System.out.println(USER_INPUT_PROMPT);
            String input = scanner.nextLine();
            int count = Integer.parseInt(input);

            // Validate input
            if (count <= 0) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }

            // Generate random numbers
            int[] numbers = generateRandomNumbers(count);

            // Display random numbers
            System.out.println(RANDOM_NUMBERS_LIST_MESSAGE);
            printArray(numbers);

            // Separate numbers into even and odd arrays
            int[] evenNumbers = new int[count];
            int[] oddNumbers = new int[count];
            int evenCount = 0;
            int oddCount = 0;

            for (int number : numbers) {
                if (number % 2 == 0) {
                    evenNumbers[evenCount++] = number;
                } else {
                    oddNumbers[oddCount++] = number;
                }
            }

            // Sort the arrays
            sortArray(evenNumbers, 0, evenCount - 1, true);
            sortArray(oddNumbers, 0, oddCount - 1, false);

            // Display sorted results
            System.out.println(RANDOM_NUMBERS_SORTED_MESSAGE);
            printSortedResults(evenNumbers, evenCount, oddNumbers, oddCount);

        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        } catch (OutOfMemoryError e) {
            System.out.println("Impossible to handle the entered number. Out of memory.");
        }
    }
    /**
     * Pseudocode:
     * 1. Create an array of specified size.
     * 2. Generate random integers in the range [0, 999].
     * 3. Store the integers in the array.
     * 4. Return the array.
     *
     * @param count The number of random integers to generate.
     * @return The array of random integers.
     */
    private static int[] generateRandomNumbers(final int count) {
        Random random = new Random();
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(MAX_RANDOM_NUMBER); // Generate numbers in range 0-999
        }
        return numbers;
    }

    /**
     * Pseudocode:
     * 1. Iterate through the array elements from the start index to the end index.
     * 2. Use bubble sort to sort the array:
     *    - If ascending is true, sort in ascending order.
     *    - If ascending is false, sort in descending order.
     * 3. Swap elements if they are not in the desired order.
     *
     * @param array The array to sort.
     * @param start The starting index of the sort.
     * @param end The ending index of the sort.
     * @param ascending True for ascending, false for descending.
     */
    private static void sortArray(final int[] array, final int start, final int end, final boolean ascending) {
        for (int i = start; i <= end; i++) {
            for (int j = start; j < end - i + start; j++) {
                if ((ascending && array[j] > array[j + 1]) || (!ascending && array[j] < array[j + 1])) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Pseudocode:
     * 1. Iterate through the array.
     * 2. Print each element separated by a space.
     * 3. Print a newline at the end.
     *
     * @param array The array to print.
     */
    private static void printArray(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * Pseudocode:
     * 1. Check if even numbers exist:
     *    - If yes, print them in a single line separated by spaces.
     *    - If no, print "No Even Numbers".
     * 2. Check if odd numbers exist:
     *    - If yes, print them in a single line separated by spaces.
     *    - If no, print "No Odd Numbers".
     * 3. Print the total count of numbers, even count, and odd count.
     *
     * @param evenNumbers Array of even numbers.
     * @param evenCount Count of even numbers.
     * @param oddNumbers Array of odd numbers.
     * @param oddCount Count of odd numbers.
     */
    private static void printSortedResults(final int[] evenNumbers, final int evenCount, final int[] oddNumbers, final  int oddCount) {
        if (evenCount > 0) {
            for (int i = 0; i < evenCount; i++) {
                System.out.print(evenNumbers[i]);
                if (i < evenCount - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.print(NO_EVEN_NUMBERS_MESSAGE);
        }

        System.out.print(" - ");

        if (oddCount > 0) {
            for (int i = 0; i < oddCount; i++) {
                System.out.print(oddNumbers[i]);
                if (i < oddCount - 1) {
                    System.out.print(" ");
                }
            }
        } else {
            System.out.print(NO_ODD_NUMBERS_MESSAGE);
        }

        System.out.println();

        System.out.printf("Of the above %d numbers, %d were even and %d odd%n",
                evenCount + oddCount, evenCount, oddCount);
    }
} 
