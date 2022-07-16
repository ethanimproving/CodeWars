package com.codewars;

public class Kata {
    /**
     * A         B          C<br>
     * |1 2|  x  |3 2|  =  | 5 4|<br>
     * |3 2|     |1 1|     |11 8|<br>
     * <p>
     * C[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0] = 1*3 + 2*1 =  5<br>
     * C[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1] = 1*2 + 2*1 =  4<br>
     * C[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0] = 3*3 + 2*1 = 11<br>
     * C[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1] = 3*2 + 2*1 =  8<br>
     */
    public static int[][] matrixMultiplication(int[][] a, int[][] b) {
        int length = a.length;
        int[][] result = new int[length][length];

        for (int row = 0; row < length; row++)
            for (int col = 0; col < length; col++)
                for (int k = 0; k < length; k++)
                    result[row][col] += a[row][k] * b[k][col];

        return result;
    }
}