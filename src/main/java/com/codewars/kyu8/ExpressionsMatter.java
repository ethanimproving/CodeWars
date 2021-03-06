package com.codewars.kyu8;

import java.util.stream.IntStream;

public class ExpressionsMatter
{
   public static int expressionsMatter(int a, int b, int c)
   {
      return IntStream.of( a * ( b + c ), ( a + b ) * c, a + b + c, a * b * c ).max().getAsInt();
   }
}
