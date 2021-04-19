import java.util.Arrays;

/**
 * Your code's time complexity is O(n2) and that won't work here.
 * You code's time complexity should be O(n) to pass.
 */
public class SumParts {
    public static int[] sumParts(int[] ls) {
        int[] output = new int[ls.length + 1];
        int sum = Arrays.stream(ls).sum();

        for (int i = 0; i < ls.length; i++) {
            output[i] = sum;
            sum -= ls[i];
        }

        return output;
    }

    public static int[] solution1(int[] ls) {
        int[] output = new int[ls.length + 1];

        for (int i = 0; i < ls.length; i++) {
            int sum = 0;
            for (int element : ls)
                sum += element;
            output[i] = sum;
            ls[i] = 0;
        }

        return output;
    }

    public static int[] solution2(int[] ls) {
        int[] output = new int[ls.length + 1];

        for (int i = 0; i < ls.length; i++) {
            output[i] = Arrays.stream(ls).sum();
            ls[i] = 0;
        }

        return output;
    }
}
