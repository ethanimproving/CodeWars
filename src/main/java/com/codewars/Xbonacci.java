package com.codewars;

import java.util.Arrays;

public class Xbonacci
{

   /**
    * 1. for each n number of times
    * 2. sum the last 3 numbers in the array, and add it
    *
    * @param s array of doubles to be sequenced.
    * @param n number of elements to be returned.
    * @return a tribonacci sequence.
    */
   public double[] tribonacci(double[] s, int n) {
      s = Arrays.copyOf( s, n);
      for (int i = 3; i < n ; i++)
         s[i] = s[i-1] + s[i-2] + s[i-3];
      return s;
   }

}
