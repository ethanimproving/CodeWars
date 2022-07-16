package com.codewars;

import com.codewars.StringToCamelCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToCamelCaseTest
{
   @Test
   public void testSomeUnderscoreLowerStart() {
      String input = "the_Stealth_Warrior";
      System.out.println("input: "+input);
      assertEquals("theStealthWarrior", StringToCamelCase.toCamelCase(input));
   }
   @Test
   public void testSomeDashLowerStart() {
      String input = "the-Stealth-Warrior";
      System.out.println("input: "+input);
      assertEquals("theStealthWarrior", StringToCamelCase.toCamelCase(input));
   }
}