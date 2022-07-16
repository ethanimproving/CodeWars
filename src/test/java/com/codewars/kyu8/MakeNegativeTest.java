package com.codewars.kyu8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeNegativeTest
{
   @Test
   public void test1() {
      assertEquals(-42, MakeNegative.makeNegative(42));
      assertEquals(-42, MakeNegative.makeNegative(-42));
      assertEquals(0, MakeNegative.makeNegative(0));
   }
}