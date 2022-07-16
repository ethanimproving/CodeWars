package com.codewars;

import java.util.stream.IntStream;

public class PhoneNumber
{
   public static String createPhoneNumber( int[] numbers )
   {
      return String.format( "(%d%d%d) %d%d%d-%d%d%d%d", IntStream.of( numbers ).boxed().toArray());
   }
}
