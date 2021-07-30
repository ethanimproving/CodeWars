package kyu8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest
{
   @Test
   public void testSetToThree() {
      Cube c = new Cube();
      c.setSide(3);
      assertEquals( 3, c.getSide());
   }
}