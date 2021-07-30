package kyu8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToBinaryTest
{
   @Test
   public void testToBinary() {
      assertEquals(1, ToBinary.toBinary(1));
      assertEquals(10, ToBinary.toBinary(2));
      assertEquals(11, ToBinary.toBinary(3));
      assertEquals(101, ToBinary.toBinary(5));
   }
}